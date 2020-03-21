/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : books

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 19/03/2020 09:32:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id，主键号',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `book_kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `book_price` decimal(10, 2) NULL DEFAULT NULL,
  `qs_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('bk_001', '活着', '人文', 43.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_002', '我与地坛', '人文', 60.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_00222', '活着', '人文', 43.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_00234', '活着', '人文', 43.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_003', 'c', '编程', 88.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_004', 'c#', '编程', 54.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_006', '我与地坛', '人文', 60.00, 'qs_002');
INSERT INTO `book` VALUES ('bk_007', 'c', '编程', 88.00, 'qs_002');
INSERT INTO `book` VALUES ('bk_008', 'c#', '编程', 54.00, 'qs_002');
INSERT INTO `book` VALUES ('bk_009', 'python', '编程', 50.00, 'qs_002');
INSERT INTO `book` VALUES ('bk_010', '活着', '人文', 43.00, 'qs_003');
INSERT INTO `book` VALUES ('bk_011', '我与地坛', '人文', 60.00, 'qs_003');
INSERT INTO `book` VALUES ('bk_012', 'c', '编程', 88.00, 'qs_003');
INSERT INTO `book` VALUES ('bk_013', 'c#', '编程', 54.00, 'qs_003');
INSERT INTO `book` VALUES ('bk_014', 'python', '编程', 50.00, 'qs_003');
INSERT INTO `book` VALUES ('bk_015', '活着', '人文', 43.00, 'qs_002');
INSERT INTO `book` VALUES ('bk_016', 'python', '编程', 43.00, 'qs_001');
INSERT INTO `book` VALUES ('bk_020', '活着', '人文', 43.00, 'qs_001');

-- ----------------------------
-- Table structure for cclass
-- ----------------------------
DROP TABLE IF EXISTS `cclass`;
CREATE TABLE `cclass`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id号，主键',
  `ccl_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cclass
-- ----------------------------
INSERT INTO `cclass` VALUES (1, '01班');
INSERT INTO `cclass` VALUES (2, '02班');
INSERT INTO `cclass` VALUES (3, '03班');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id号，主键',
  `col_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机学院');
INSERT INTO `college` VALUES (2, '数学学院');
INSERT INTO `college` VALUES (4, '传媒学院');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gra_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '15级');
INSERT INTO `grade` VALUES (2, '16级');
INSERT INTO `grade` VALUES (3, '17级');
INSERT INTO `grade` VALUES (4, '18级');

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stoo_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费人id',
  `payment` decimal(10, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `pay_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '缴费日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (2, 'to7uF3PC8IAaBBpW', 215.00, '2020-03-05 16:04:46');
INSERT INTO `payments` VALUES (3, '5b570x0UB0Up26C6', 240.00, '2020-03-05 17:19:07');
INSERT INTO `payments` VALUES (4, 'sI9JMa8MRv9Yvq19', 540.00, '2020-03-07 13:56:36');
INSERT INTO `payments` VALUES (5, '0kXVDtvJk5E2pww1', 162.00, '2020-03-07 14:18:20');
INSERT INTO `payments` VALUES (6, 'Fk8aD14dp2Xoa26v', 378.00, '2020-03-07 14:32:26');
INSERT INTO `payments` VALUES (7, 'LfBdlDWByr03q7gE', 270.00, '2020-03-16 12:25:36');

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prof_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES (1, '软件工程专业');
INSERT INTO `profession` VALUES (2, '计算机科学与技术');
INSERT INTO `profession` VALUES (3, '信息管理专业');
INSERT INTO `profession` VALUES (4, '数学专业');
INSERT INTO `profession` VALUES (5, '统计专业');
INSERT INTO `profession` VALUES (6, '电科专业');
INSERT INTO `profession` VALUES (7, '电信专业');
INSERT INTO `profession` VALUES (100, '电子专业');

-- ----------------------------
-- Table structure for qs
-- ----------------------------
DROP TABLE IF EXISTS `qs`;
CREATE TABLE `qs`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id号，主键',
  `qs_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供货商公司名称',
  `qs_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供货商地址',
  `qs_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供货商联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qs
-- ----------------------------
INSERT INTO `qs` VALUES ('qs_001', 'q1', '湖北', 'aaaaa');
INSERT INTO `qs` VALUES ('qs_002', 'q2', '江苏', 'qqq');
INSERT INTO `qs` VALUES ('qs_003', 'q3', '浙江', NULL);

-- ----------------------------
-- Table structure for store_in
-- ----------------------------
DROP TABLE IF EXISTS `store_in`;
CREATE TABLE `store_in`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id，主键号',
  `store_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '入库时间',
  `store_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库信息',
  `book_count` int(255) NULL DEFAULT NULL,
  `book_init` int(11) NULL DEFAULT NULL,
  `book_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教材id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_in
-- ----------------------------
INSERT INTO `store_in` VALUES ('0F35BoanJf22fU0G', '2020-03-07 14:30:39', '急需', 60, 100, 'bk_008');
INSERT INTO `store_in` VALUES ('27PGbdj2GCqWgf3y', '2020-03-07 15:43:18', '是撒撒', 111, 111, 'bk_006');
INSERT INTO `store_in` VALUES ('qVwdTvVCY609HUFo', '2020-03-07 14:30:46', '急需', 70, 100, 'bk_009');
INSERT INTO `store_in` VALUES ('yFK3J50H37l2ju05', '2020-03-07 14:30:53', '急需', 80, 100, 'bk_007');

