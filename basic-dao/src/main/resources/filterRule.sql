CREATE TABLE `filter_rule` (
   `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
   `rule_id` varchar(128) NOT NULL COMMENT '规则Id',
   `model_type` varchar(64) NOT NULL COMMENT '模块类型',
   `rule` varchar(2048) NOT NULL COMMENT '规则内容',
   `enable` int(1) NOT NULL DEFAULT '1' COMMENT '状态:1-启用,0-关闭',
   `create_time` timestamp NOT NULL DEFAULT NOW() COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='过滤规则表(指单类具体规则)';