CREATE TABLE `common_user` (
   `id` bigInt NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT  '主键',
   `user_id` varchar(256) NOT NULL COMMENT '用户ID',
   `user_name` varchar(256) NOT NULL COMMENT '用户名',
   `password` varchar(1024) NOT NULL COMMENT '密码',
   `nick_name` varchar(256) NOT NULL COMMENT '昵称',
   `phone_number` varchar(256) NULL COMMENT '电话',
   `birthday` timestamp NULL COMMENT '生日',
   `email` timestamp NULL COMMENT '邮箱',
   `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   UNIQUE INDEX `unique_idx_common_user_user_id`(`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';