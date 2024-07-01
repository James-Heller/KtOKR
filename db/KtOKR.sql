/*
 Navicat Premium Dump SQL

 Source Server         : 办公室MySQL
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : 192.168.1.5:3306
 Source Schema         : spring_rbac2

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 01/07/2024 17:36:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `objective_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `objective_id`(`objective_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`objective_id`) REFERENCES `objective` (`objective_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for dept_members
-- ----------------------------
DROP TABLE IF EXISTS `dept_members`;
CREATE TABLE `dept_members`  (
  `dept_id` int NOT NULL,
  `dept_member` json NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_members
-- ----------------------------
INSERT INTO `dept_members` VALUES (1, '[1]');
INSERT INTO `dept_members` VALUES (3, '[4]');
INSERT INTO `dept_members` VALUES (5, '[7]');

-- ----------------------------
-- Table structure for key_results
-- ----------------------------
DROP TABLE IF EXISTS `key_results`;
CREATE TABLE `key_results`  (
  `key_result_id` int NOT NULL AUTO_INCREMENT,
  `objective_id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `target_value` decimal(10, 2) NULL DEFAULT NULL,
  `current_value` decimal(10, 2) NULL DEFAULT NULL,
  `measurement_type` enum('NUMERIC','PERCENTAGE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('NOT_STARTED','IN_PROGRESS','ACHIEVED','MISSED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `progress` decimal(3, 2) NULL DEFAULT 0.00,
  `visibility` enum('PUBLIC','LEADER_ONLY') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`key_result_id`) USING BTREE,
  INDEX `objective_id`(`objective_id` ASC) USING BTREE,
  CONSTRAINT `key_results_ibfk_1` FOREIGN KEY (`objective_id`) REFERENCES `objective` (`objective_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of key_results
-- ----------------------------
INSERT INTO `key_results` VALUES (4, 5, 'Key Result 1', 'Key Result 1 description', 100.00, 0.00, 'PERCENTAGE', 'NOT_STARTED', 0.00, 'PUBLIC');
INSERT INTO `key_results` VALUES (5, 5, 'Key Result 2', 'Key Result 2 description', 200.00, 75.00, 'NUMERIC', 'NOT_STARTED', 0.00, 'PUBLIC');
INSERT INTO `key_results` VALUES (6, 6, 'Key Result 1', 'Key Result 1 description', 100.00, 0.00, 'PERCENTAGE', 'NOT_STARTED', 0.00, 'PUBLIC');
INSERT INTO `key_results` VALUES (7, 6, 'Key Result 2', 'Key Result 2 description', 200.00, 75.00, 'NUMERIC', 'NOT_STARTED', 0.00, 'PUBLIC');
INSERT INTO `key_results` VALUES (8, 7, 'Key Result 1', 'Key Result 1 description', 100.00, 0.00, 'PERCENTAGE', 'NOT_STARTED', 0.00, 'LEADER_ONLY');
INSERT INTO `key_results` VALUES (9, 7, 'Key Result 2', 'Key Result 2 description', 200.00, 75.00, 'NUMERIC', 'NOT_STARTED', 0.00, 'LEADER_ONLY');

-- ----------------------------
-- Table structure for objective
-- ----------------------------
DROP TABLE IF EXISTS `objective`;
CREATE TABLE `objective`  (
  `objective_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `dept_id` int NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `scope` enum('COMPANY','DEPARTMENT','PERSONAL') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('DRAFT','IN_PROGRESS','COMPLETED','CALCELLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `visibility` enum('PUBLIC','LEADER_ONLY') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`objective_id`) USING BTREE,
  INDEX `user_id`(`admin_id` ASC) USING BTREE,
  INDEX `dept_id`(`dept_id` ASC) USING BTREE,
  CONSTRAINT `objective_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `system_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `objective_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `system_dept` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of objective
-- ----------------------------
INSERT INTO `objective` VALUES (5, 1, 1, NULL, 'Objective 1', 'Objective 1 description', '2024-07-01', '2024-07-31', 'COMPANY', 'DRAFT', 'PUBLIC');
INSERT INTO `objective` VALUES (6, 3, 1, NULL, 'Objective 1', 'Objective 1 description', '2024-07-01', '2024-07-31', 'COMPANY', 'DRAFT', 'PUBLIC');
INSERT INTO `objective` VALUES (7, 7, 1, NULL, 'Objective 1', 'Objective 1 description', '2024-07-01', '2024-07-31', 'COMPANY', 'DRAFT', 'LEADER_ONLY');

-- ----------------------------
-- Table structure for objective_participant
-- ----------------------------
DROP TABLE IF EXISTS `objective_participant`;
CREATE TABLE `objective_participant`  (
  `participant_id` int NOT NULL AUTO_INCREMENT,
  `objective_id` int NOT NULL,
  `admin_id` int NOT NULL,
  `role` enum('owner','contributor') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`participant_id`) USING BTREE,
  INDEX `objective_id`(`objective_id` ASC) USING BTREE,
  INDEX `user_id`(`admin_id` ASC) USING BTREE,
  CONSTRAINT `objective_participant_ibfk_1` FOREIGN KEY (`objective_id`) REFERENCES `objective` (`objective_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `objective_participant_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `system_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of objective_participant
-- ----------------------------

-- ----------------------------
-- Table structure for okr_alignment
-- ----------------------------
DROP TABLE IF EXISTS `okr_alignment`;
CREATE TABLE `okr_alignment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `master_id` int NOT NULL,
  `slave_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of okr_alignment
-- ----------------------------

-- ----------------------------
-- Table structure for role_menus
-- ----------------------------
DROP TABLE IF EXISTS `role_menus`;
CREATE TABLE `role_menus`  (
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `role_menus_ibfk_2`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `role_menus_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menus_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `system_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menus
-- ----------------------------

-- ----------------------------
-- Table structure for role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions`  (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `role_permissions_ibfk_2`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `role_permissions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permissions_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `system_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permissions
-- ----------------------------
INSERT INTO `role_permissions` VALUES (1, 1);

-- ----------------------------
-- Table structure for system_dept
-- ----------------------------
DROP TABLE IF EXISTS `system_dept`;
CREATE TABLE `system_dept`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `primary_head` int NULL DEFAULT NULL,
  `secondary_head` int NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `第一负责人`(`primary_head` ASC) USING BTREE,
  INDEX `第二负责人`(`secondary_head` ASC) USING BTREE,
  CONSTRAINT `第一负责人` FOREIGN KEY (`primary_head`) REFERENCES `system_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `第二负责人` FOREIGN KEY (`secondary_head`) REFERENCES `system_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dept
-- ----------------------------
INSERT INTO `system_dept` VALUES (1, 'BOSS', NULL, NULL, NULL);
INSERT INTO `system_dept` VALUES (2, 'Leader1', NULL, NULL, 1);
INSERT INTO `system_dept` VALUES (3, 'Leader2', NULL, NULL, 1);
INSERT INTO `system_dept` VALUES (4, 'IT1', NULL, NULL, 1);
INSERT INTO `system_dept` VALUES (5, 'IT2', NULL, NULL, 1);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
INSERT INTO `system_permission` VALUES (1, 'READ', '读');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, 'ROLE_CREATOR', '开发者');
INSERT INTO `system_role` VALUES (2, 'ROLE_USER', '普通用户');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, 'JamesTang', '$2a$10$EIwOagpaFD4boF489SsRMuXh6zW7.zvGyh07s.M18jjhnWIFD8I2y', 'James-Heller@Outlook.com');
INSERT INTO `system_user` VALUES (3, 'Ashley Campbell', 'vtqRZ4HIqT', 'acampbell@icloud.com');
INSERT INTO `system_user` VALUES (4, 'Tang Sze Yu', 'lNeeyaxNkw', 'tangsy@gmail.com');
INSERT INTO `system_user` VALUES (5, 'Qiu Shihan', 'CaEc11Npi1', 'qiushihan@icloud.com');
INSERT INTO `system_user` VALUES (6, 'Thomas Guzman', '7MHptzqE7L', 'thomasguzman64@gmail.com');
INSERT INTO `system_user` VALUES (7, 'Dai Ka Keung', 'zAl46UFzUH', 'dakakeung@outlook.com');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 1);
INSERT INTO `user_roles` VALUES (1, 2);

SET FOREIGN_KEY_CHECKS = 1;
