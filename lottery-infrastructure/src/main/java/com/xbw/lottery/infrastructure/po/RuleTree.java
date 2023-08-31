package com.xbw.lottery.infrastructure.po;


import java.io.Serializable;
import java.util.Date;

/**
 * 规则树
 * @TableName rule_tree
 */
public class RuleTree implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 规则树Id
     */
    private String treeName;

    /**
     * 规则树描述
     */
    private String treeDesc;

    /**
     * 规则树根ID
     */
    private Long treeRootNodeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


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
     * 规则树Id
     */
    public String getTreeName() {
        return treeName;
    }

    /**
     * 规则树Id
     */
    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    /**
     * 规则树描述
     */
    public String getTreeDesc() {
        return treeDesc;
    }

    /**
     * 规则树描述
     */
    public void setTreeDesc(String treeDesc) {
        this.treeDesc = treeDesc;
    }

    /**
     * 规则树根ID
     */
    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    /**
     * 规则树根ID
     */
    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}