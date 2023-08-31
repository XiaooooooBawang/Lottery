package com.xbw.lottery.infrastructure.po;

import java.io.Serializable;

/**
 * 规则树节点
 * @TableName rule_tree_node
 */
public class RuleTreeNode implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 规则树ID
     */
    private Long treeId;

    /**
     * 节点类型；1子叶、2果实
     */
    private Integer nodeType;

    /**
     * 节点值[nodeType=2]；果实值
     */
    private String nodeValue;

    /**
     * 规则Key
     */
    private String ruleKey;

    /**
     * 规则描述
     */
    private String ruleDesc;

    /**
     * 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 规则树ID
     */
    public Long getTreeId() {
        return treeId;
    }

    /**
     * 规则树ID
     */
    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    /**
     * 节点类型；1子叶、2果实
     */
    public Integer getNodeType() {
        return nodeType;
    }

    /**
     * 节点类型；1子叶、2果实
     */
    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 节点值[nodeType=2]；果实值
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * 节点值[nodeType=2]；果实值
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * 规则Key
     */
    public String getRuleKey() {
        return ruleKey;
    }

    /**
     * 规则Key
     */
    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    /**
     * 规则描述
     */
    public String getRuleDesc() {
        return ruleDesc;
    }

    /**
     * 规则描述
     */
    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}