CREATE TABLE `common_user` (
   `id` int(11) NOT NULL PRIMARY KEY COMMENT '用户id,主键',
   `user_name` varchar(256) NOT NULL COMMENT '用户名',
   `password` varchar(32) NOT NULL COMMENT '密码',
   `phone` varchar(11) NULL COMMENT '登录使用的手机号',
   `qq` varchar(12) DEFAULT NULL COMMENT '即时通讯',
   `goods_num` int(11) DEFAULT '0' COMMENT '发布过的物品数量',
   `power` int(10) DEFAULT '0' COMMENT '权限值，0：普通用户，1：管理员',
   `coin` float DEFAULT '0' COMMENT '账户金额',
   `credit` float DEFAULT '5',
   `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '注册时间',
   `update_time` timestamp NOT NULL DEFAULT NOW() COMMENT '更新时间',
   UNIQUE INDEX `unique_idx_common_user_user_name`(`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';