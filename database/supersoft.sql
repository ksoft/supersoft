/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : supersoft

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-01-08 14:39:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adm_log
-- ----------------------------
DROP TABLE IF EXISTS `adm_log`;
CREATE TABLE `adm_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `create_dt` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_log
-- ----------------------------
INSERT INTO `adm_log` VALUES ('2', '4', '192.168.1.1', '2018-01-07 13:32:02', '管理员');
INSERT INTO `adm_log` VALUES ('3', '4', '0:0:0:0:0:0:0:1', '2018-01-07 13:38:56', '管理员');
INSERT INTO `adm_log` VALUES ('4', '4', '0:0:0:0:0:0:0:1', '2018-01-07 13:44:29', '管理员');
INSERT INTO `adm_log` VALUES ('5', '4', '0:0:0:0:0:0:0:1', '2018-01-07 13:44:43', '管理员');
INSERT INTO `adm_log` VALUES ('6', '4', '0:0:0:0:0:0:0:1', '2018-01-08 09:00:55', '管理员');

-- ----------------------------
-- Table structure for adm_menu
-- ----------------------------
DROP TABLE IF EXISTS `adm_menu`;
CREATE TABLE `adm_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` int(1) DEFAULT '0',
  `pid` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_dt` datetime DEFAULT NULL,
  `create_by` varchar(25) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_menu
