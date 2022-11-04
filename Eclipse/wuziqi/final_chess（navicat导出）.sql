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

 Date: 02/05/2022 23:14:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for allmatch
-- ----------------------------
DROP TABLE IF EXISTS `allmatch`;
CREATE TABLE `allmatch`  (
  `BlackNo` tinyint(3) UNSIGNED NULL DEFAULT NULL,
  `WhiteNo` tinyint(3) UNSIGNED NULL DEFAULT NULL,
  `MatchNo` tinyint(3) UNSIGNED NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bisailunci
-- ----------------------------
DROP TABLE IF EXISTS `bisailunci`;
CREATE TABLE `bisailunci`  (
  `qipansize` tinyint(128) UNSIGNED NULL DEFAULT NULL,
  `Number` tinyint(128) UNSIGNED NULL DEFAULT NULL,
  `firstplayerNo` int(10) UNSIGNED NULL DEFAULT NULL,
  `lastplayerNo` int(10) UNSIGNED NULL DEFAULT NULL,
  `chessbox` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `laststep` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `steps` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `winner` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bisailunci
-- ----------------------------
INSERT INTO `bisailunci` VALUES (10, 1, 2, 3, '0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,1,0,0,0,-1,-1,-1,0,0,0,0,0,0,0,-1,0,0,0,0,0,1,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,', '3,2', '(3,3),(2,7),(7,5),(10,7),(4,1),(7,3),(3,1),(6,5),(2,3),(7,1),(5,2),(4,7),(3,2),', NULL);

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code`  (
  `codeNo` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `playerNo` tinyint(50) NULL DEFAULT NULL,
  `pathFile` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`codeNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `playerNo` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Age` tinyint(3) UNSIGNED NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`playerNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
