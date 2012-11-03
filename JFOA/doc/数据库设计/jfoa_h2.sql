CREATE TABLE calendar (
  Id int(11) NOT NULL auto_increment,-- '日程主键',
  Subject varchar(200) default NULL,-- '日程标题',
  Location varchar(200) default NULL,-- '地点',
  Description varchar(500) default NULL,-- '说明',
  CalendarType tinyint(4) default '1',-- '日程类型\r\n            1	个人日程\r\n            2	部门日程',
  StartTime datetime NOT NULL,-- '开始时间',
  EndTime datetime NOT NULL,-- '结束时间',
  IsAllDayEvent bit(1) NOT NULL default 0,-- '是否全天日程',
  HasAttachment bit(1) NOT NULL default 0,-- '是否有附件',
  Category varchar(400) default NULL,-- '分类',
  InstanceType tinyint(4) NOT NULL,-- '实例类型\r\n            0	Single（一般日程）\r\n            1	Master（循环主日程）\r\n            2	Instance（循环实例日程）\r\n            3	Exception （错误）\r\n            4	MeetingRequest（会议安排）',
  Attendees varchar(500) default NULL,-- '参与人',
  AttendeeNames varchar(500) default NULL,-- '参与人姓名',
  OtherAttendee varchar(500) default NULL,-- '其他参与人',
  UPAccount bigint(20) default NULL,-- '更新人账号',
  UPName varchar(100) default NULL,-- '更新人姓名',
  UPTime datetime default NULL,-- '最后一次更新时间',
  RecurringRule varchar(1000) default NULL,-- '循环规则',
  PRIMARY KEY  (Id)
);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
CREATE TABLE employee (
  id bigint(20) NOT NULL auto_increment,
  fullname varchar(50) NOT NULL,
  gender varchar(2) NOT NULL default '1',
  userno varchar(20) NOT NULL,
  pwd varchar(50) NOT NULL,
  birthday varchar(20) NOT NULL,
  phone varchar(20) default NULL,
  addr varchar(100) default NULL,
  fnamepy varchar(50) default NULL,
  worktime varchar(30) default NULL,
  nation varchar(10) default '汉族',
  idcard varchar(20) default NULL,
  hometown smallint(6) default NULL,
  diploma smallint(6) default NULL,
  wage smallint(6) default NULL,
  photo varchar(50) default NULL,
  partmentid bigint(20) NOT NULL,
  orgid bigint(20) default NULL,
  stat smallint(1) default NULL,
  remark varchar(255) default NULL,
  resume longtext,
  PRIMARY KEY  (id),
  UNIQUE KEY userno (userno)
);
-- ----------------------------
-- Table structure for menu
-- ----------------------------
CREATE TABLE menu (
  id bigint(20) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  url varchar(50) default NULL,
  type smallint(6) default NULL,
  menulevel smallint(6) default '0',
  icon varchar(50) default NULL,
  ordernum int(11) default '0',
  safecode varchar(20) default NULL,
  pid bigint(20) default '0',
  target smallint(6) default '0',
  rel varchar(30) default NULL,
  enable smallint(6) default '1',
  PRIMARY KEY  (id)
);

-- ----------------------------
-- Table structure for organization
-- ----------------------------
CREATE TABLE organization (
  id bigint(20) NOT NULL auto_increment,
  name varchar(30) default NULL,
  addr varchar(50) default NULL,
  picid bigint(20) default NULL,
  sortnum smallint(6) default NULL,
  remark varchar(100) default NULL,
  num varchar(20) default NULL,
  PRIMARY KEY  (id)
);

-- ----------------------------
-- Table structure for partment
-- ----------------------------
CREATE TABLE partment (
  id bigint(20) NOT NULL auto_increment,
  name varchar(50) default NULL,
  remark varchar(250) default NULL,
  picid bigint(20) default NULL,
  orgid bigint(20) default NULL,
  pid bigint(10) default NULL,-- '父级部门id',
  num varchar(20) default NULL,
  PRIMARY KEY  (id)
);

-- ----------------------------
-- Table structure for powermenu
-- ----------------------------
CREATE TABLE powermenu (
  menuid bigint(20) NOT NULL,
  objid bigint(20) NOT NULL,
  type smallint(1) NOT NULL
);

-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE role (
  id bigint(20) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  remark varchar(255) default NULL,
  PRIMARY KEY  (id)
);

-- ----------------------------
-- Table structure for systemparam
-- ----------------------------
CREATE TABLE systemparam (
  id bigint(20) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  val varchar(20) NOT NULL,
  type smallint(1) default NULL,
  sortnum smallint(6) default '0',
  remark varchar(255) default NULL,
  PRIMARY KEY  (id)
);

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
CREATE TABLE userrole (
  userid bigint(20) NOT NULL,
  roleid bigint(20) NOT NULL
);

