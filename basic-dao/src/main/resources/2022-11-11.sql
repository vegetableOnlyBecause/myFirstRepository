CREATE TABLE `family_order` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `order_id` varchar(256) NOT NULL COMMENT '订单ID',
    `user_id` varchar(256) NOT NULL COMMENT '用户ID',
    `goods_id` varchar(256) NULL COMMENT '货品ID',
    `goods_num` int(11) NOT NULL DEFAULT '1' COMMENT '货品数量',
    `total_amount` decimal NOT NULL COMMENT '原始价格总价(单位为分)',
    `pay_amount` decimal NOT NULL COMMENT '销售价格总价(单位为分)',
    `order_status` varchar(10) NOT NULL DEFAULT 'INIT' COMMENT '订单状态',
    `order_type` varchar(10) NOT NULL DEFAULT 'COMMON' COMMENT '订单类型',
    `pay_time` timestamp NULL COMMENT '付款时间',
    `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `ext` varchar(1024) NULL COMMENT 'ext信息',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_family_order_order_id` (`order_id`) USING BTREE,
    INDEX `idx_family_order_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';