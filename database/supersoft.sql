/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : supersoft

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-28 11:08:52
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_menu
-- ----------------------------
INSERT INTO `adm_menu` VALUES ('1', '系统管理', 'larry-xitongshezhi1', '', '0', null, '0', '0', '2017-12-11 15:07:36', 'admin', null);
INSERT INTO `adm_menu` VALUES ('2', '内容管理', 'larry-neirongguanli', null, '0', null, '0', '0', '2017-12-11 15:07:32', 'admin', null);
INSERT INTO `adm_menu` VALUES ('3', '微信公众', 'larry-weixingongzhongpingtai', null, '0', null, '0', '0', '2017-12-11 15:08:15', 'admin', null);
INSERT INTO `adm_menu` VALUES ('4', '扩展模块', 'larry-ht_expand', null, '0', null, '0', '0', '2017-12-11 15:08:46', 'admin', null);
INSERT INTO `adm_menu` VALUES ('5', '后台首页', 'larry-houtaishouye', 'html/main.php', '0', '1', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('6', '我的面板', 'larry-gerenxinxi5', null, '1', '1', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('7', '用户管理', 'larry-10103', null, '1', '1', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('8', '个人信息', 'larry-gerenxinxi1', '/user/myInfo', '0', '6', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('9', '修改密码', 'larry-xiugaimima2', 'html/changepwd.html', '0', '6', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('10', '日志信息', 'larry-rizhi2', 'html/myloginfo.html', '0', '6', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('11', '用户列表', 'larry-yonghuliebiao1', '/user/index', '0', '7', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('12', '角色列表', 'larry-jiaoseguanli1', '/role/index', '0', '7', '1', '0', null, null, null);
INSERT INTO `adm_menu` VALUES ('13', '菜单管理', 'larry-caidanguanli', '/menu/index', '0', '7', '1', '0', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_role
-- ----------------------------
INSERT INTO `adm_role` VALUES ('1', '超级管理员', '1', '0000-00-00 00:00:00', '');
INSERT INTO `adm_role` VALUES ('2', '系统管理员', '1', '0000-00-00 00:00:00', '');
INSERT INTO `adm_role` VALUES ('3', '普通', '1', '0000-00-00 00:00:00', '');
INSERT INTO `adm_role` VALUES ('4', '新注册', '1', '2016-12-13 14:55:18', '');

-- ----------------------------
-- Table structure for adm_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `adm_role_menu`;
CREATE TABLE `adm_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_role_menu
-- ----------------------------
INSERT INTO `adm_role_menu` VALUES ('1', '1', '1');
INSERT INTO `adm_role_menu` VALUES ('2', '1', '2');
INSERT INTO `adm_role_menu` VALUES ('3', '1', '3');
INSERT INTO `adm_role_menu` VALUES ('4', '1', '4');
INSERT INTO `adm_role_menu` VALUES ('5', '1', '5');
INSERT INTO `adm_role_menu` VALUES ('6', '1', '6');
INSERT INTO `adm_role_menu` VALUES ('7', '1', '11');
INSERT INTO `adm_role_menu` VALUES ('8', '1', '12');
INSERT INTO `adm_role_menu` VALUES ('9', '1', '13');

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
  `role_code` bigint(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_user
-- ----------------------------
INSERT INTO `adm_user` VALUES ('4', 'admin', '管理员2', 'admin', '1', '2017-12-26 14:38:32', 'admin', '0', '17688888888', 'admin44@datuzi.com', '4fae5755-f294-4739-b0d2-2ec3c1606925.png', null, '0');

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