-- ----------------------------
-- Table structure for wagepay
-- ----------------------------
CREATE TABLE wagepay (
  ID bigint(20) NOT NULL auto_increment,
  EMPID bigint(20) default NULL,
  OPERATER bigint(20) default NULL,
  AMOUNT decimal(10,2) default NULL,
  SDATE varchar(20) default NULL,
  EDATE varchar(20) default NULL,
  PAYDATE varchar(20) default NULL,
  PRIMARY KEY  (ID)
);

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO employee VALUES ('1', '龙影', '男', 'loyin', 'e10adc3949ba59abbe56e057f20f883e', '1984-12-12', '13333333333', 'fdsafdgag', 'lsf', '2000-01-01', '汉族', '362422111112302332', null, null, '10000', '/upload/201210252151010.jpg', '1', '1', '1', 'fdsagfdsgfdg', '范德萨分\r\nsdfsdfa\r\n范德萨\r\nwegfds\r\n过分的是\r\n核辐射');
INSERT INTO menu VALUES ('2', '系统管理', null, '0', '0', null, '10', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('3', '人力资源', null, '0', '0', null, '9', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('4', '计划', null, '0', '0', null, '8', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('5', '财务', null, '0', '0', null, '7', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('24', '任务', null, '0', '0', null, '4', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('22', '流程', null, '0', '0', null, '2', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('21', '文档', null, '0', '0', null, '1', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('20', '员工信息', 'employee', '1', '2', null, '1', null, '15', '0', null, '1');
INSERT INTO menu VALUES ('10', '功能菜单', 'menu', '1', '2', null, '0', null, '32', '0', null, '1');
INSERT INTO menu VALUES ('11', '角色', 'role', '1', '2', null, '1', null, '33', '0', null, '1');
INSERT INTO menu VALUES ('12', '部门', 'partment', '1', '2', null, '2', null, '33', '0', null, '1');
INSERT INTO menu VALUES ('13', '参数设置', 'systemparam', '1', '2', null, '3', null, '32', '0', null, '1');
INSERT INTO menu VALUES ('14', '机构', 'organization', '1', '2', null, '4', null, '33', '0', null, '1');
INSERT INTO menu VALUES ('15', '人员管理', null, '1', '1', null, '1', null, '3', '0', null, '1');
INSERT INTO menu VALUES ('16', '权限配置', 'power', '1', '2', null, '5', 'employee', '32', '0', null, '1');
INSERT INTO menu VALUES ('31', '流程管理', null, '1', '1', null, '1', null, '2', '0', null, '1');
INSERT INTO menu VALUES ('18', '员工工资发放', 'wagepay', '1', '1', null, '1', 'wagepay', '5', '0', null, '1');
INSERT INTO menu VALUES ('25', '日程', null, '0', '0', null, '5', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('26', '我的日程', null, '1', '1', null, '1', null, '25', '0', null, '1');
INSERT INTO menu VALUES ('27', '我的日程管理', 'calender', '1', '2', null, '1', null, '26', '0', null, '1');
INSERT INTO menu VALUES ('28', '考勤管理', null, '1', '1', null, '1', null, '3', '0', null, '1');
INSERT INTO menu VALUES ('29', '我的考勤', null, '1', '2', null, '1', null, '28', '0', null, '1');
INSERT INTO menu VALUES ('30', '考勤统计', null, '1', '2', null, '2', null, '28', '0', null, '1');
INSERT INTO menu VALUES ('32', '系统设置', null, '1', '1', null, '0', null, '2', '0', null, '1');
INSERT INTO menu VALUES ('33', '组织设置', null, '1', '1', null, '1', null, '2', '0', null, '1');
INSERT INTO menu VALUES ('34', '流程分类', 'flowcate', '1', '2', null, '1', null, '31', '0', null, '1');
INSERT INTO menu VALUES ('35', '流程部署', 'flowdeploy', '1', '2', null, '0', null, '31', '0', null, '1');
INSERT INTO menu VALUES ('36', '我的流程', null, '1', '1', null, '0', null, '22', '0', null, '1');
INSERT INTO menu VALUES ('37', '发布流程', null, '1', '1', null, '0', null, '22', '0', null, '1');
INSERT INTO menu VALUES ('38', '我的任务', null, '1', '1', null, '1', null, '24', '0', null, '1');
INSERT INTO menu VALUES ('39', '发布任务', null, '1', '1', null, '0', null, '24', '1', null, '1');
INSERT INTO menu VALUES ('43', '发布文档', null, '1', '2', null, '1', null, '42', '1', null, '1');
INSERT INTO menu VALUES ('41', '公共文档', null, '1', '1', null, '0', null, '21', '0', null, '1');
INSERT INTO menu VALUES ('42', '我的文档', null, '1', '1', null, '0', null, '21', '0', null, '1');
INSERT INTO menu VALUES ('44', '文档管理', null, '1', '2', null, '0', null, '42', '0', null, '1');
INSERT INTO menu VALUES ('45', '发布文档', null, '1', '2', null, '1', null, '41', '0', null, '1');
INSERT INTO menu VALUES ('46', '文档管理', null, '1', '2', null, '2', null, '41', '0', null, '1');
INSERT INTO menu VALUES ('47', '平台设置', 'platform/set', '1', '2', null, '0', null, '32', '0', null, '1');
INSERT INTO menu VALUES ('48', '流程管理', null, '1', '1', null, '0', null, '22', '0', null, '1');
INSERT INTO menu VALUES ('49', '流程分类', null, '1', '2', null, '0', null, '48', '0', null, '1');
INSERT INTO menu VALUES ('50', '流程发布', null, '1', '2', null, '0', null, '48', '0', null, '1');
INSERT INTO menu VALUES ('51', '资产', null, '0', '0', null, '1', null, '0', '0', null, '1');
INSERT INTO menu VALUES ('52', '会议管理', null, '1', '1', null, '0', null, '25', '0', null, '1');
INSERT INTO menu VALUES ('53', '证件', null, '0', '0', null, '0', null, '0', '0', null, '1');
INSERT INTO organization VALUES ('1', '肯德基', '北京路', null, '0', '范德萨', 'KFC01');
INSERT INTO partment VALUES ('1', '斯蒂芬', '范德萨', '1', '1', null, '0001');
INSERT INTO partment VALUES ('2', '范德萨分', '范德萨分', '1', '1', '1', '000102');
INSERT INTO powermenu VALUES ('14', '1', '0');
INSERT INTO powermenu VALUES ('12', '1', '0');
INSERT INTO powermenu VALUES ('11', '1', '0');
INSERT INTO powermenu VALUES ('33', '1', '0');
INSERT INTO powermenu VALUES ('16', '1', '0');
INSERT INTO powermenu VALUES ('13', '1', '0');
INSERT INTO powermenu VALUES ('10', '1', '0');
INSERT INTO powermenu VALUES ('47', '1', '0');
INSERT INTO powermenu VALUES ('32', '1', '0');
INSERT INTO powermenu VALUES ('2', '1', '0');
INSERT INTO powermenu VALUES ('20', '1', '0');
INSERT INTO powermenu VALUES ('15', '1', '0');
INSERT INTO powermenu VALUES ('30', '1', '0');
INSERT INTO powermenu VALUES ('29', '1', '0');
INSERT INTO powermenu VALUES ('28', '1', '0');
INSERT INTO powermenu VALUES ('3', '1', '0');
INSERT INTO powermenu VALUES ('4', '1', '0');
INSERT INTO powermenu VALUES ('18', '1', '0');
INSERT INTO powermenu VALUES ('5', '1', '0');
INSERT INTO powermenu VALUES ('27', '1', '0');
INSERT INTO powermenu VALUES ('26', '1', '0');
INSERT INTO powermenu VALUES ('52', '1', '0');
INSERT INTO powermenu VALUES ('25', '1', '0');
INSERT INTO powermenu VALUES ('38', '1', '0');
INSERT INTO powermenu VALUES ('39', '1', '0');
INSERT INTO powermenu VALUES ('24', '1', '0');
INSERT INTO powermenu VALUES ('36', '1', '0');
INSERT INTO powermenu VALUES ('37', '1', '0');
INSERT INTO powermenu VALUES ('49', '1', '0');
INSERT INTO powermenu VALUES ('50', '1', '0');
INSERT INTO powermenu VALUES ('48', '1', '0');
INSERT INTO powermenu VALUES ('22', '1', '0');
INSERT INTO powermenu VALUES ('46', '1', '0');
INSERT INTO powermenu VALUES ('45', '1', '0');
INSERT INTO powermenu VALUES ('41', '1', '0');
INSERT INTO powermenu VALUES ('43', '1', '0');
INSERT INTO powermenu VALUES ('44', '1', '0');
INSERT INTO powermenu VALUES ('42', '1', '0');
INSERT INTO powermenu VALUES ('21', '1', '0');
INSERT INTO powermenu VALUES ('53', '1', '0');
INSERT INTO powermenu VALUES ('51', '1', '0');
INSERT INTO powermenu VALUES ('31', '1', '0');
INSERT INTO powermenu VALUES ('35', '1', '0');
INSERT INTO powermenu VALUES ('34', '1', '0');
INSERT INTO role VALUES ('3', '员工', null);
INSERT INTO role VALUES ('2', '管理员', 'fds');
INSERT INTO userrole VALUES ('1', '2');
INSERT INTO userrole VALUES ('1', '3');
