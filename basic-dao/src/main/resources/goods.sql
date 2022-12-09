CREATE TABLE `goods` (
                         `id` int(11) NOT NULL PRIMARY KEY COMMENT '商品主键',
                         `name` varchar(50) NOT NULL COMMENT '商品名称',
                         `type_id` int(11) DEFAULT NULL COMMENT '商品分类，外键',
                         `user_id` int(11) NOT NULL COMMENT '用户外键',
                         `price` float(11,2) NOT NULL COMMENT '出售价格',
				`num` int(11) NOT NULL DEFAULT 1 COMMENT '库存',
        `img_url` varchar(256) DEFAULT NULL COMMENT '图片地址',
        `real_price` float(11,2) DEFAULT NULL COMMENT '真实价格',
        `polish_time` timestamp NULL COMMENT '擦亮时间，按该时间进行查询，精确到时分秒',
        `end_time` timestamp NOT NULL DEFAULT NOW() COMMENT '下架时间',
        `description` varchar(4096) DEFAULT NULL COMMENT '详细信息',
        `comment_num` int(11) DEFAULT NULL,
        `status` int(11) DEFAULT '1',
        `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '发布时间',
        `update_time` timestamp NOT NULL DEFAULT NOW() COMMENT '更新时间',
        KEY `idx_goods_type_id` (`type_id`) USING BTREE,
        KEY `idx_goods_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';