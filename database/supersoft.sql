/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : supersoft

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-26 16:28:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ADM_MENU
-- ----------------------------
DROP TABLE IF EXISTS `ADM_MENU`;
CREATE TABLE `ADM_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(200) DEFAULT NULL,
  `ICON` varchar(200) DEFAULT NULL,
  `HREF` varchar(255) DEFAULT NULL,
  `SPREAD` int(1) DEFAULT '0',
  `PID` bigint(20) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `CREATE_DT` datetime DEFAULT NULL,
  `CREATE_BY` varchar(25) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADM_MENU
-- ----------------------------
INSERT INTO `ADM_MENU` VALUES ('1', '系统管理', 'larry-xitongshezhi1', '', '0', null, '0', '0', '2017-12-11 15:07:36', 'admin', null);
INSERT INTO `ADM_MENU` VALUES ('2', '内容管理', 'larry-neirongguanli', null, '0', null, '0', '0', '2017-12-11 15:07:32', 'admin', null);
INSERT INTO `ADM_MENU` VALUES ('3', '微信公众', 'larry-weixingongzhongpingtai', null, '0', null, '0', '0', '2017-12-11 15:08:15', 'admin', null);
INSERT INTO `ADM_MENU` VALUES ('4', '扩展模块', 'larry-ht_expand', null, '0', null, '0', '0', '2017-12-11 15:08:46', 'admin', null);
INSERT INTO `ADM_MENU` VALUES ('5', '后台首页', 'larry-houtaishouye', 'html/main.php', '0', '1', '1', '0', null, null, null);
INSERT INTO `ADM_MENU` VALUES ('6', '我的面板', 'larry-gerenxinxi5', null, '1', '1', '1', '0', null, null, null);
INSERT INTO `ADM_MENU` VALUES ('7', '用户管理', 'larry-10103', null, '1', '1', '1', '0', null, null, null);
INSERT INTO `ADM_MENU` VALUES ('8', '个人信息', 'larry-gerenxinxi1', '/toLogin', '0', '6', '1', '0', null, null, null);
INSERT INTO `ADM_MENU` VALUES ('9', '修改密码', 'larry-xiugaimima2', 'html/changepwd.html', '0', '6', '1', '0', null, null, null);
INSERT INTO `ADM_MENU` VALUES ('10', '日志信息', 'larry-rizhi2', 'html/myloginfo.html', '0', '6', '1', '0', null, null, null);
INSERT INTO `ADM_MENU` VALUES ('11', '用户列表', 'larry-yonghuliebiao1', '/user/index', '0', '7', '1', '0', null, null, null);

-- ----------------------------
-- Table structure for ADM_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `ADM_ROLE`;
CREATE TABLE `ADM_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(25) DEFAULT NULL,
  `STATUS` varchar(25) DEFAULT NULL,
  `CREATE_DT` datetime NOT NULL,
  `CREATE_BY` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADM_ROLE
-- ----------------------------
INSERT INTO `ADM_ROLE` VALUES ('1', '超级管理员', '1', '0000-00-00 00:00:00', '');
INSERT INTO `ADM_ROLE` VALUES ('2', '系统管理员', '1', '0000-00-00 00:00:00', '');
INSERT INTO `ADM_ROLE` VALUES ('3', '普通', '1', '0000-00-00 00:00:00', '');
INSERT INTO `ADM_ROLE` VALUES ('4', '新注册', '1', '2016-12-13 14:55:18', '');

-- ----------------------------
-- Table structure for ADM_ROLE_MENU
-- ----------------------------
DROP TABLE IF EXISTS `ADM_ROLE_MENU`;
CREATE TABLE `ADM_ROLE_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `MENU_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADM_ROLE_MENU
-- ----------------------------
INSERT INTO `ADM_ROLE_MENU` VALUES ('1', '1', '1');
INSERT INTO `ADM_ROLE_MENU` VALUES ('2', '1', '2');
INSERT INTO `ADM_ROLE_MENU` VALUES ('3', '1', '3');
INSERT INTO `ADM_ROLE_MENU` VALUES ('4', '1', '4');
INSERT INTO `ADM_ROLE_MENU` VALUES ('5', '1', '5');
INSERT INTO `ADM_ROLE_MENU` VALUES ('6', '1', '6');
INSERT INTO `ADM_ROLE_MENU` VALUES ('7', '1', '11');
INSERT INTO `ADM_ROLE_MENU` VALUES ('8', '1', '12');
INSERT INTO `ADM_ROLE_MENU` VALUES ('9', '1', '13');

-- ----------------------------
-- Table structure for ADM_USER
-- ----------------------------
DROP TABLE IF EXISTS `ADM_USER`;
CREATE TABLE `ADM_USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_CODE` varchar(25) DEFAULT NULL,
  `USER_NAME` varchar(25) DEFAULT NULL,
  `PASSWORD` varchar(40) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `CREATE_DT` datetime DEFAULT NULL,
  `CREATE_BY` varchar(25) DEFAULT NULL,
  `SEX` varchar(255) DEFAULT NULL,
  `MOBILE_PHONE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(25) DEFAULT NULL,
  `ROLE_CODE` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADM_USER
-- ----------------------------
INSERT INTO `ADM_USER` VALUES ('4', 'admin12', '管理员t', 'admin12', '1', '2017-12-26 14:38:32', 'admin', '1', null, 'admin44@datuzi.com', '1');

-- ----------------------------
-- Function structure for getChild
-- ----------------------------
DROP FUNCTION IF EXISTS `getChild`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChild`(`rootId` bigint) RETURNS varchar(1000) CHARSET utf8
    NO SQL
BEGIN
	DECLARE ptemp varchar(1000);
	DECLARE ctemp varchar(1000);
       SET ptemp = '#';
       SET ctemp =cast(rootId as CHAR);
       WHILE ctemp is not null DO
             SET ptemp = concat(ptemp,',',ctemp);
            SELECT group_concat(id) INTO ctemp FROM ADM_MENU   
            WHERE FIND_IN_SET(pid,ctemp)>0; 
       END WHILE;  
       RETURN ptemp;  
END
;;
DELIMITER ;