-- ----------------------------
INSERT INTO `adm_menu` VALUES ('1', '系统管理', 'larry-xitongshezhi1', '', '0', null, '0', '0', '2017-12-11 15:07:36', 'admin', null);
INSERT INTO `adm_menu` VALUES ('2', '内容管理', 'larry-neirongguanli', null, '0', null, '0', '0', '2017-12-11 15:07:32', 'admin', null);
INSERT INTO `adm_menu` VALUES ('3', '微信公众', 'larry-weixingongzhongpingtai', null, '0', null, '0', '0', '2017-12-11 15:08:15', 'admin', null);
INSERT INTO `adm_menu` VALUES ('4', '扩展模块', 'larry-ht_expand', null, '0', null, '0', '0', '2017-12-11 15:08:46', 'admin', null);
INSERT INTO `adm_menu` VALUES ('5', '后台首页', 'larry-houtaishouye', 'html/main.php', '0', '1', '1', '0', null, 'admin', null);
INSERT INTO `adm_menu` VALUES ('6', '我的面板', 'larry-gerenxinxi5', null, '1', '1', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('7', '用户管理', 'larry-10103', null, '1', '1', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('8', '个人信息', 'larry-gerenxinxi1', '/user/myInfo', '0', '6', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('9', '修改密码', 'larry-xiugaimima2', '/user/changePwd', '0', '6', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('10', '日志信息', 'larry-rizhi2', '/log/index', '0', '6', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('11', '用户列表', 'larry-yonghuliebiao1', '/user/index', '0', '7', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('12', '角色列表', 'larry-jiaoseguanli1', '/role/index', '0', '7', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('13', '菜单管理', 'larry-caidanguanli', '/menu/index', '0', '7', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('14', '顶替', 'larry-neirongguanli', '/TEST', null, null, '0', '0', '2018-01-03 16:43:10', 'admin', null);
INSERT INTO `adm_menu` VALUES ('15', '顶替2', 'larry-neirongguanli', '/TEST2', null, '14', '1', '1', '2018-01-03 16:44:27', 'admin', null);
INSERT INTO `adm_menu` VALUES ('17', '图片管理', 'larry-rizhi2', '/photo/index', null, '6', '1', '0', '2018-01-08 11:24:36', 'admin', null);

-- ----------------------------
-- Table structure for adm_role
-- ----------------------------
DROP TABLE IF EXISTS `adm_role`;
CREATE TABLE `adm_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `create_dt` datetime NOT NULL,
  `create_by` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_role
-- ----------------------------
INSERT INTO `adm_role` VALUES ('1', '超级管理员', '1', '2017-12-29 10:00:24', 'SYS');
INSERT INTO `adm_role` VALUES ('2', '管理员', '0', '2017-12-29 10:00:27', 'admin');
INSERT INTO `adm_role` VALUES ('3', '普通用户', '0', '2017-12-29 10:00:30', 'admin');
INSERT INTO `adm_role` VALUES ('4', '新注册', '0', '2016-12-13 14:55:18', 'admin');
INSERT INTO `adm_role` VALUES ('5', '程序员', '0', '2017-12-29 10:44:12', 'admin');
INSERT INTO `adm_role` VALUES ('6', '止', '1', '2017-12-29 10:44:31', 'admin');
INSERT INTO `adm_role` VALUES ('7', '222', '0', '2018-01-03 14:16:03', 'admin');

-- ----------------------------
-- Table structure for adm_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `adm_role_menu`;
CREATE TABLE `adm_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_role_menu
-- ----------------------------
INSERT INTO `adm_role_menu` VALUES ('211', '3', '14', '管理员2', '2018-01-05 10:16:51');
INSERT INTO `adm_role_menu` VALUES ('212', '3', '15', '管理员2', '2018-01-05 10:16:51');
INSERT INTO `adm_role_menu` VALUES ('213', '4', '4', '管理员2', '2018-01-05 10:18:36');
INSERT INTO `adm_role_menu` VALUES ('214', '4', '14', '管理员2', '2018-01-05 10:18:36');
INSERT INTO `adm_role_menu` VALUES ('215', '4', '15', '管理员2', '2018-01-05 10:18:36');
INSERT INTO `adm_role_menu` VALUES ('260', '6', '1', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('261', '6', '6', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('262', '6', '8', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('263', '6', '9', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('264', '6', '10', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('265', '6', '7', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('266', '6', '11', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('267', '6', '12', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('268', '6', '13', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('269', '6', '3', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('270', '6', '14', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('271', '6', '15', '管理员2', '2018-01-05 10:22:19');
INSERT INTO `adm_role_menu` VALUES ('323', '7', '1', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('324', '7', '5', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('325', '7', '6', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('326', '7', '8', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('327', '7', '9', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('328', '7', '10', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('329', '7', '7', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('330', '7', '12', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('331', '7', '13', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('332', '7', '2', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('333', '7', '14', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('334', '7', '15', '管理员2', '2018-01-07 12:04:27');
INSERT INTO `adm_role_menu` VALUES ('350', '2', '1', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('351', '2', '6', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('352', '2', '8', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('353', '2', '9', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('354', '2', '10', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('355', '2', '7', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('356', '2', '11', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('357', '2', '12', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('358', '2', '13', '管理员2', '2018-01-07 12:24:52');
INSERT INTO `adm_role_menu` VALUES ('359', '1', '1', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('360', '1', '5', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('361', '1', '6', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('362', '1', '8', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('363', '1', '9', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('364', '1', '10', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('365', '1', '17', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('366', '1', '7', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('367', '1', '11', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('368', '1', '12', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('369', '1', '13', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('370', '1', '2', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('371', '1', '3', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('372', '1', '4', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('373', '1', '14', '管理员', '2018-01-08 11:24:47');
INSERT INTO `adm_role_menu` VALUES ('374', '1', '15', '管理员', '2018-01-08 11:24:47');

-- ----------------------------
-- Table structure for adm_user
-- ----------------------------
DROP TABLE IF EXISTS `adm_user`;
CREATE TABLE `adm_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(25) DEFAULT NULL,
  `user_name` varchar(25) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `create_dt` datetime DEFAULT NULL,
  `create_by` varchar(25) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `head_icon` varchar(255) DEFAULT NULL,
  `motto` varchar(2000) DEFAULT NULL,
  `role_id` bigint(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_user
-- ----------------------------
INSERT INTO `adm_user` VALUES ('4', 'admin', '管理员', 'admin', '0', '2017-12-26 14:38:32', 'admin', '0', '17688888888', 'admin44@datuzi.com', '64e4e658-bec1-43d0-9f30-fea4f412e626.png', '', '1');
INSERT INTO `adm_user` VALUES ('6', 'xiaozhang', '小张', 'xiaozhang', '0', '2018-01-03 14:26:19', 'admin', '0', '17688882636', 'asdf@adsf.com', null, '就是装X', '2');
INSERT INTO `adm_user` VALUES ('7', 'xiaoli', '小李', 'xiaoli', '0', '2018-01-03 14:29:50', 'admin', '1', '17699632658', 'asdf@asdf.com', null, '测试', '4');
INSERT INTO `adm_user` VALUES ('8', 'test', 'Test用户', 'test1', '0', '2018-01-07 12:30:54', 'admin', '0', '18688888888', '452@saf.com', null, '', '5');

-- ----------------------------
-- Table structure for user_photo
-- ----------------------------
DROP TABLE IF EXISTS `user_photo`;
CREATE TABLE `user_photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `img_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `img_src` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_dt` datetime DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_photo
-- ----------------------------
INSERT INTO `user_photo` VALUES ('1', '4', '车头照', '0e903467-32f4-4800-b37f-39176f0deb85.png', '2018-01-08 11:15:53', 'admin');
INSERT INTO `user_photo` VALUES ('2', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('3', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('4', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('5', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('6', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('7', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('8', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('9', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('10', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('11', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('12', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('13', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('14', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('15', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('16', '4', '基本原则', 'userface2.jpg', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('17', '4', '基本原则', '0e903467-32f4-4800-b37f-39176f0deb85.png', '2018-01-08 11:16:16', 'admin');
INSERT INTO `user_photo` VALUES ('18', '4', '基本原则', 'userface1.jpg', '2018-01-08 11:16:16', 'admin');

-- ----------------------------
-- Function structure for getChild
-- ----------------------------
DROP FUNCTION IF EXISTS `getChild`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChild`(`rootId` bigint) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_unicode_ci
    NO SQL
BEGIN
	DECLARE ptemp varchar(1000);
	DECLARE ctemp varchar(1000);
       SET ptemp = '#';
       SET ctemp =cast(rootId as CHAR);
       WHILE ctemp is not null DO
             SET ptemp = concat(ptemp,',',ctemp);
            SELECT group_concat(id) INTO ctemp FROM adm_menu
            WHERE status=0 and FIND_IN_SET(pid,ctemp)>0;
       END WHILE;
       RETURN ptemp;
END
;;
DELIMITER ;
