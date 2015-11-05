CREATE TABLE `tb_manage_operation_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manage_account_id` int(11) NOT NULL COMMENT '后台操作人Id',
  `url` varchar(200) DEFAULT NULL COMMENT 'action路径',
  `params` varchar(1000) DEFAULT NULL COMMENT '访问参数集',
  `type` int(4) DEFAULT NULL COMMENT '操作类型\r\n1,增加\r\n2,删除\r\n3,修改\r\n4,查询\r\n5,授权\r\n6,导入导出',
  `module` varchar(200) DEFAULT NULL COMMENT '操作菜单目录',
  `detail` varchar(500) DEFAULT NULL COMMENT '操作详情，操作名称',
  `result` tinyint(4) DEFAULT NULL COMMENT '操作情况，1成功，0失败',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tb_operation_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(200) DEFAULT NULL COMMENT '业务模块,菜单目录',
  `type` int(4) DEFAULT NULL COMMENT '1,增加\r\n2,删除\r\n3,修改\r\n4,查询\r\n5,授权\r\n,6导入导出',
  `url` varchar(200) DEFAULT NULL COMMENT '访问action',
  `detail` varchar(200) DEFAULT NULL COMMENT '操作详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作资源表';



INSERT INTO `tb_operation_resource` VALUES (1, '渠道管理-院校账号管理&渠道管理-三级分类', 4, '/teacher/showClassDto', '查询班级');
INSERT INTO `tb_operation_resource` VALUES (2, '渠道管理-院校账号管理', 1, '/teacher/correlationClass', '院校分配教师');
INSERT INTO `tb_operation_resource` VALUES (3, '渠道管理-企业账号管理&渠道管理-二级分类', 4, '/teacher/showCompanyDeDto', '查询企业部门');
INSERT INTO `tb_operation_resource` VALUES (4, '渠道管理-企业账号管理', 1, '/teacher/correlationCompanyDe', '企业分配教师');
INSERT INTO `tb_operation_resource` VALUES (5, '渠道管理-一级分类', 1, '/teacher/saveSchool', '创建学校');
INSERT INTO `tb_operation_resource` VALUES (6, '渠道管理-一级分类', 1, '/teacher/saveCompany', '创建公司');
INSERT INTO `tb_operation_resource` VALUES (7, '渠道管理-一级分类', 3, '/teacher/updateSchool', '编辑学校');
INSERT INTO `tb_operation_resource` VALUES (8, '渠道管理-一级分类', 3, '/teacher/updateCompany', '编辑公司');
INSERT INTO `tb_operation_resource` VALUES (9, '渠道管理-一级分类', 2, '/teacher/deleteSchool', '删除学校');
INSERT INTO `tb_operation_resource` VALUES (10, '渠道管理-一级分类', 2, '/teacher/deleteCompany', '删除公司');
INSERT INTO `tb_operation_resource` VALUES (11, '渠道管理-二级分类', 4, '/teacher/showSchoolDeDto', '查询学校系');
INSERT INTO `tb_operation_resource` VALUES (12, '渠道管理-二级分类', 1, '/teacher/saveDepartment', '新建学校系');
INSERT INTO `tb_operation_resource` VALUES (13, '渠道管理-二级分类', 1, '/teacher/saveCompanyDe', '新建企业部门');
INSERT INTO `tb_operation_resource` VALUES (14, '渠道管理-二级分类', 3, '/teacher/updateDepartment', '编辑学校系');
INSERT INTO `tb_operation_resource` VALUES (15, '渠道管理-二级分类', 3, '/teacher/updateComapnyDe', '编辑企业部门');
INSERT INTO `tb_operation_resource` VALUES (16, '渠道管理-二级分类', 2, '/teacher/deleteDepartment', '删除学校系');
INSERT INTO `tb_operation_resource` VALUES (17, '渠道管理-二级分类', 2, '/teacher/deleteCompanyDe', '删除企业部门');
INSERT INTO `tb_operation_resource` VALUES (18, '渠道管理-三级分类', 1, '/teacher/createSchoolClass', '创建班级');
INSERT INTO `tb_operation_resource` VALUES (19, '渠道管理-三级分类', 3, '/teacher/updateClass', '编辑班级');
INSERT INTO `tb_operation_resource` VALUES (20, '渠道管理-三级分类', 2, '/teacher/deleteClass', '删除班级');
INSERT INTO `tb_operation_resource` VALUES (21, '系统管理-权限管理', 1, '/systemManage/addRole', '创建新角色');
INSERT INTO `tb_operation_resource` VALUES (22, '系统管理-权限管理', 3, '/systemManage/updateRole', '编辑角色');
INSERT INTO `tb_operation_resource` VALUES (23, '系统管理-权限管理', 4, '/manage/systemManage', '进入权限分配页面');
INSERT INTO `tb_operation_resource` VALUES (24, '系统管理-权限管理', 5, '/systemManage/saveRoleResource', '保存角色分配权限');
INSERT INTO `tb_operation_resource` VALUES (25, '系统管理-权限管理', 2, '/systemManage/deleteRole', '删除角色权限');
INSERT INTO `tb_operation_resource` VALUES (26, '系统管理-账号管理', 1, '/systemManage/toAddAccount', '创建后台新账户');
INSERT INTO `tb_operation_resource` VALUES (27, '系统管理-账号管理', 3, '/systemManage/toUpdateAccount', '编辑后台账户');
INSERT INTO `tb_operation_resource` VALUES (28, '系统管理-账号管理', 2, '/systemManage/deleteAccount', '删除后台账户');
INSERT INTO `tb_operation_resource` VALUES (29, '用户管理-批量创建渠道用户', 1, '/user/createBatch', '批量创建渠道用户');
INSERT INTO `tb_operation_resource` VALUES (30, '用户管理-批量创建校园用户', 4, '/user/createBatchByExcel', '根据导入excel中的用户生成预览');
INSERT INTO `tb_operation_resource` VALUES (31, '用户管理-批量创建校园用户', 1, '/user/saveAndExportAccountInfo', '生成用户并导出');
INSERT INTO `tb_operation_resource` VALUES (32, '作业练习列表-作业批阅', 4, '/review/index', '进入作业列表页');
INSERT INTO `tb_operation_resource` VALUES (33, '作业练习列表-作业批阅', 4, '/review/reading', '进入批阅详情页');
INSERT INTO `tb_operation_resource` VALUES (34, '作业练习列表-作业批阅', 1, '/review/marking', '批阅操作');
INSERT INTO `tb_operation_resource` VALUES (35, '作业练习列表-作业批阅', 4, '/review/genInfo', '列表页查询操作');
INSERT INTO `tb_operation_resource` VALUES (36, '促销管理-体验卡', 4, '/expcard/index', '进入体验卡首页（体验卡列表）');
INSERT INTO `tb_operation_resource` VALUES (37, '促销管理-体验卡', 4, 'expcard/show', '进入体验卡详情页');
INSERT INTO `tb_operation_resource` VALUES (38, '促销管理-体验卡', 4, '/expcard/checkIndex', '进入创建体验卡页面');
INSERT INTO `tb_operation_resource` VALUES (39, '促销管理-体验卡', 4, '/expcard/genCourse', '获取可以被体验的课程');
INSERT INTO `tb_operation_resource` VALUES (40, '促销管理-体验卡', 1, '/expcard/create', '体验卡创建/修改操作');
INSERT INTO `tb_operation_resource` VALUES (41, '促销管理-体验卡', 4, '/expcard/editInfo', '进入体验卡编辑页面');
INSERT INTO `tb_operation_resource` VALUES (42, '促销管理-体验卡', 4, '/expcard/search', '体验卡详情页查询激活码操作');
INSERT INTO `tb_operation_resource` VALUES (43, '促销管理-体验卡', 4, '/expcard/exportExcelAll', '体验卡详情页导出全部数据');
INSERT INTO `tb_operation_resource` VALUES (44, '促销管理-体验卡', 4, '/expcard/exportCurrentPageExcel', '体验卡详情页导出本页数据');
INSERT INTO `tb_operation_resource` VALUES (45, '社区管理-问答列表', 4, '/communityManage/manageQuestionList', '查询');
INSERT INTO `tb_operation_resource` VALUES (46, '社区管理-问答列表', 4, '/communityAnswerManage/showAnswer', '查看问题详情');
INSERT INTO `tb_operation_resource` VALUES (47, '社区管理-问答列表', 4, '/communityAnswerManage/editQuestion', '进入问题编辑页');
INSERT INTO `tb_operation_resource` VALUES (48, '社区管理-问答列表', 3, 'communityAnswerManage/updateQuestion', '修改问题');
INSERT INTO `tb_operation_resource` VALUES (49, '社区管理-问答列表', 3, '/communityManage/pushTopQuestion', '把问题设为首页1，置顶2，精华3');
INSERT INTO `tb_operation_resource` VALUES (50, '社区管理-问答列表', 2, '/communityManage/deleteSubjectById', '删除问题');
INSERT INTO `tb_operation_resource` VALUES (51, '社区管理-问答列表', 3, '/communityManage/accuseQuestion', '把被举报的问题设置为已处理');
INSERT INTO `tb_operation_resource` VALUES (52, '用户管理-用户信息', 4, '/user/search', '用户查询');
INSERT INTO `tb_operation_resource` VALUES (53, '用户管理-用户信息', 1, '/userOperation/createUser', '创建用户');
INSERT INTO `tb_operation_resource` VALUES (54, '用户管理-用户信息', 6, '/user/exportCurrentPageExcel', '导出当前页用户');
INSERT INTO `tb_operation_resource` VALUES (55, '用户管理-用户信息', 6, '/user/exportExcelAll', '导出全部用户');
INSERT INTO `tb_operation_resource` VALUES (56, '用户管理-用户信息', 1, '/userOperation/sendCourse', '配送课程');
INSERT INTO `tb_operation_resource` VALUES (57, '用户管理-用户信息', 4, '/userOperation/userInfoDetail', '查看用户详情');
INSERT INTO `tb_operation_resource` VALUES (58, '用户管理-用户信息', 3, '/userOperation/updateUserInfo', '编辑用户');
INSERT INTO `tb_operation_resource` VALUES (59, '用户管理-用户信息', 2, '/userOperation/deleteUser', '禁用用户');
INSERT INTO `tb_operation_resource` VALUES (60, '用户管理-用户信息', 3, '/userOperation/updatePassword', '修改密码');
INSERT INTO `tb_operation_resource` VALUES (61, '用户管理-用户信息', 3, '/userOperation/updateOne', '更新用户');
INSERT INTO `tb_operation_resource` VALUES (62, '用户管理-用户信息', 1, '/userOperation/createOne', '创建一个用户');
INSERT INTO `tb_operation_resource` VALUES (63, '资源管理-资源列表', 4, '/file/show', '查看文件');
INSERT INTO `tb_operation_resource` VALUES (64, '资源管理-资源列表', 6, '/fileupload/uploadFile', '上传文件');
INSERT INTO `tb_operation_resource` VALUES (65, '资源管理-资源列表', 2, '/fileupload/logicDeleteFile', '删除文件');
INSERT INTO `tb_operation_resource` VALUES (66, 'marvell活动管理-活动申请查询', 4, '/applyManager/show', '申请人查看');
INSERT INTO `tb_operation_resource` VALUES (67, 'marvell活动管理-活动申请查询', 6, '/applyManager/exportExcelAll', '导出团队');
INSERT INTO `tb_operation_resource` VALUES (68, 'marvell活动管理-活动申请查询', 6, '/applyManager/exportExcelTeamAll', '导出全部申请人');
INSERT INTO `tb_operation_resource` VALUES (69, 'marvell活动管理-活动申请查询', 4, '/applyManager/teamDetail', '查看团队详情');
INSERT INTO `tb_operation_resource` VALUES (70, 'marvell活动管理-活动申请查询', 4, '/applyManager/teamMemberDetail', '查看队员详情');
INSERT INTO `tb_operation_resource` VALUES (71, '认证管理-理论考试', 4, '/certManage/paperIndex', '查询');
INSERT INTO `tb_operation_resource` VALUES (72, '认证管理-理论考试', 4, '/certManage/paperDetail', '查看理论考试详情');
INSERT INTO `tb_operation_resource` VALUES (73, '认证管理-理论考试', 3, '/certManage/giveScore', '批阅理论考试');
INSERT INTO `tb_operation_resource` VALUES (74, '认证管理-项目考试', 4, '/ certManage/projectIndex', '查询');
INSERT INTO `tb_operation_resource` VALUES (75, '认证管理-项目考试', 4, '/certManage/projectDetail', '查看项目考试详情');
INSERT INTO `tb_operation_resource` VALUES (76, '认证管理-项目考试', 3, '/certManage/updateProjectLog', '批阅项目考试');
INSERT INTO `tb_operation_resource` VALUES (77, '用户管理-商品有效期延长', 4, '/userGoods/delay', '查询列表');
INSERT INTO `tb_operation_resource` VALUES (78, '用户管理-商品有效期延长', 5, '/userGoods/saveGoods', '延长有效期');
INSERT INTO `tb_operation_resource` VALUES (79, '用户管理-商品有效期延长', 3, '/userGoods/closeLearn', '关闭学习');
INSERT INTO `tb_operation_resource` VALUES (80, '讲义编译管理', 4, '/course/index', '查看大纲');
INSERT INTO `tb_operation_resource` VALUES (81, '讲义编译管理', 3, '/course/saveCompile', '保存/修改在线编译');
INSERT INTO `tb_operation_resource` VALUES (82, '讲义编译管理', 1, '/course/add', '增加知识点');
INSERT INTO `tb_operation_resource` VALUES (83, '讲义编译管理', 3, '/course/update', '修改知识点');
INSERT INTO `tb_operation_resource` VALUES (84, '作业练习管理-作业练习列表', 4, '/ homework/index', '查看列表');
INSERT INTO `tb_operation_resource` VALUES (85, '作业练习管理-作业练习列表', 3, '/practice/saveRel', '关联练习题');
INSERT INTO `tb_operation_resource` VALUES (86, '作业练习管理-作业练习列表', 3, '/homework/saveRel', '关联作业题');
INSERT INTO `tb_operation_resource` VALUES (87, '作业练习管理-作业练习列表', 3, '/practice/deleteRel', '解绑练习题');
INSERT INTO `tb_operation_resource` VALUES (88, '作业练习管理-作业练习列表', 4, '/homework/deleteRel', '解绑作业题');
INSERT INTO `tb_operation_resource` VALUES (89, '作业练习管理-作业练习列表', 4, '/practice/practiceSet', '查询练习关联数据');
INSERT INTO `tb_operation_resource` VALUES (90, '作业练习管理-作业练习列表', 4, '/homework/homeworkSet', '查询作业关联数据');
INSERT INTO `tb_operation_resource` VALUES (91, '认证管理-身份认证', 1, '/certPersonManage/handleCerPerson', '身份审核');
INSERT INTO `tb_operation_resource` VALUES (92, '认证管理-模拟面试', 1, '/interviewManage/handleInterview', '安排面试');
INSERT INTO `tb_operation_resource` VALUES (93, '试题管理-试题列表', 4, '/topicManage/search', '查询试题列表');
INSERT INTO `tb_operation_resource` VALUES (94, '试题管理-试题列表', 1, '/topicManage/addChoiceTopic', '新增试题');
INSERT INTO `tb_operation_resource` VALUES (95, '试题管理-试题列表', 2, '/topicManage/deleteTopic', '删除试题');
INSERT INTO `tb_operation_resource` VALUES (96, '试题管理-试题列表', 2, '/topicManage/deleteTopics', '批量删除试题');
INSERT INTO `tb_operation_resource` VALUES (97, '试题管理-试题列表', 3, '/topicManage/modifyTopic', '编辑试题');
INSERT INTO `tb_operation_resource` VALUES (98, '试题管理-试题列表', 3, '/topicManage/modifyState', '修改试题状态');
INSERT INTO `tb_operation_resource` VALUES (99, '试题管理-试题列表', 6, '/management/importTopic', '批量导入试题');
