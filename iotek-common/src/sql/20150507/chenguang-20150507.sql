CREATE TABLE `tb_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `intro` varchar(1000) DEFAULT NULL,
  `sort_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合作企业表';


CREATE TABLE `tb_company_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `intro` varchar(1000) DEFAULT NULL,
  `sort_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业部门表';

CREATE TABLE `tb_company_import_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empno` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `import_status` int(1) DEFAULT NULL,
  `creater_user` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业学员导入日志表';

CREATE TABLE `tb_class_manage_account_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `manage_account_id` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级教师关系表';

CREATE TABLE `tb_department_manage_account_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_department_id` int(11) DEFAULT NULL,
  `manage_account_id` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业部门教师关系表';

CREATE TABLE `tb_school_import_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `import_status` int(1) DEFAULT NULL,
  `creater_user` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='院校学员导入日志表';

CREATE TABLE `tb_user_company_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `company_department_id` int(11) DEFAULT NULL,
  `creater_user` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户企业关系表，存储前台用户和公司部门之间的关系';