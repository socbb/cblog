/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : 127.0.0.1:3306
 Source Schema         : cblog

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 20/12/2018 20:14:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `domain` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` bigint(20) NOT NULL,
  `key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `value` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `table_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `field_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `root` int(1) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int(11) NOT NULL,
  `seq` int(11) NOT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, NULL, 1, '首页', '/', NULL, NULL, 1, 0, '2018-07-22 18:04:06', '2018-07-22 18:04:06');
INSERT INTO `menu` VALUES (2, NULL, 1, '系统管理', NULL, '', NULL, 1, 1, '2018-07-22 18:04:06', '2018-07-22 18:04:08');
INSERT INTO `menu` VALUES (3, 2, 0, '菜单管理', 'menus', 'menus', NULL, 1, 1, '2018-07-22 18:04:06', '2018-07-22 18:04:06');
INSERT INTO `menu` VALUES (4, 2, 0, '用户管理', 'users', 'users', NULL, 1, 2, '2018-07-27 12:23:11', '2018-07-27 12:23:45');
INSERT INTO `menu` VALUES (5, 2, 0, '角色管理', 'roles', 'roles', NULL, 1, 3, '2018-07-22 18:04:06', '2018-07-22 18:04:06');
INSERT INTO `menu` VALUES (6, 3, 0, '菜单添加', NULL, 'menu:add', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (7, 3, 0, '菜单修改', NULL, 'menu:edit', NULL, 2, 2, '2018-07-22 18:04:06', '2018-07-22 18:04:06');
INSERT INTO `menu` VALUES (8, 3, 0, '菜单删除', NULL, 'menu:delete', NULL, 2, 3, '2018-07-22 18:04:06', '2018-07-27 11:32:31');
INSERT INTO `menu` VALUES (9, 4, 0, '用户添加', NULL, 'user:add', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (10, 4, 0, '用户编辑', NULL, 'user:edit', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (11, 4, 0, '用户删除', NULL, 'user:delete', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (12, 4, 0, '分配角色', NULL, 'user:allotRole', NULL, 2, 2147483647, '2018-07-29 09:16:58', '2018-07-29 12:24:05');
INSERT INTO `menu` VALUES (13, 5, 0, '角色添加', NULL, 'role:add', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (14, 5, 0, '角色编辑', NULL, 'role:edit', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (15, 5, 0, '角色删除', NULL, 'role:delete', NULL, 2, 1, '2018-07-22 18:04:06', '2018-07-27 12:21:57');
INSERT INTO `menu` VALUES (16, 5, 0, '权限设置', NULL, 'role:allot', NULL, 2, 2147483647, '2018-07-27 20:51:35', '2018-07-27 20:51:35');
INSERT INTO `menu` VALUES (2291118888714240, NULL, 1, '博客管理', NULL, 'blogs', NULL, 1, 2147483647, '2018-07-28 16:51:35', '2018-07-28 16:51:35');
INSERT INTO `menu` VALUES (2292719586115584, 2291118888714240, 0, '文章管理', 'articles', 'articles', NULL, 1, 2147483647, '2018-07-28 16:57:57', '2018-07-28 16:57:57');
INSERT INTO `menu` VALUES (2292984548687872, 2291118888714240, 0, '标签管理', 'tags', 'tags', NULL, 1, 2147483647, '2018-07-28 16:59:00', '2018-07-28 16:59:00');
INSERT INTO `menu` VALUES (2293127180189696, 2291118888714240, 0, '分类管理', 'nodes', 'nodes', NULL, 1, 2147483647, '2018-07-28 16:59:34', '2018-07-28 16:59:34');
INSERT INTO `menu` VALUES (2298583143415808, 2292719586115584, 0, '文章添加', NULL, 'article:add', NULL, 2, 2147483647, '2018-07-28 17:21:15', '2018-07-28 17:21:15');
INSERT INTO `menu` VALUES (2298737871290368, 2292719586115584, 0, '文章修改', NULL, 'article:edit', NULL, 2, 2147483647, '2018-07-28 17:21:52', '2018-07-28 17:21:52');
INSERT INTO `menu` VALUES (2298853235621888, 2292719586115584, 0, '文章删除', NULL, 'article:delete', NULL, 2, 2147483647, '2018-07-28 17:22:19', '2018-07-28 17:22:19');
INSERT INTO `menu` VALUES (2299215044673536, 2292984548687872, 0, '标签添加', NULL, 'tag:add', NULL, 2, 2147483647, '2018-07-28 17:23:46', '2018-07-28 17:23:46');
INSERT INTO `menu` VALUES (2299351422468096, 2292984548687872, 0, '标签修改', NULL, 'tag:edit', NULL, 2, 2147483647, '2018-07-28 17:24:18', '2018-07-28 17:24:18');
INSERT INTO `menu` VALUES (2299479466180608, 2292984548687872, 0, '标签删除', NULL, 'tag:delete', NULL, 2, 2147483647, '2018-07-28 17:24:49', '2018-07-28 17:24:49');
INSERT INTO `menu` VALUES (2299794592628736, 2293127180189696, 0, '分类添加', NULL, 'node:add', NULL, 2, 2147483647, '2018-07-28 17:26:04', '2018-07-28 17:26:04');
INSERT INTO `menu` VALUES (2299928453840896, 2293127180189696, 0, '分类修改', NULL, 'node:edit', NULL, 2, 2147483647, '2018-07-28 17:26:36', '2018-07-28 17:26:36');
INSERT INTO `menu` VALUES (2300031717605376, 2293127180189696, 0, '分类删除', NULL, 'node:delete', NULL, 2, 2147483647, '2018-07-28 17:27:00', '2018-07-28 17:27:00');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` int(1) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 1, '超级管理员, 不要更改, 不要删除', NULL, '2018-07-27 19:15:06');
INSERT INTO `role` VALUES (1964917309571072, '管理员', 1, '管理员', '2018-07-27 19:15:23', '2018-07-27 19:15:23');
INSERT INTO `role` VALUES (1965150747754496, '前台用户', 1, '前台用户, 不能登陆后台', '2018-07-27 19:16:18', '2018-07-27 19:16:18');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `menu_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (2585036930940928, '1', '1');
INSERT INTO `role_menu` VALUES (2585036951912448, '1', '2');
INSERT INTO `role_menu` VALUES (2585036964495360, '1', '3');
INSERT INTO `role_menu` VALUES (2585036981272576, '1', '6');
INSERT INTO `role_menu` VALUES (2585036998049792, '1', '7');
INSERT INTO `role_menu` VALUES (2585037006438400, '1', '8');
INSERT INTO `role_menu` VALUES (2585037023215616, '1', '4');
INSERT INTO `role_menu` VALUES (2585037039992832, '1', '9');
INSERT INTO `role_menu` VALUES (2585037052575744, '1', '10');
INSERT INTO `role_menu` VALUES (2585037069352960, '1', '11');
INSERT INTO `role_menu` VALUES (2585037098713088, '1', '12');
INSERT INTO `role_menu` VALUES (2585037115490304, '1', '5');
INSERT INTO `role_menu` VALUES (2585037136461824, '1', '13');
INSERT INTO `role_menu` VALUES (2585037153239040, '1', '14');
INSERT INTO `role_menu` VALUES (2585037170016256, '1', '15');
INSERT INTO `role_menu` VALUES (2585037190987776, '1', '16');
INSERT INTO `role_menu` VALUES (2585037211959296, '1', '2291118888714240');
INSERT INTO `role_menu` VALUES (2585037228736512, '1', '2292719586115584');
INSERT INTO `role_menu` VALUES (2585037249708032, '1', '2298583143415808');
INSERT INTO `role_menu` VALUES (2585037266485248, '1', '2298737871290368');
INSERT INTO `role_menu` VALUES (2585037287456768, '1', '2298853235621888');
INSERT INTO `role_menu` VALUES (2585037304233984, '1', '2292984548687872');
INSERT INTO `role_menu` VALUES (2585037325205504, '1', '2299215044673536');
INSERT INTO `role_menu` VALUES (2585037341982720, '1', '2299351422468096');
INSERT INTO `role_menu` VALUES (2585037362954240, '1', '2299479466180608');
INSERT INTO `role_menu` VALUES (2585037379731456, '1', '2293127180189696');
INSERT INTO `role_menu` VALUES (2585037400702976, '1', '2299794592628736');
INSERT INTO `role_menu` VALUES (2585037413285888, '1', '2299928453840896');
INSERT INTO `role_menu` VALUES (2585037425868800, '1', '2300031717605376');
INSERT INTO `role_menu` VALUES (2589029790908416, '1964917309571072', '1');
INSERT INTO `role_menu` VALUES (2589029811879936, '1964917309571072', '2291118888714240');
INSERT INTO `role_menu` VALUES (2589029828657152, '1964917309571072', '2292719586115584');
INSERT INTO `role_menu` VALUES (2589029849628672, '1964917309571072', '2298583143415808');
INSERT INTO `role_menu` VALUES (2589029866405888, '1964917309571072', '2298737871290368');
INSERT INTO `role_menu` VALUES (2589029883183104, '1964917309571072', '2298853235621888');
INSERT INTO `role_menu` VALUES (2589029908348928, '1964917309571072', '2292984548687872');
INSERT INTO `role_menu` VALUES (2589029933514752, '1964917309571072', '2299215044673536');
INSERT INTO `role_menu` VALUES (2589029954486272, '1964917309571072', '2299351422468096');
INSERT INTO `role_menu` VALUES (2589029975457792, '1964917309571072', '2299479466180608');
INSERT INTO `role_menu` VALUES (2589029996429312, '1964917309571072', '2293127180189696');
INSERT INTO `role_menu` VALUES (2589030017400832, '1964917309571072', '2299794592628736');
INSERT INTO `role_menu` VALUES (2589030038372352, '1964917309571072', '2299928453840896');
INSERT INTO `role_menu` VALUES (2589030059343872, '1964917309571072', '2300031717605376');
INSERT INTO `role_menu` VALUES (2589055439077376, '1965150747754496', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int(1) NOT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `last_login_time` datetime(0) DEFAULT NULL,
  `last_login_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `login_count` int(11) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'cy2ut1oGQa8dGgLjKDMXeg==', '超级管理员', 'ROOT', '987176700@qq.com', NULL, NULL, NULL, 1, NULL, NULL, '2018-12-20 20:12:56', '127.0.0.1', 35);
INSERT INTO `user` VALUES (2582066428379136, 'admin', '9HuGVANUXXBy45KKEaL+Mg==', 'admin', 'ADMIN', NULL, NULL, NULL, NULL, 1, '2018-07-29 12:07:43', '2018-07-29 12:07:43', '2018-12-20 20:07:57', '127.0.0.1', 2);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2306968211423232, '2306968161091584', '1964917309571072');
INSERT INTO `user_role` VALUES (2588905291382784, '2582066428379136', '1964917309571072');
INSERT INTO `user_role` VALUES (2588924312551424, '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
