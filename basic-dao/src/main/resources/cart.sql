CREATE TABLE `cart` (
    `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_id` int(11) DEFAULT NULL COMMENT '用户id',
    `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
    KEY `idx_cart_user_id` (`user_id`),
    KEY `idx_cart_goods_id` (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';