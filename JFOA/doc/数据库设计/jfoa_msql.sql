SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for calendar
-- ----------------------------
CREATE TABLE `calendar` (
  `Id` int(11) NOT NULL auto_increment COMMENT '日程主键',
  `Subject` varchar(200) default NULL COMMENT '日程标题',
  `Location` varchar(200) default NULL COMMENT '地点',
  `Description` varchar(500) default NULL COMMENT '说明',
  `CalendarType` tinyint(4) default '1' COMMENT '日程类型\r\n            1	个人日程\r\n            2	部门日程',
  `StartTime` datetime NOT NULL COMMENT '开始时间',
  `EndTime` datetime NOT NULL COMMENT '结束时间',
  `IsAllDayEvent` bit(1) NOT NULL default b'0' COMMENT '是否全天日程',
  `HasAttachment` bit(1) NOT NULL default b'0' COMMENT '是否有附件',
  `Category` varchar(400) default NULL COMMENT '分类',
  `InstanceType` tinyint(4) NOT NULL COMMENT '实例类型\r\n            0	Single（一般日程）\r\n            1	Master（循环主日程）\r\n            2	Instance（循环实例日程）\r\n            3	Exception （错误）\r\n            4	MeetingRequest（会议安排）',
  `Attendees` varchar(500) default NULL COMMENT '参与人',
  `AttendeeNames` varchar(500) default NULL COMMENT '参与人姓名',
  `OtherAttendee` varchar(500) default NULL COMMENT '其他参与人',
  `UPAccount` bigint(20) default NULL COMMENT '更新人账号',
  `UPName` varchar(100) default NULL COMMENT '更新人姓名',
  `UPTime` datetime default NULL COMMENT '最后一次更新时间',
  `RecurringRule` varchar(1000) default NULL COMMENT '循环规则',
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL auto_increment,
  `fullname` varchar(50) NOT NULL,
  `gender` varchar(2) NOT NULL default '1',
  `userno` varchar(20) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `phone` varchar(20) default NULL,
  `addr` varchar(100) default NULL,
  `fnamepy` varchar(50) default NULL,
  `worktime` varchar(30) default NULL,
  `nation` varchar(10) default '汉族',
  `idcard` varchar(20) default NULL,
  `hometown` smallint(6) default NULL,
  `diploma` smallint(6) default NULL,
  `wage` smallint(6) default NULL,
  `photo` varchar(50) default NULL,
  `partmentid` bigint(20) NOT NULL,
  `orgid` bigint(20) default NULL,
  `stat` smallint(1) default NULL,
  `remark` varchar(255) default NULL,
  `resume` longtext,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `userno` (`userno`),
  KEY `idx_employee_pwd` (`pwd`),
  KEY `idx_employee_userno` (`userno`),
  KEY `idx_emp_pyname` (`fnamepy`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member
-- ----------------------------
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL auto_increment,
  `fullname` varchar(50) NOT NULL,
  `gender` varchar(2) NOT NULL default '1',
  `pwd` varchar(50) default NULL,
  `birthday` varchar(20) NOT NULL,
  `phone` varchar(20) default NULL,
  `tel` varchar(40) default NULL,
  `addr` varchar(100) default NULL,
  `fnamepy` varchar(50) default NULL,
  `nation` varchar(10) default '汉族',
  `idcard` varchar(20) default NULL,
  `hometown` smallint(6) default NULL,
  `diploma` smallint(6) default NULL,
  `photo` varchar(50) default NULL,
  `level` smallint(6) NOT NULL default '1',
  `amount` decimal(10,2) NOT NULL default '0.00',
  `credits` int(11) default NULL,
  `cardtype` int(11) default NULL,
  `cardno` varchar(50) default NULL,
  `rebate` smallint(6) default '100',
  `stat` smallint(6) default '0',
  `vendingdate` varchar(20) default NULL,
  `validdate` varchar(20) default NULL,
  `regdate` varchar(20) NOT NULL,
  `remark` varchar(250) default NULL,
  `hobby` varchar(250) default NULL,
  `email` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idcard` (`idcard`),
  UNIQUE KEY `cardno` (`cardno`),
  KEY `idx_employee_pwd` (`pwd`),
  KEY `idx_member_fnamepy` (`fnamepy`),
  KEY `idx_member_cardno` (`cardno`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `url` varchar(50) default NULL,
  `type` smallint(6) default NULL,
  `menulevel` smallint(6) default '0',
  `icon` varchar(50) default NULL,
  `ordernum` int(11) default '0',
  `safecode` varchar(20) default NULL,
  `pid` bigint(20) default '0',
  `target` smallint(6) default '0',
  `rel` varchar(30) default NULL,
  `enable` smallint(6) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(30) default NULL,
  `addr` varchar(50) default NULL,
  `picid` bigint(20) default NULL,
  `sortnum` smallint(6) default NULL,
  `remark` varchar(100) default NULL,
  `num` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for partment
-- ----------------------------
CREATE TABLE `partment` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `remark` varchar(250) default NULL,
  `picid` bigint(20) default NULL,
  `orgid` bigint(20) default NULL,
  `pid` bigint(10) default NULL COMMENT '父级部门id',
  `num` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for powermenu
-- ----------------------------
CREATE TABLE `powermenu` (
  `menuid` bigint(20) NOT NULL,
  `objid` bigint(20) NOT NULL,
  `type` smallint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systemparam
-- ----------------------------
CREATE TABLE `systemparam` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `val` varchar(20) NOT NULL,
  `type` smallint(1) default NULL,
  `sortnum` smallint(6) default '0',
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
CREATE TABLE `userrole` (
  `userid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wagepay
-- ----------------------------
CREATE TABLE `wagepay` (
  `ID` bigint(20) NOT NULL auto_increment,
  `EMPID` bigint(20) default NULL,
  `OPERATER` bigint(20) default NULL,
  `AMOUNT` decimal(10,2) default NULL,
  `SDATE` varchar(20) default NULL,
  `EDATE` varchar(20) default NULL,
  `PAYDATE` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '龙影', '男', 'loyin', 'e10adc3949ba59abbe56e057f20f883e', '1984-12-12', '13333333333', 'fdsafdgag', 'lsf', '2000-01-01', '汉族', '362422111112302332', null, null, '10000', '/upload/201210252151010.jpg', '1', '1', '1', 'fdsagfdsgfdg', '范德萨分\r\nsdfsdfa\r\n范德萨\r\nwegfds\r\n过分的是\r\n核辐射');
INSERT INTO `member` VALUES ('1', '龙宝宝', '男', null, '2012-08-30', '13333333333', null, '224324', null, '汉族', '1234343', null, null, '/upload/201210011424028.jpg', '1', '1.99', null, null, '1342543543', '100', '0', '2012-09-30', '2014-10-01', '2012-09-30', 'fafdsaf', 'rewqrewqt', '23432@qq.com');
INSERT INTO `menu` VALUES ('2', '系统管理', null, '0', '0', null, '10', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('3', '人力资源', null, '0', '0', null, '9', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('4', '计划', null, '0', '0', null, '8', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('5', '财务', null, '0', '0', null, '7', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('24', '任务', null, '0', '0', null, '4', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('22', '流程', null, '0', '0', null, '2', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('21', '文档', null, '0', '0', null, '1', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('20', '员工信息', 'employee', '1', '2', null, '1', null, '15', '0', null, '1');
INSERT INTO `menu` VALUES ('10', '功能菜单', 'menu', '1', '2', null, '0', null, '32', '0', null, '1');
INSERT INTO `menu` VALUES ('11', '角色', 'role', '1', '2', null, '1', null, '33', '0', null, '1');
INSERT INTO `menu` VALUES ('12', '部门', 'partment', '1', '2', null, '2', null, '33', '0', null, '1');
INSERT INTO `menu` VALUES ('13', '参数设置', 'systemparam', '1', '2', null, '3', null, '32', '0', null, '1');
INSERT INTO `menu` VALUES ('14', '机构', 'organization', '1', '2', null, '4', null, '33', '0', null, '1');
INSERT INTO `menu` VALUES ('15', '人员管理', null, '1', '1', null, '1', null, '3', '0', null, '1');
INSERT INTO `menu` VALUES ('16', '权限配置', 'power', '1', '2', null, '5', 'employee', '32', '0', null, '1');
INSERT INTO `menu` VALUES ('31', '流程管理', null, '1', '1', null, '1', null, '2', '0', null, '1');
INSERT INTO `menu` VALUES ('18', '员工工资发放', 'wagepay', '1', '1', null, '1', 'wagepay', '5', '0', null, '1');
INSERT INTO `menu` VALUES ('25', '日程', null, '0', '0', null, '5', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('26', '我的日程', null, '1', '1', null, '1', null, '25', '0', null, '1');
INSERT INTO `menu` VALUES ('27', '我的日程管理', 'calender', '1', '2', null, '1', null, '26', '0', null, '1');
INSERT INTO `menu` VALUES ('28', '考勤管理', null, '1', '1', null, '1', null, '3', '0', null, '1');
INSERT INTO `menu` VALUES ('29', '我的考勤', null, '1', '2', null, '1', null, '28', '0', null, '1');
INSERT INTO `menu` VALUES ('30', '考勤统计', null, '1', '2', null, '2', null, '28', '0', null, '1');
INSERT INTO `menu` VALUES ('32', '系统设置', null, '1', '1', null, '0', null, '2', '0', null, '1');
INSERT INTO `menu` VALUES ('33', '组织设置', null, '1', '1', null, '1', null, '2', '0', null, '1');
INSERT INTO `menu` VALUES ('34', '流程分类', 'flowcate', '1', '2', null, '1', null, '31', '0', null, '1');
INSERT INTO `menu` VALUES ('35', '流程部署', 'flowdeploy', '1', '2', null, '0', null, '31', '0', null, '1');
INSERT INTO `menu` VALUES ('36', '我的流程', null, '1', '1', null, '0', null, '22', '0', null, '1');
INSERT INTO `menu` VALUES ('37', '发布流程', null, '1', '1', null, '0', null, '22', '0', null, '1');
INSERT INTO `menu` VALUES ('38', '我的任务', null, '1', '1', null, '1', null, '24', '0', null, '1');
INSERT INTO `menu` VALUES ('39', '发布任务', null, '1', '1', null, '0', null, '24', '1', null, '1');
INSERT INTO `menu` VALUES ('43', '发布文档', null, '1', '2', null, '1', null, '42', '1', null, '1');
INSERT INTO `menu` VALUES ('41', '公共文档', null, '1', '1', null, '0', null, '21', '0', null, '1');
INSERT INTO `menu` VALUES ('42', '我的文档', null, '1', '1', null, '0', null, '21', '0', null, '1');
INSERT INTO `menu` VALUES ('44', '文档管理', null, '1', '2', null, '0', null, '42', '0', null, '1');
INSERT INTO `menu` VALUES ('45', '发布文档', null, '1', '2', null, '1', null, '41', '0', null, '1');
INSERT INTO `menu` VALUES ('46', '文档管理', null, '1', '2', null, '2', null, '41', '0', null, '1');
INSERT INTO `menu` VALUES ('47', '平台设置', 'platform/set', '1', '2', null, '0', null, '32', '0', null, '1');
INSERT INTO `menu` VALUES ('48', '流程管理', null, '1', '1', null, '0', null, '22', '0', null, '1');
INSERT INTO `menu` VALUES ('49', '流程分类', null, '1', '2', null, '0', null, '48', '0', null, '1');
INSERT INTO `menu` VALUES ('50', '流程发布', null, '1', '2', null, '0', null, '48', '0', null, '1');
INSERT INTO `menu` VALUES ('51', '资产', null, '0', '0', null, '1', null, '0', '0', null, '1');
INSERT INTO `menu` VALUES ('52', '会议管理', null, '1', '1', null, '0', null, '25', '0', null, '1');
INSERT INTO `menu` VALUES ('53', '证件', null, '0', '0', null, '0', null, '0', '0', null, '1');
INSERT INTO `organization` VALUES ('1', '肯德基', '北京路', null, '0', '范德萨', 'KFC01');
INSERT INTO `partment` VALUES ('1', '斯蒂芬', '范德萨', '1', '1', null, '0001');
INSERT INTO `partment` VALUES ('2', '范德萨分', '范德萨分', '1', '1', '1', '000102');
INSERT INTO `powermenu` VALUES ('14', '1', '0');
INSERT INTO `powermenu` VALUES ('12', '1', '0');
INSERT INTO `powermenu` VALUES ('11', '1', '0');
INSERT INTO `powermenu` VALUES ('33', '1', '0');
INSERT INTO `powermenu` VALUES ('16', '1', '0');
INSERT INTO `powermenu` VALUES ('13', '1', '0');
INSERT INTO `powermenu` VALUES ('10', '1', '0');
INSERT INTO `powermenu` VALUES ('47', '1', '0');
INSERT INTO `powermenu` VALUES ('32', '1', '0');
INSERT INTO `powermenu` VALUES ('2', '1', '0');
INSERT INTO `powermenu` VALUES ('20', '1', '0');
INSERT INTO `powermenu` VALUES ('15', '1', '0');
INSERT INTO `powermenu` VALUES ('30', '1', '0');
INSERT INTO `powermenu` VALUES ('29', '1', '0');
INSERT INTO `powermenu` VALUES ('28', '1', '0');
INSERT INTO `powermenu` VALUES ('3', '1', '0');
INSERT INTO `powermenu` VALUES ('4', '1', '0');
INSERT INTO `powermenu` VALUES ('18', '1', '0');
INSERT INTO `powermenu` VALUES ('5', '1', '0');
INSERT INTO `powermenu` VALUES ('27', '1', '0');
INSERT INTO `powermenu` VALUES ('26', '1', '0');
INSERT INTO `powermenu` VALUES ('52', '1', '0');
INSERT INTO `powermenu` VALUES ('25', '1', '0');
INSERT INTO `powermenu` VALUES ('38', '1', '0');
INSERT INTO `powermenu` VALUES ('39', '1', '0');
INSERT INTO `powermenu` VALUES ('24', '1', '0');
INSERT INTO `powermenu` VALUES ('36', '1', '0');
INSERT INTO `powermenu` VALUES ('37', '1', '0');
INSERT INTO `powermenu` VALUES ('49', '1', '0');
INSERT INTO `powermenu` VALUES ('50', '1', '0');
INSERT INTO `powermenu` VALUES ('48', '1', '0');
INSERT INTO `powermenu` VALUES ('22', '1', '0');
INSERT INTO `powermenu` VALUES ('46', '1', '0');
INSERT INTO `powermenu` VALUES ('45', '1', '0');
INSERT INTO `powermenu` VALUES ('41', '1', '0');
INSERT INTO `powermenu` VALUES ('43', '1', '0');
INSERT INTO `powermenu` VALUES ('44', '1', '0');
INSERT INTO `powermenu` VALUES ('42', '1', '0');
INSERT INTO `powermenu` VALUES ('21', '1', '0');
INSERT INTO `powermenu` VALUES ('53', '1', '0');
INSERT INTO `powermenu` VALUES ('51', '1', '0');
INSERT INTO `powermenu` VALUES ('31', '1', '0');
INSERT INTO `powermenu` VALUES ('35', '1', '0');
INSERT INTO `powermenu` VALUES ('34', '1', '0');
INSERT INTO `role` VALUES ('3', '员工', null);
INSERT INTO `role` VALUES ('2', '管理员', 'fds');
INSERT INTO `userrole` VALUES ('1', '2');
INSERT INTO `userrole` VALUES ('1', '3');
