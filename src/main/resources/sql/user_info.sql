/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : zhangchangq

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-03-10 19:40:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '//1代表男性，2代表女性',
  `age` int(11) NOT NULL DEFAULT '0',
  `telphone` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//byphone,by wechat ,by alipay',
  `thrird_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
