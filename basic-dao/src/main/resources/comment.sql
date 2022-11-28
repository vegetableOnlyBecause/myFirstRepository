CREATE TABLE `comments` (
    `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
    `user_id` int(11) DEFAULT NULL COMMENT '用户id',
    `user_name` varchar(256) NOT NULL COMMENT '用户名',
    `comments` varchar(45) DEFAULT NULL COMMENT '内容',
    `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '创建时间',
    KEY `idx_comments_goods_id` (`goods_id`),
    KEY `idx_comments_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';