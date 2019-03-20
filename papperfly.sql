/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : papperfly

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-01-08 03:52:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleid` int(20) NOT NULL AUTO_INCREMENT,
  `authorid` int(20) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '123', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');
INSERT INTO `article` VALUES ('2', '123', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');
INSERT INTO `article` VALUES ('3', '123', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');
INSERT INTO `article` VALUES ('4', '123', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');
INSERT INTO `article` VALUES ('5', '1234', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');
INSERT INTO `article` VALUES ('6', '1234', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');
INSERT INTO `article` VALUES ('7', '123', '穿过县境上长长的隧道，便是雪国。夜空下，大地一片莹白，火车在信号所前停下来。');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collectid` int(20) NOT NULL,
  `collecterid` int(20) DEFAULT NULL,
  `collectdarticleid` int(25) DEFAULT NULL,
  PRIMARY KEY (`collectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '123', '1');
INSERT INTO `collect` VALUES ('2', '123', '2');
INSERT INTO `collect` VALUES ('3', '1234', '1');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `followid` int(20) NOT NULL,
  `follower` int(20) DEFAULT NULL,
  `followee` int(20) DEFAULT NULL,
  PRIMARY KEY (`followid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('1', '123', '1234');
INSERT INTO `follow` VALUES ('2', '1234', '123');
INSERT INTO `follow` VALUES ('3', '123', '12345');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(20) NOT NULL,
  `sender_id` int(20) DEFAULT NULL,
  `accepter_id` int(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '123', '1234', '你好，认识一下。');
INSERT INTO `message` VALUES ('2', '123', '1234', '在吗？想问你件事。');
INSERT INTO `message` VALUES ('3', '123', '1234', '哈哈，我也这么觉得。');
INSERT INTO `message` VALUES ('4', '123', '1234', '那下次再说吧。');

-- ----------------------------
-- Table structure for mimi
-- ----------------------------
DROP TABLE IF EXISTS `mimi`;
CREATE TABLE `mimi` (
  `mimi_id` int(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`mimi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mimi
-- ----------------------------
INSERT INTO `mimi` VALUES ('1', '最多能发送三十个字符，包括英文字符，无法输入超过三十个字符。');
INSERT INTO `mimi` VALUES ('2', '最多能发送三十个字符，包括英文字符，无法输入超过三十个字符。');
INSERT INTO `mimi` VALUES ('3', '最多能发送三十个字符，包括英文字符，无法输入超过三十个字符。');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '123456789', '许江', '123456');
INSERT INTO `user` VALUES ('1234', '123456789', '发不了', '123444');
INSERT INTO `user` VALUES ('12345', '123456789', '哈哈', '1234567');
