package com.xbw.lottery.infrastructure.po;

import java.io.Serializable;

/**
 * 规则树节点连线
 * @TableName rule_tree_node_line
 */
public class RuleTreeNodeLine implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 规则树ID
     */
    private Long treeId;

    /**
     * 节点From
     */
    private Long nodeIdFrom;

    /**
     * 节点To
     */
    private Long nodeIdTo;

    /**
     * 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实
     */
    private Integer ruleLimitType;

    /**
     * 限定值
     */
    private String ruleLimitValue;

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
     * 节点From
     */
    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    /**
     * 节点From
     */
    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    /**
     * 节点To
     */
    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    /**
     * 节点To
     */
    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    /**
     * 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实
     */
    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    /**
     * 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实
     */
    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    /**
     * 限定值
     */
    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    /**
     * 限定值
     */
    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }
}