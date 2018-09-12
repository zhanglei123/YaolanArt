/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : YaolanArt

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-08-08 00:03:38
*/

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) NOT NULL COMMENT '登录用户名',
  `password` varchar(200) NOT NULL,
  `true_name` varchar(200) NOT NULL COMMENT '真实姓名',
  `sex` int(1) NOT NULL COMMENT '1男 2女',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `qq` varchar(30) NOT NULL COMMENT 'QQ',
  `role_id` int(11) NOT NULL COMMENT '角色权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1', 'admin', 'a', '张三','1','13388888888','888888@qq.com','888888',0,now(),now());


-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '登录用户ID',
  `user_name` varchar(200) NOT NULL COMMENT '登录用户名',
  `state` int(1) NOT NULL COMMENT '1登录成功 2登录失败',
  `ip` varchar(20) NOT NULL COMMENT '登录IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '物品分类名称',
  `remark` varchar(1000) NOT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for supplier 供应商
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) NOT NULL COMMENT '供应商公司名称',
  `supplier_addr` varchar(100) NOT NULL COMMENT '供应商公司地址',
  `linkman_name` varchar(20) NOT NULL COMMENT '联系人名称',
  `linkman_sex` int(1) NOT NULL COMMENT '1男 2女',
  `linkman_phone` varchar(20) NOT NULL COMMENT '联系人电话',
  `remark` varchar(1000) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL COMMENT '物品分类ID',
  `name` varchar(50) NOT NULL COMMENT '物品名称',
  `version` varchar(20) NOT NULL COMMENT '型号',
  `colour` varchar(20) NOT NULL COMMENT '颜色',
  `num` int(8) NOT NULL COMMENT '数量',
  `unit` varchar(20) NOT NULL COMMENT '数量单位',
  `image_path` varchar(50) NOT NULL COMMENT '图片路径',
  `remark` varchar(1000) NOT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Table structure for course_card 课程卡次
-- ----------------------------
DROP TABLE IF EXISTS `course_card`;
CREATE TABLE `course_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '课程名称',
  `type` int(11) NOT NULL COMMENT '1,年卡,2,季卡',
  `num` int(5) NOT NULL COMMENT '节数',
  `total_price` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '总价格',
  `average_price` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '平均价格',
  `remark` varchar(1000) NOT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_promotion 课程活动
-- ----------------------------
DROP TABLE IF EXISTS `course_promotion`;
CREATE TABLE `course_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '活动名称',
  `type` int(11) NOT NULL COMMENT '类型，未定',
  `num` int(5) NOT NULL COMMENT '节数',
  `price` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '价格',
  `content` varchar(1000) NOT NULL COMMENT '活动内容简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for teacher 教师
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `sex` int(1) NOT NULL COMMENT '1男 2女',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `id_card` varchar(20) NOT NULL COMMENT '身份证号码',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `qq` varchar(30) NOT NULL COMMENT 'QQ',
  `image_path` varchar(50) NOT NULL COMMENT '个人图片路径',
  `course_type` int(2) NOT NULL COMMENT '课程类型，1:国画，1:漫画',
  `education_school` varchar(50) NOT NULL COMMENT '毕业学校',
  `education_type` int(2) NOT NULL COMMENT '学历',
  `education_time` datetime DEFAULT NULL COMMENT '毕业时间',
  `status` int(2) NOT NULL COMMENT '状态 1:在职,2:离职,3:兼职',
  `in_time` datetime DEFAULT NULL COMMENT '入职时间',
  `out_time` datetime DEFAULT NULL COMMENT '离职时间',
  `remark` varchar(1000) DEFAULT '' COMMENT '个人说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;








-- ----------------------------
-- Table structure for teacher_student 教师学生关联表
-- 当老师或学生离开时，可直接删除记录
-- ----------------------------
DROP TABLE IF EXISTS `teacher_student`;
CREATE TABLE `teacher_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) NOT NULL COMMENT '教师id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for student 学生
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `sex` int(1) NOT NULL COMMENT '1男 2女',
  `age` int(2) NOT NULL COMMENT '年龄',
  `school` varchar(50) NOT NULL COMMENT '学校',
  `class_name` varchar(30) NOT NULL COMMENT '班级',
  `address` varchar(50) NOT NULL COMMENT '地址',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `grade` int(1) DEFAULT NULL COMMENT '年级，0:幼儿园',
  `source_type` int(2) DEFAULT NULL COMMENT '学生来源',
  `image_path` varchar(50) NOT NULL COMMENT '个人图片路径',
  `status` int(2) NOT NULL COMMENT '状态 1:在读,2:离开',
  `remark` varchar(1000) DEFAULT '' COMMENT '个人说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for student_linkman_info 学生联系人信息
-- 会牵扯发通知短信
-- ----------------------------
DROP TABLE IF EXISTS `student_linkman_info`;
CREATE TABLE `student_linkman_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `relation_type` int(2) NOT NULL COMMENT '关系类型',
  `linkman_name` varchar(50) NOT NULL COMMENT '姓名',
  `linkman_sex` int(1) NOT NULL COMMENT '1男 2女',
  `linkman_phone` varchar(20) DEFAULT '' COMMENT '父亲电话',
  `is_first` int(1) NOT NULL COMMENT '是否首选联系人 1:是  2:否',
  `remark` varchar(1000) DEFAULT '' COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for student 学生卡次信息
