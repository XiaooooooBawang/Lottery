package com.xbw.lottery.common;

/**
 *
 */
public class Constants {
    public enum ResponseCode {
        SUCCESS(0, "成功"),

        UNKNOWN_ERROR(1, "未知失败"),

        ILLEGAL_PARAMETER(2, "非法参数"),

        INDEX_DUP(3, "主键冲突"),

        UPDATE_FAIL(4,"数据库更新失败"),

        LOSING_DRAW(5, "未中奖"),

        RULE_ERR(6, "量化人群规则执行失败"),
        NOT_CONSUMED_TAKE(7, "未消费活动领取记录"),
        OUT_OF_STOCK(8, "活动无库存"),
        ERR_TOKEN(9, "分布式锁失败")
        ;


        private final Integer code;

        private final String info;

        ResponseCode(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    /**
     * 全局属性
     */
    public static final class Global {
        /** 空节点值 */
        public static final Long TREE_NULL_NODE = 0L;
    }

    /**
     * 决策树节点
     */
    public static final class NodeType{
        /** 树茎 */
        public static final Integer STEM = 1;
        /** 果实 */
        public static final Integer FRUIT = 2;
    }

    /**
     * 规则限定类型
     */
    public static final class RuleLimitType {
        /** 等于 */
        public static final int EQUAL = 1;
        /** 大于 */
        public static final int GT = 2;
        /** 小于 */
        public static final int LT = 3;
        /** 大于&等于 */
        public static final int GE = 4;
        /** 小于&等于 */
        public static final int LE = 5;
        /** 枚举 */
        public static final int ENUM = 6;
    }


    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖
     */
    public enum DrawState {
        FAIL(0, "未中奖"),
        SUCCESS(1, "已中奖"),
        COVER(2, "兜底奖")
        ;

        private final Integer code;

        private final String info;

        DrawState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 抽奖策略模式：
     * 1:单项概率
     * 2:总体概率
     */
    public enum StrategyMode {
        /**
         * 单项概率：如果A奖品抽空后，B和C保持目前中奖概率，用户抽奖扔有20%中为A，因A库存抽空则结果展示为未中奖。为了运营成本，通常这种情况的使用的比较多
         */
        SINGLE(1, "单项概率"),

        /**
         * 总体概率：如果A奖品抽空后，B和C奖品的概率按照 3:5 均分，相当于B奖品中奖概率由 0.3 升为 0.375
         */
        ENTIRETY(2, "总体概率");
        ;

        private final Integer code;

        private final String info;

        StrategyMode(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 发奖状态：0等待发奖、1发奖成功、2发奖失败
     */
    public enum AwardState {
        WAIT(0, "等待发奖"),
        SUCCESS(1, "发奖成功"),
        FAIL(2, "发奖失败")
        ;

        private final Integer code;

        private final String info;

        AwardState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    public enum AwardType {
        DESC(1, "文字描述"),
        REDEEM_CODE(2, "兑换码"),
        COUPON(3, "优惠券"),
        PHYSICAL(4, "实物奖品")
        ;

        private final Integer code;

        private final String info;

        AwardType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     */
    public enum ActivityState {

        /** 1：编辑 */
        EDIT(1, "编辑"),
        /** 2：提审 */
        ARRAIGNMENT(2, "提审"),
        /** 3：撤审 */
        REVOKE(3, "撤审"),
        /** 4：通过 */
        PASS(4, "通过"),
        /** 5：运行(活动中) */
        DOING(5, "运行(活动中)"),
        /** 6：拒绝 */
        REFUSE(6, "拒绝"),
        /** 7：关闭 */
        CLOSE(7, "关闭"),
        /** 8：开启 */
        OPEN(8, "开启");

        private Integer code;
        private String info;

        ActivityState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    /**
     * Ids 生成策略枚举
     */
    public enum Ids {
        /** 雪花算法 */
        SNOWFLAKE,

        /** 日期算法 */
        SHORT_CODE,

        /** 随机算法 */
        RANDOM_NUMERIC
    }

    /**
     * 活动单使用状态 0未使用、1已使用
     */
    public enum TaskState {

        NO_USED(0, "未使用"),
        USED(1, "已使用");

        private Integer code;
        private String info;

        TaskState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 发奖状态 0初始、1完成、2失败
     */
    public enum GrantState{

        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");

        private Integer code;
        private String info;

        GrantState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    /**
     * 消息发送状态（0未发送、1发送成功、2发送失败）
     */
    public enum MQState {
        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");

        private Integer code;
        private String info;

        MQState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 缓存 Key
     */
    public static final class RedisKey {

        // 抽奖活动已使用库存 Key，不用来加锁，只是拿来做库存记录判断的
        private static final String LOTTERY_ACTIVITY_STOCK_USED_COUNT = "lottery_activity_stock_used_count_";

        public static String KEY_LOTTERY_ACTIVITY_STOCK_USED_COUNT(Long activityId) {
            return LOTTERY_ACTIVITY_STOCK_USED_COUNT + activityId;
        }

        // 抽奖活动已使用库存锁 Key，拿来加锁的
        private static final String LOTTERY_ACTIVITY_STOCK_USED_COUNT_TOKEN = "lottery_activity_stock_used_count_token_";

        public static String KEY_LOTTERY_ACTIVITY_STOCK_USED_COUNT_TOKEN(Long activityId, Integer stockUsedCount) {
            return LOTTERY_ACTIVITY_STOCK_USED_COUNT_TOKEN + activityId + "_" + stockUsedCount;
        }

    }

}
