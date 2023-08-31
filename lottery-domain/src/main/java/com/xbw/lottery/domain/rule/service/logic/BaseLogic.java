package com.xbw.lottery.domain.rule.service.logic;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.rule.model.req.DecisionMatterReq;
import com.xbw.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * 规则基础抽象类
 */
public abstract class BaseLogic implements LogicFilter{


    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        // 遍历每一条边（每一种判断条件）
        for (TreeNodeLineVO treeNodeLineVO : treeNodeLineInfoList) {
            // 符合判断条件的返回下一个节点的id
            if (decisionLogic(matterValue, treeNodeLineVO)) {
                return treeNodeLineVO.getNodeIdTo();
            }
        }
        // 都不符合则返回空节点
        return Constants.Global.TREE_NULL_NODE;
    }

    /**
     * 在抽象方法中实现接口方法，获取规则比对值。
     * 每一个实现接口的类都必须按照规则提供决策值，这个决策值用于做逻辑比对
     * @param decisionMatter 决策物料
     * @return 比对值
     */

    @Override
    public abstract String matterValue(DecisionMatterReq decisionMatter);


    /**
     * 定义基本的决策方法 1、2、3、4、5，等于、小于、大于、小于等于、大于等于的判断逻辑
     *
     * @param matterValue
     * @param nodeLine
     * @return
     */
    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }

}
