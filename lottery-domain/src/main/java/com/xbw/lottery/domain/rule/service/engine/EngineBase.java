package com.xbw.lottery.domain.rule.service.engine;

import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.xbw.lottery.domain.rule.model.req.DecisionMatterReq;
import com.xbw.lottery.domain.rule.model.res.EngineResult;
import com.xbw.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.xbw.lottery.domain.rule.model.vo.TreeNodeVO;
import com.xbw.lottery.domain.rule.model.vo.TreeRootVO;
import com.xbw.lottery.domain.rule.service.logic.LogicFilter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 规则引擎基础类
 * 提供决策树流程的处理过程，有点像通过链路的关系(性别、年龄)在二叉树中寻找果实节点的过程。
 */
@Slf4j
public abstract class EngineBase extends EngineConfig implements EngineFilter {


    @Override
    public abstract EngineResult process(DecisionMatterReq matter);

    /**
     * 决策树决策的流程处理
     *
     * @param treeRuleRich
     * @return
     */
    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq matter) {
        // 获取决策树根节点
        TreeRootVO treeRoot = treeRuleRich.getTreeRoot();

        // 获取决策树的节点map，每个节点id->节点信息
        Map<Long, TreeNodeVO> treeNodeMap = treeRuleRich.getTreeNodeMap();

        // 决策树根节点id
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        // 获取根节点的信息（有点多余，但是为了下面循环，赋给通用的引用）
        TreeNodeVO treeNodeInfo = treeNodeMap.get(rootNodeId);

        // 循环遍历决策树，直到找到果实节点
        while (Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())) {
            // 获取当前节点的规则关键字
            String ruleKey = treeNodeInfo.getRuleKey();

            // 根据规则关键字从逻辑过滤器映射中获取逻辑过滤器对象
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);

            // 计算当前节点的"matterValue"值，这个值将用于决策
            String matterValue = logicFilter.matterValue(matter);

            // 使用逻辑过滤器来确定下一个节点的ID
            Long nextNodeId = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());

            // 获取下一个节点的信息
            treeNodeInfo = treeNodeMap.get(nextNodeId);
            log.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}",
                    treeRoot.getTreeName(), matter.getUserId(), matter.getTreeId(),
                    treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }

        return treeNodeInfo;
    }

}
