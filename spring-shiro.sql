/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : spring-shiro

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-12-30 17:33:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
INSERT INTO `sys_permissions` VALUES ('1', 'user:add', '增加用户权限', null);
INSERT INTO `sys_permissions` VALUES ('2', 'user:delete', '删除用户的权限', null);
INSERT INTO `sys_permissions` VALUES ('3', 'user:update', '修改用户权限', null);
INSERT INTO `sys_permissions` VALUES ('4', 'user:view', '查看用户的权限', null);
INSERT INTO `sys_permissions` VALUES ('5', 'user:huiyuan', null, null);
INSERT INTO `sys_permissions` VALUES ('6', 'user:superhuiyuan', null, null);

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', 'admin', null, '0');
INSERT INTO `sys_roles` VALUES ('2', '老板', null, null);
INSERT INTO `sys_roles` VALUES ('3', '游客', null, null);
INSERT INTO `sys_roles` VALUES ('4', '普通会员', null, null);
INSERT INTO `sys_roles` VALUES ('5', '高级会员', null, null);

-- ----------------------------
-- Table structure for sys_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------
INSERT INTO `sys_roles_permissions` VALUES ('1', '1');
INSERT INTO `sys_roles_permissions` VALUES ('1', '2');
INSERT INTO `sys_roles_permissions` VALUES ('1', '3');
INSERT INTO `sys_roles_permissions` VALUES ('2', '3');
INSERT INTO `sys_roles_permissions` VALUES ('2', '5');
INSERT INTO `sys_roles_permissions` VALUES ('3', '5');
INSERT INTO `sys_roles_permissions` VALUES ('3', '6');
INSERT INTO `sys_roles_permissions` VALUES ('4', '3');
INSERT INTO `sys_roles_permissions` VALUES ('4', '4');

-- ----------------------------
-- Table structure for sys_url_filter
-- ----------------------------
DROP TABLE IF EXISTS `sys_url_filter`;
CREATE TABLE `sys_url_filter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_url_filter
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', '202CB962AC59075B964B07152D234B70', '0', null);
INSERT INTO `sys_users` VALUES ('2', 'guest', '202CB962AC59075B964B07152D234B70', '0', null);
INSERT INTO `sys_users` VALUES ('3', 'dimhat', '202CB962AC59075B964B07152D234B70', '0', null);

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES ('1', '1');
INSERT INTO `sys_users_roles` VALUES ('1', '3');
INSERT INTO `sys_users_roles` VALUES ('2', '4');
