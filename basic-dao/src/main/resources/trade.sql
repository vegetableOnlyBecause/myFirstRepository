CREATE TABLE `trade` (
     `id` int(11) NOT NULL PRIMARY KEY COMMENT '主键',
     `saler_id` int(11) NOT NULL COMMENT '卖家id',
     `buyer_id` int(11) NOT NULL COMMENT '买家id',
     `goods_id` int(11) NOT NULL COMMENT '商品id',
     `status` int(4) NOT NULL DEFAULT '2' COMMENT '交易状态\n1：未付款 2：未发货 3：正在路上 4：已确认收货 5：已评价',
     `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '交易创建时间',
     UNIQUE KEY `id_UNIQUE` (`id`),
     KEY `idx_trade_buyer_id` (`buyer_id`),
     KEY `idx_trade_saler_id` (`saler_id`),
     KEY `idx_trade_goods_id` (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品交易';