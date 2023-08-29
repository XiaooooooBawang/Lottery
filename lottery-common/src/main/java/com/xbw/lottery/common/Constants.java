package com.xbw.lottery.common;

/**
 *
 */
public class Constants {
    public enum ResponseCode {
        SUCCESS(0, "成功"),

        UNKNOWN_ERROR(1, "未知失败"),

        ILLEGAL_PARAMETER(2, "非法参数"),

        INDEX_DUP(3, "主键冲突");


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
    public enum DistributionState {
        WAIT(0, "等待发奖"),
        SUCCESS(1, "发奖成功"),
        FAIL(2, "发奖失败")
        ;

        private final Integer code;

        private final String info;

        DistributionState(Integer code, String info) {
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


}