-- ----------------------------
-- Table structure for store_out
-- ----------------------------
DROP TABLE IF EXISTS `store_out`;
CREATE TABLE `store_out`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id,主键号',
  `book_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_out` int(255) NULL DEFAULT 0 COMMENT '领走了多少本',
  `book_back` int(255) NULL DEFAULT 0 COMMENT '归还多少本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_out
-- ----------------------------
INSERT INTO `store_out` VALUES ('ARUv4rawPT3N1n86', 'bk_008', 't111', 28, 0);
INSERT INTO `store_out` VALUES ('fj3JdagtN3GrPnC0', 'bk_009', 't111', 22, 0);
INSERT INTO `store_out` VALUES ('Fk8aD14dp2Xoa26v', 'bk_008', 'jP3QxgU10NBsSrTD', 10, 3);
INSERT INTO `store_out` VALUES ('gw8NSf0Lr9g4TKlv', 'bk_009', 'jP3QxgU10NBsSrTD', 8, 0);
INSERT INTO `store_out` VALUES ('I0AAT5rCDoQ6Sc40', 'bk_007', 't111', 25, 0);
INSERT INTO `store_out` VALUES ('Ie2J5A8bN883bxF4', 'bk_007', 'jP3QxgU10NBsSrTD', 5, 0);
INSERT INTO `store_out` VALUES ('LfBdlDWByr03q7gE', 'bk_008', '6IXgo845XWQHqjFS', 5, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id号，主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员登录时的头像',
  `identification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表明自己是学生还是老师还是管理员',
  `is_admin` int(11) NULL DEFAULT 0 COMMENT '是否为管理员，1代表是管理员，0代表普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1Y14UAkYIXqL5lC3', '11', '123', '1', '11', '', '学生', 0);
INSERT INTO `user` VALUES ('20200001', 'morty', '123', 'xxx', 'xxx', '1581236183743_administrator_32px_1175697_easyicon.net.png', '管理员', 1);
INSERT INTO `user` VALUES ('6IXgo845XWQHqjFS', 'rick', '123', '13557896451', '13779856531@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('7HvbPS4n7sgf8gBM', 'bob', '123', '13976544321', '13976544321@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('EP459v610Clxx85f', 'aliace', '123', '13976544561', '13976544561@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('j2I2u84TUERsyXUe', 'jack', '123', '15986544321', '15986544321@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('jP3QxgU10NBsSrTD', 'king', '123', '15943213421', '15943213421@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('kbakl02nF40QX6oc', 'jick', '123', '15976544563', '15976544563@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('N9Xw2gnaMVyqLC66', 'dsvsc', '123', '111', '111', '', '教师', 0);
INSERT INTO `user` VALUES ('R83ry6t8VAdC0CbH', 'sun', '123', '13786544321', '13786544321@163.com', '', '学生', 0);
INSERT INTO `user` VALUES ('t111', 'xx', '123', 'xxx', 'xx', '', '教师', 0);
INSERT INTO `user` VALUES ('x8w6te77K92k52Mm', 'cathy', '123', '15986533564', '15986533564@163.com', '', '学生', 0);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `col_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prof_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gra_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ccl_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 't111', '1', '1', '1', '1', NULL);
INSERT INTO `userinfo` VALUES (13, '1Y14UAkYIXqL5lC3', '1', '1', '1', '1', 't111');
INSERT INTO `userinfo` VALUES (14, 'N9Xw2gnaMVyqLC66', '1', '1', '1', '1', NULL);
INSERT INTO `userinfo` VALUES (15, '6IXgo845XWQHqjFS', '1', '1', '1', '2', 't111');
INSERT INTO `userinfo` VALUES (16, 'R83ry6t8VAdC0CbH', '1', '1', '1', '2', 't111');
INSERT INTO `userinfo` VALUES (17, 'jP3QxgU10NBsSrTD', '1', '1', '1', '1', 't111');
INSERT INTO `userinfo` VALUES (18, 'x8w6te77K92k52Mm', '1', '1', '1', '3', 't111');
INSERT INTO `userinfo` VALUES (19, '7HvbPS4n7sgf8gBM', '1', '1', '1', '3', 't111');
INSERT INTO `userinfo` VALUES (20, 'j2I2u84TUERsyXUe', '1', '1', '1', '3', 't111');
INSERT INTO `userinfo` VALUES (21, 'EP459v610Clxx85f', '1', '1', '2', '1', 't111');
INSERT INTO `userinfo` VALUES (22, 'kbakl02nF40QX6oc', '1', '1', '2', '2', 't111');

-- ----------------------------
-- View structure for book图表
-- ----------------------------
DROP VIEW IF EXISTS `book图表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `book图表` AS select `book`.`id` AS `id`,`book`.`book_name` AS `book_name`,`book`.`book_kind` AS `book_kind`,`book`.`book_price` AS `book_price`,`book`.`qs_id` AS `qs_id` from `book`;

SET FOREIGN_KEY_CHECKS = 1;
