package com.xbw.lottery.infrastructure.repository;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.activity.model.req.PartakeReq;
import com.xbw.lottery.domain.activity.model.res.StockResult;
import com.xbw.lottery.domain.activity.model.vo.*;
import com.xbw.lottery.domain.activity.repository.IActivityRepository;
import com.xbw.lottery.infrastructure.dao.*;
import com.xbw.lottery.infrastructure.po.*;
import com.xbw.lottery.infrastructure.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IActivityDao activityDao;

    @Resource
    private IAwardDao awardDao;

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);

        // 设置活动已使用库存 KEY，value=0，使用了0个库存，此时库存为满
        redisUtil.set(Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_USED_COUNT(activity.getActivityId()), 0);
    }

    @Override
    public void addAwardList(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVO awardVO : awardList) {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO, award);
            req.add(award);
        }
        awardDao.insertAwardList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insertStrategy(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertStrategyDetailList(req);
    }

    @Override
    public boolean alterState(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVO);
        return 1 == count;
    }

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {

        // 查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 从缓存中获取已使用的库存数
        Object usedStockCountObj = redisUtil.get(Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_USED_COUNT(req.getActivityId()));

        // 查询领取次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setuId(req.getuId());
        userTakeActivityCountReq.setActivityId(req.getActivityId());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountDao.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 封装结果信息
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setuId(req.getuId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setStockCount(activity.getStockCount());
        // 从缓存中获取剩余库存
        activityBillVO.setStockSurplusCount(null == usedStockCountObj ?
                activity.getStockSurplusCount() : activity.getStockCount() - Integer.parseInt(String.valueOf(usedStockCountObj)));
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setActivityState(activity.getActivityState());
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null : userTakeActivityCount.getLeftCount());

        return activityBillVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityDao.subtractionActivityStock(activityId);
    }


    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        List<Activity> activityList = activityDao.scanToDoActivityList(id);
        List<ActivityVO> activityVOList = new ArrayList<>(activityList.size());
        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setBeginDateTime(activity.getBeginDateTime());
            activityVO.setEndDateTime(activity.getEndDateTime());
            activityVO.setState(activity.getActivityState());
            activityVOList.add(activityVO);
        }
        return activityVOList;
    }

    /** 为什么要搞两个key，而且一个不加锁，一个加锁？
     * <p>
     * 1. 不加锁的key记录已使用库存，但在集群模式下，incr会因为网络抖动、集群主从切换，或活动出问题时恢复的 incr 值多了等原因而不准，
     * 所以不能拿这个数字当锁，只能拿来当准确率较高的库存缓存，
     * 并为加锁的key生成一个stockUsedCount的标记数。加锁的key其实是为每一个sku（每一间库存）创建一个锁，防止incr出错让101个人进来抢100个库存，
     * 此时必有一个库存是至少被2个人抢的，这时每个库存都有一个自己的锁来做兜底拦截，就能防止超卖了。又不加锁key在incr后有是否超出总库存的判断，
     * 所以能保证加锁的key的个数不超过总库存。
     * <p>
     * 2. incr的那个key不好把握释放时间，因为秒杀都是瞬态的，释放的晚了活动用户都走了，释放的早了，流程可能还没处理完。而setNx是为每个sku加的锁，
     * 用户占用一次就不再需要被其他用户占用了，所以锁的key可以设置活动结束后释放。
     *
     * 关于库存恢复，一般这类抽奖都是瞬态的，且redis集群非常稳定。所以很少有需要恢复库存，如果需要恢复库存，
     * 那么是把失败的秒杀incr对应的值的key，加入到待消费队列中。等整体库存消耗后，开始消耗队列库存。
     *
     * @param uId        用户ID
     * @param activityId 活动ID
     * @param stockCount 总库存
     * @return
     */

    @Override
    public StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {
        //  1. 获取抽奖活动已使用库存 Key
        String stockKey = Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_USED_COUNT(activityId);

        // 2. 扣减库存，目前占用库存数
        Integer stockUsedCount = (int) redisUtil.incr(stockKey, 1);

        // 3. 超出总库存判断，进行恢复原始库存
        if (stockUsedCount > stockCount) {
            redisUtil.decr(stockKey, 1);
            return new StockResult(Constants.ResponseCode.UNKNOWN_ERROR.getCode(), Constants.ResponseCode.UNKNOWN_ERROR.getInfo());
        }

        // 4. 以活动库存占用编号，生成对应加锁Key，细化锁的颗粒度
        String stockTokenKey = Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_USED_COUNT_TOKEN(activityId, stockUsedCount);

        // 5. 使用 Redis.setNx 加一个分布式锁
        boolean lockToken = redisUtil.setNx(stockTokenKey, 350L);
        if (!lockToken) {
            log.info("抽奖活动{}用户秒杀{}扣减库存，分布式锁失败：{}", activityId, uId, stockTokenKey);
            return new StockResult(Constants.ResponseCode.UNKNOWN_ERROR.getCode(), Constants.ResponseCode.UNKNOWN_ERROR.getInfo());
        }
        return new StockResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), stockTokenKey, stockCount - stockUsedCount);

    }

    @Override
    public void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, Integer code) {
        // 删除key
        redisUtil.del(tokenKey);
    }

}
