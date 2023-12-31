package com.xbw.lottery.domain.strategy.service.draw;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.strategy.model.aggregate.StrategyRich;
import com.xbw.lottery.domain.strategy.model.req.DrawReq;
import com.xbw.lottery.domain.strategy.model.res.DrawResult;
import com.xbw.lottery.domain.strategy.model.vo.*;
import com.xbw.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义模板方法流程。
 * 在抽象类的 doDrawExec 方法中，定义整个抽奖流程的方法执行顺序，并提供在流程中需要使用到的抽象方法，
 * 由 DrawExecImpl 服务逻辑中做具体实现。
 */
@Slf4j
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {

    /**
     * 定义整个抽奖流程的方法执行顺序
     * 注意，为防止恶意操作，一般模板方法都加上 final 关键词，禁止其子类重写该方法
     *
     * @param drawReq
     * @return
     */
    @Override
    public final DrawResult doDrawExec(DrawReq drawReq) {
        //1.根据入参策略ID获取抽奖策略配置
        StrategyRich strategyRich = super.queryStrategyRich(drawReq.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();
        Long strategyId = strategy.getStrategyId();
        Integer strategyMode = strategy.getStrategyMode();

        //2.校验和处理抽奖策略的数据初始化到内存
        this.checkAndInitRateData(strategyId, strategyMode, strategyRich.getStrategyDetailList());

        //3.获取那些被排除掉的奖品ID列表，这些奖品可能是已经奖品库存为空，或者因为风控策略不能给这个用户薅羊毛的奖品
        List<Long> excludedAwardIdList = this.queryExcludedAwardIdList(strategyId);

        //4.执行抽奖算法
        Long awardId = this.drawAlgorithm(strategyId, drawAlgorithmGroup.get(strategyMode), excludedAwardIdList);

        //5.包装中奖结果
        return buildDrawResult(drawReq.getuId(), strategyId, awardId, strategy);
    }

    /**
     * 校验和处理抽奖策略的数据初始化到内存
     *
     * @param strategyId
     * @param strategyMode
     * @param strategyDetailList
     */
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {
        // 根据抽奖策略模式，获取对应的抽奖服务
        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);

        // 判断已处理过的的数据
        if (drawAlgorithm.isExist(strategyId)) {
            return;
        }

        // 解析并初始化中奖概率数据到散列表
        List<AwardRateVO> awardRateVOList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateVOList.add(new AwardRateVO(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, strategyMode, awardRateVOList);

    }


    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等，这类数据是含有业务逻辑的，所以需要定为抽象方法，由具体实现方决定
     *
     * @param strategyId
     * @return 奖品ID列表
     */
    protected abstract List<Long> queryExcludedAwardIdList(Long strategyId);


    /**
     * 执行抽奖算法
     *
     * @param strategyId
     * @param drawAlgorithm
     * @param excludedAwardIdList
     * @return 中奖奖品ID
     */
    protected abstract Long drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<Long> excludedAwardIdList);

    /**
     * 包装抽奖结果
     *
     * @param userId
     * @param strategyId
     * @param awardId
     * @return
     */
    private DrawResult buildDrawResult(String userId, Long strategyId, Long awardId, StrategyBriefVO strategy) {
        // 并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
        if (null == awardId) {
            log.info("抽奖结果：【未中奖】，用户id：{}，策略id：{}", userId, strategyId);
            return new DrawResult(userId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        String awardName = award.getAwardName();
        DrawAwardVO drawAwardVO = new DrawAwardVO(userId, award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        drawAwardVO.setStrategyMode(strategy.getStrategyMode());
        drawAwardVO.setGrantType(strategy.getGrantType());
        drawAwardVO.setGrantDate(strategy.getGrantDate());
        log.info("抽奖结果：【已中奖】，用户id：{}，策略id：{}，奖品名称：{}", userId, strategyId, awardName);

        return new DrawResult(userId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardVO);
    }
}
