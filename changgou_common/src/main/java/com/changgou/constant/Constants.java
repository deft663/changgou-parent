package com.changgou.constant;

public class Constants {
    /**
     * 规格启用状态
     */
    public static enum SpecStatus {
        ENABLE_SPEC(0, "启用规格"), DISABLED_SPEC(1, "不启用规格");

        private SpecStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Sku状态
     * 商品状态 1-正常，2-下架，3-删除',
     */
    public static enum SkuDeleteStatus {
        NROMAL(1, "正常"), NO_MARKET(2, "下架"),DELETE(3,"删除");

        private SkuDeleteStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
    /**
     * Spu删除状态
     */
    public static enum SpuDeleteStatus {
        NO_DELEET(0, "未删除"), DELETEED(1, "已删除");

        private SpuDeleteStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 商品上架状态
     */
    public static enum GoodsMarketStatus {
        MARKET(0, "上架"), NO_MARKET(1, "下架");

        private GoodsMarketStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 商品审核状态
     */
    public static enum GoodsCheckedStatus {
        WAIT(0, "待审核"), PASS(1, "审核通过"), NOTPASS(2, "审核不通过");

        private GoodsCheckedStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 订单状态
     */
    public static enum OrderStats {

        DELETE(0, "删除"), RESERVE(1, "订单预定"), CONFIRM(2, "订单确认"), COMPLETE(3, "订单完成"), CLOSE(4, "订单关闭");

        private OrderStats(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

    }
}
