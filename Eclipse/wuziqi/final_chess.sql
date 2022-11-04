/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3307
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3307
 Source Schema         : final_chess

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 18/05/2022 19:19:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for allmatch
-- ----------------------------
DROP TABLE IF EXISTS `allmatch`;
CREATE TABLE `allmatch`  (
  `BlackNo` tinyint(3) UNSIGNED NOT NULL COMMENT '黑方编号',
  `WhiteNo` tinyint(3) UNSIGNED NOT NULL COMMENT '白方编号',
  `MatchNo` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '对局编号',
  PRIMARY KEY (`MatchNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bisailunci
-- ----------------------------
DROP TABLE IF EXISTS `bisailunci`;
CREATE TABLE `bisailunci`  (
  `qipansize` tinyint(128) UNSIGNED NULL DEFAULT NULL COMMENT '棋盘大小',
  `Number` tinyint(128) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '对局编号',
  `firstplayerNo` int(10) UNSIGNED NOT NULL COMMENT '先行方编号',
  `lastplayerNo` int(10) UNSIGNED NOT NULL COMMENT '后行方编号',
  `chessbox` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '棋盘情况存储',
  `laststep` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后落子',
  `steps` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '每步落子',
  `winner` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '胜者',
  PRIMARY KEY (`Number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code`  (
  `codeNo` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '算法编号',
  `playerNo` tinyint(50) NULL DEFAULT NULL COMMENT '棋手编号',
  `pathFile` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '算法存储路径',
  PRIMARY KEY (`codeNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `playerNo` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '棋手编号',
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '棋手用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '棋手密码',
  PRIMARY KEY (`playerNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