-- 学生可能有多个卡次或活动，但只有一个卡次是激活状态
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `type` int(2) NOT NULL COMMENT '类型，1：课程卡次，2：课程活动',
  `course_id` int(11) NOT NULL COMMENT '课程或活动ID',
  `course_name` varchar(50) NOT NULL COMMENT '课程或活动名称',
  `original_num` int(3) NOT NULL DEFAULT 0 COMMENT '卡次或活动初始次数',
  `original_money` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '卡次或活动缴费金额',
  `average_money` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '卡次或活动单价',
  `remain_num` int(3) NOT NULL DEFAULT 0 COMMENT '卡次或活动剩余次数',
  `remain_money` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '卡次或活动剩余金额',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态 1:可用,2:冻结',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for lesson 课程
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL COMMENT '课程类型',
  `name` varchar(50) NOT NULL COMMENT '课程名称',
  `remark` varchar(200) NOT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Table structure for student_lesson 学生课程信息，一对多
-- ----------------------------
DROP TABLE IF EXISTS `student_lesson`;
CREATE TABLE `student_lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `lesson_id` int(11) NOT NULL COMMENT '课程id',
  `lesson_name` varchar(50) NOT NULL COMMENT '课程名称',
  `use_course_num` int(3) NOT NULL COMMENT '刷课使用卡次次数',
  `week` int(1) NOT NULL COMMENT '周几上课，1,3 代表周一，周三上课',
  `start_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;





-- ----------------------------
-- Table structure for student_recharge_record 学生充值缴费记录
-- 包括课程，活动（考级也是活动），不包括材料，材料在库存统计
-- ----------------------------
DROP TABLE IF EXISTS `student_recharge_record`;
CREATE TABLE `student_recharge_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `type` int(2) NOT NULL COMMENT '缴费内容类型，1：课程卡次，2：活动',
  `course_id` int(11) DEFAULT NULL COMMENT '课程或活动ID',
  `course_name` varchar(50) DEFAULT '' COMMENT '课程或活动名称',
  `money` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '缴费金额',
  `recharge_type` int(2) NOT NULL COMMENT '缴费方式，1：支付宝，2：微信，3：银行卡',
  `remark` varchar(1000) DEFAULT '' COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;





-- ----------------------------
-- Table structure for student_consumption_record 学生消费记录
-- ----------------------------
DROP TABLE IF EXISTS `student_consumption_record`;
CREATE TABLE `student_consumption_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `type` int(2) NOT NULL COMMENT '消费内容类型，1：课程卡次，2：活动',
  `lesson_id` int(11) NOT NULL COMMENT '课程id',
  `lesson_name` varchar(50) NOT NULL COMMENT '课程名称',
  `course_id` int(11) DEFAULT NULL COMMENT '课程或活动ID',
  `course_name` varchar(50) DEFAULT '' COMMENT '课程或活动名称',
  `money` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '消费金额',
  `num` int(2) NOT NULL DEFAULT 0 COMMENT '消费次数',
  `course_remain_num` int(3) NOT NULL DEFAULT 0 COMMENT '卡次或活动剩余次数',
  `course_remain_money` decimal(15,6) NOT NULL DEFAULT '0.000000' COMMENT '卡次或活动剩余金额',
  `teacher_id` int(11) NOT NULL COMMENT '上课老师id',
  `remark` varchar(1000) DEFAULT '' COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', 'admin', null);
INSERT INTO `role` VALUES ('1', 'teacher', null);
INSERT INTO `role` VALUES ('2', 'student', null);

-- ----------------------------
-- Table structure for config 配置表
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数名',
  `config_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数值',
  `parent_id` int(11) COLLATE utf8_bin DEFAULT NULL COMMENT '父级id',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO `config` VALUES (null, 'network_ip', '127.0.0.1',null,'服务器所在公网ip','2018-09-10 19:35:00','2018-09-10 19:35:00');
