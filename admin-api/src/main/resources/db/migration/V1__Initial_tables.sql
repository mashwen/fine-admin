SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `p_merchant`;
CREATE TABLE `p_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '商户名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '商户描述',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级id',
  `sort_no` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `created_by` int(11) NOT NULL COMMENT '记录生成人',
  `created_at` datetime NOT NULL COMMENT '创建人id',
  `updated_by` int(11) DEFAULT NULL COMMENT '最后更新人id',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_fokgwg0pkf4hhl71159j3wmqk` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商户表';

DROP TABLE IF EXISTS  `p_messager_config`;
CREATE TABLE `p_messager_config` (
  `config_id` int(11) NOT NULL,
  `merchant_id` varchar(45) DEFAULT NULL,
  `region_id` varchar(45) DEFAULT NULL,
  `access_key_id` varchar(45) DEFAULT NULL,
  `secret` varchar(45) DEFAULT NULL,
  `app_key` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `p_resource`;
CREATE TABLE `p_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `url` varchar(300) NOT NULL COMMENT '菜单资源URL',
  `label` varchar(100) DEFAULT NULL COMMENT '视图 功能标签',
  `remark` varchar(500) DEFAULT '' COMMENT '菜单资源简要描述',
  `parent_id` int(11) DEFAULT '0' COMMENT '父级id',
  `type` int(1) NOT NULL COMMENT '1.菜单资源;2.视图资源;3.功能资源；',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号',
  `created_by` int(11) NOT NULL COMMENT '记录生成人',
  `created_at` datetime NOT NULL COMMENT '创建人id',
  `updated_by` int(11) DEFAULT NULL COMMENT '最后更新人id',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_7fnpvkp4t1h8vianij8rpvmmr` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='权限资源表';

DROP TABLE IF EXISTS  `p_role`;
CREATE TABLE `p_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `name` varchar(30) NOT NULL COMMENT '角色名称',
  `description` text COMMENT '角色描述',
  `remark` varchar(500) DEFAULT '' COMMENT '角色描述',
  `created_by` int(11) NOT NULL COMMENT '记录生成人',
  `created_at` datetime NOT NULL COMMENT '创建人id',
  `updated_by` int(11) DEFAULT NULL COMMENT '最后更新人id',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

DROP TABLE IF EXISTS  `p_role_resource`;
CREATE TABLE `p_role_resource` (
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  KEY `FK_5rl10c4dh5wrkkdosebh0ghwd` (`resource_id`),
  KEY `FK_8moampu5rc978884rqav0shh4` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `p_store`;
CREATE TABLE `p_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '门店名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '门店描述',
  `merchant_id` int(11) NOT NULL DEFAULT '0' COMMENT '商户id',
  `sort_no` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `created_by` int(11) NOT NULL COMMENT '记录生成人',
  `created_at` datetime NOT NULL COMMENT '创建人id',
  `updated_by` int(11) DEFAULT NULL COMMENT '最后更新人id',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_fokgwg0pkf4hhl71159j3wmqk` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='门店表';

DROP TABLE IF EXISTS  `p_user`;
CREATE TABLE `p_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `department_id` int(11) NOT NULL COMMENT '外键，所属部门Id',
  `username` varchar(30) NOT NULL COMMENT '登录用户名',
  `password` varchar(150) NOT NULL COMMENT '登录密码，salt md5 之后的值',
  `fullname` varchar(30) NOT NULL COMMENT '姓名',
  `mobile` varchar(100) NOT NULL COMMENT '联系方式',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别：0男1女',
  `position` varchar(20) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否管理员：1是0否',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1 正常 2冻结 3 删除',
  `created_by` int(11) NOT NULL DEFAULT '1' COMMENT '记录生成人',
  `created_at` datetime NOT NULL COMMENT '记录生成时间',
  `updated_by` int(11) DEFAULT NULL COMMENT '最后更新人',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  `merchant_id` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  `default_store` int(11) DEFAULT '0',
  `default_qrcode` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_mobile` (`mobile`),
  KEY `fk_p_user_p_department1` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

DROP TABLE IF EXISTS  `p_user_info`;
CREATE TABLE `p_user_info` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `user_number` varchar(25) DEFAULT NULL COMMENT '员工编号',
  `entry_time` varchar(255) DEFAULT NULL COMMENT '入职时间',
  `user_type` int(11) DEFAULT NULL COMMENT '员工类型 0 正式 1临时 2实习',
  `address` varchar(100) DEFAULT NULL COMMENT '住址',
  `created_by` int(11) NOT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `updated_at` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `p_user_level`;
CREATE TABLE `p_user_level` (
  `user_id` int(11) NOT NULL,
  `level` int(11) NOT NULL COMMENT '等级：1总部，2区域、3城市、4门店',
  `region_Id` varchar(400) DEFAULT NULL COMMENT '区域id，多个区域用逗号分隔',
  `city_id` varchar(1024) DEFAULT NULL COMMENT '城市id，多个区域用逗号分隔',
  `store_id` varchar(1024) DEFAULT NULL COMMENT '门店id，多个区域用逗号分隔',
  `extra` varchar(1000) DEFAULT NULL COMMENT '未来扩展\\n',
  `created_by` int(11) DEFAULT NULL COMMENT '记录生成人',
  `created_at` datetime DEFAULT NULL COMMENT '记录生成时间',
  `updated_by` int(11) DEFAULT NULL COMMENT '最后更新人',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  `merchant_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户等级表';

DROP TABLE IF EXISTS  `p_user_level_type`;
CREATE TABLE `p_user_level_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(20) NOT NULL,
  `extension1` int(11) DEFAULT NULL,
  `extension2` varchar(20) DEFAULT NULL,
  `extension3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户等级基础数据';

DROP TABLE IF EXISTS  `p_user_resource`;
CREATE TABLE `p_user_resource` (
  `sub_id` int(11) NOT NULL COMMENT '员工id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  KEY `fk_p_user_action_p_resource1` (`resource_id`),
  KEY `fk_p_user_action_p_user1` (`sub_id`),
  CONSTRAINT `FK_35au3mmrhe5ekq64k71ac033r` FOREIGN KEY (`resource_id`) REFERENCES `p_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `p_user_role`;
CREATE TABLE `p_user_role` (
  `user_id` int(11) NOT NULL COMMENT '员工ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

DROP TABLE IF EXISTS  `p_user_store`;
CREATE TABLE `p_user_store` (
  `user_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `merchant_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `p_user_wechat`;
CREATE TABLE `p_user_wechat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `union_id` varchar(45) DEFAULT NULL,
  `public_app_id` varchar(45) DEFAULT NULL,
  `public_open_id` varchar(45) DEFAULT NULL,
  `mini_app_id` varchar(45) DEFAULT NULL,
  `mini_open_id` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

