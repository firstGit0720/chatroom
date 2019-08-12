/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : chat-room

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 12/08/2019 17:54:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendId` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送人的id',
  `sendTime` datetime(0) NOT NULL COMMENT '发送时的时间',
  `receiveId` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收人的id',
  `message` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息内容',
  `readStatus` smallint(6) NOT NULL COMMENT '阅读状态0：未阅 1 已阅',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '11460004', '2019-08-12 16:04:50', '25403717', '啊是大', 0);
INSERT INTO `message` VALUES (2, '11460004', '2019-08-12 16:56:06', '10620950', '阿萨达', 0);
INSERT INTO `message` VALUES (3, '11460004', '2019-08-12 17:03:26', '10620950', '阿萨达', 0);
INSERT INTO `message` VALUES (4, '11460004', '2019-08-12 17:06:16', '10620950', '啊实打实', 0);
INSERT INTO `message` VALUES (5, '11460004', '2019-08-12 17:10:35', '10620950', '阿萨达', 0);
INSERT INTO `message` VALUES (6, '10620950', '2019-08-12 17:12:28', '11460004', '阿萨达', 0);
INSERT INTO `message` VALUES (7, '10620950', '2019-08-12 17:19:49', '11460004', '你好', 0);
INSERT INTO `message` VALUES (8, '10620950', '2019-08-12 17:20:48', '11460004', '你好', 0);
INSERT INTO `message` VALUES (9, '11460004', '2019-08-12 17:21:41', '10620950', '阿斯达四大', 0);
INSERT INTO `message` VALUES (10, '10620950', '2019-08-12 17:22:22', '11460004', '阿萨达', 0);
INSERT INTO `message` VALUES (11, '10620950', '2019-08-12 17:24:06', '11460004', '阿萨达', 0);
INSERT INTO `message` VALUES (12, '11460004', '2019-08-12 17:26:02', '10620950', '啊实打实', 0);
INSERT INTO `message` VALUES (13, '11460004', '2019-08-12 17:27:50', '10620950', '啊实打实', 0);
INSERT INTO `message` VALUES (14, '11460004', '2019-08-12 17:30:07', '10620950', '阿萨达撒多撒', 0);
INSERT INTO `message` VALUES (15, '10620950', '2019-08-12 17:33:51', '11460004', '啊实打实', 0);
INSERT INTO `message` VALUES (16, '10620950', '2019-08-12 17:35:36', '11460004', '啊实打实', 0);
INSERT INTO `message` VALUES (17, '11460004', '2019-08-12 17:35:47', '10620950', '哈哈', 0);
INSERT INTO `message` VALUES (18, '10620950', '2019-08-12 17:36:00', '11460004', '？？', 0);
INSERT INTO `message` VALUES (19, '10620950', '2019-08-12 17:36:35', '11460004', '怎会', 0);
INSERT INTO `message` VALUES (20, '11460004', '2019-08-12 17:36:44', '10620950', '你看不见', 0);
INSERT INTO `message` VALUES (21, '11460004', '2019-08-12 17:37:07', '10620950', '咋回事', 0);
INSERT INTO `message` VALUES (22, '10620950', '2019-08-12 17:41:06', '11460004', '你好', 0);
INSERT INTO `message` VALUES (23, '11460004', '2019-08-12 17:41:13', '10620950', '你好', 0);
INSERT INTO `message` VALUES (24, '10620950', '2019-08-12 17:41:23', '11460004', '终于过来了', 0);
INSERT INTO `message` VALUES (25, '11460004', '2019-08-12 17:41:30', '10620950', '是啊', 0);
INSERT INTO `message` VALUES (26, '10620950', '2019-08-12 17:41:44', '11460004', '不容易啊', 0);
INSERT INTO `message` VALUES (27, '11460004', '2019-08-12 17:41:57', '10620950', '真的很麻烦', 0);
INSERT INTO `message` VALUES (28, '10620950', '2019-08-12 17:46:21', '11460004', '你好', 2);
INSERT INTO `message` VALUES (29, '11460004', '2019-08-12 17:46:36', '10620950', '你也好', 0);
INSERT INTO `message` VALUES (30, '10620950', '2019-08-12 17:47:26', '11460004', '阿SA', 2);
INSERT INTO `message` VALUES (31, '11460004', '2019-08-12 17:47:30', '10620950', 'ASDASA ', 0);
INSERT INTO `message` VALUES (32, '10620950', '2019-08-12 17:47:35', '11460004', 'ASDADA', 2);
INSERT INTO `message` VALUES (33, '11460004', '2019-08-12 17:47:40', '10620950', 'ASDASDAS', 0);
INSERT INTO `message` VALUES (34, '10620950', '2019-08-12 17:49:57', '11460004', '啊实打实', 2);
INSERT INTO `message` VALUES (35, '11460004', '2019-08-12 17:50:04', '10620950', '阿斯达四大', 0);
INSERT INTO `message` VALUES (36, '10620950', '2019-08-12 17:50:09', '11460004', '阿斯达四大', 2);
INSERT INTO `message` VALUES (37, '11460004', '2019-08-12 17:50:13', '10620950', '阿闪大大', 0);
INSERT INTO `message` VALUES (38, '10620950', '2019-08-12 17:50:19', '11460004', '阿斯达四大', 2);
INSERT INTO `message` VALUES (39, '11460004', '2019-08-12 17:50:25', '10620950', '阿斯达四大', 0);
INSERT INTO `message` VALUES (40, '10620950', '2019-08-12 17:50:32', '11460004', '阿闪大大撒', 2);
INSERT INTO `message` VALUES (41, '10620950', '2019-08-12 17:53:02', '11460004', '啊实打实', 2);
INSERT INTO `message` VALUES (42, '11460004', '2019-08-12 17:53:08', '10620950', '阿斯达四大所', 0);
INSERT INTO `message` VALUES (43, '10620950', '2019-08-12 17:53:13', '11460004', '阿斯达四大', 2);
INSERT INTO `message` VALUES (44, '11460004', '2019-08-12 17:53:19', '10620950', '123213', 0);
INSERT INTO `message` VALUES (45, '10620950', '2019-08-12 17:53:23', '11460004', '12312321', 2);
INSERT INTO `message` VALUES (46, '11460004', '2019-08-12 17:53:30', '10620950', '啊实打实', 0);

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `myId` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '你的id',
  `friendId` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '好友的id',
  `status` smallint(6) NOT NULL COMMENT '好友状态0：已发送了好友请求，1：成为好友，2不是好友',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation
-- ----------------------------
INSERT INTO `relation` VALUES (1, '11460004', '10620950', 1);
INSERT INTO `relation` VALUES (2, '11460004', '25403717', 1);
INSERT INTO `relation` VALUES (3, '10620950', '11460004', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `pid` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户的id或聊天的id',
  `pname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户的真实姓名',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户的登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `sex` smallint(6) NOT NULL COMMENT '性别0：男，1：女',
  `birthday` date NOT NULL COMMENT '出生日期',
  `age` int(11) NOT NULL COMMENT '年龄',
  `phone` varchar(33) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话',
  `status` smallint(6) NOT NULL DEFAULT 0 COMMENT '是否在线  0：未在线，1在线',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10620950', '李刚1', 'padmin1', '4QrcOUm6Wau+VuBX8g+IPg==', 0, '1996-03-01', 23, '15028409088', 1);
INSERT INTO `user` VALUES ('11460004', '李刚', 'padmin', '4QrcOUm6Wau+VuBX8g+IPg==', 0, '1996-03-01', 23, '15008409088', 1);
INSERT INTO `user` VALUES ('25403717', '李刚2', 'padmin2', '4QrcOUm6Wau+VuBX8g+IPg==', 0, '1996-03-01', 23, '15028409088', 0);

SET FOREIGN_KEY_CHECKS = 1;
