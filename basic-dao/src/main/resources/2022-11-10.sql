CREATE TABLE `commodity` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `commodity_id` varchar(256) NOT NULL COMMENT '商品ID',
     `commodity_name` varchar(256) NOT NULL COMMENT '商品名称',
     `category_id` varchar(256) NOT NULL COMMENT '商品类别ID',
     `original_price` decimal NOT NULL COMMENT '原始价格(单位为分)',
     `sale_price` decimal NOT NULL COMMENT '销售价格(单位为分)',
     `detail` varchar(512) NULL DEFAULT NULL COMMENT '描述',
     `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT NOW() COMMENT '更新时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `unique_idx_commodity_commodity_id` (`commodity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

CREATE TABLE `category` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_id` varchar(256) NOT NULL COMMENT '类别ID',
    `parent_category_id` varchar(256) NULL COMMENT '父类别ID',
    `category_name` varchar(256) NOT NULL COMMENT '商品名称',
    `detail` varchar(512) NULL DEFAULT NULL COMMENT '描述',
    `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT NOW() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_idx_category_category_id` (`category_id`) USING BTREE,
    INDEX `idx_category_parent_category_id` (`parent_category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类目信息表';