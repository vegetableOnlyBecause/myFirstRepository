CREATE TABLE `type` (
    `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `name` varchar(30) DEFAULT NULL COMMENT '分类名',
    `number` int(11) DEFAULT '0' COMMENT '该分类下的商品数量',
    `status` tinyint(10) DEFAULT '0' COMMENT '分类状态，0正常，1暂用',
    `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '创建时间',
    UNIQUE INDEX `unique_idx_type_name`(`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';