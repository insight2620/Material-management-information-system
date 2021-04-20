/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : store_m

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 29/01/2021 04:37:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ex_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `ex_warehouse`;
CREATE TABLE `ex_warehouse`  (
  `goodsID` int(0) NOT NULL,
  `warehouseID` int(0) NOT NULL,
  `number` int(0) NOT NULL,
  `EX_time` date NOT NULL,
  PRIMARY KEY (`goodsID`, `warehouseID`, `number`, `EX_time`) USING BTREE,
  INDEX `warehouseID`(`warehouseID`) USING BTREE,
  CONSTRAINT `ex_warehouse_ibfk_1` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ex_warehouse_ibfk_2` FOREIGN KEY (`warehouseID`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ex_warehouse
-- ----------------------------
INSERT INTO `ex_warehouse` VALUES (1, 1, 3, '2021-01-29');
INSERT INTO `ex_warehouse` VALUES (1, 1, 6, '2021-01-29');
INSERT INTO `ex_warehouse` VALUES (1, 1, 8, '2021-01-29');
INSERT INTO `ex_warehouse` VALUES (1, 1, 12, '2019-12-04');
INSERT INTO `ex_warehouse` VALUES (1, 1, 31, '2019-12-04');
INSERT INTO `ex_warehouse` VALUES (1, 1, 100, '2019-12-07');
INSERT INTO `ex_warehouse` VALUES (1, 1, 100, '2021-01-07');
INSERT INTO `ex_warehouse` VALUES (1, 1, 100, '2021-01-22');
INSERT INTO `ex_warehouse` VALUES (20, 1, 25, '2021-01-29');
INSERT INTO `ex_warehouse` VALUES (2, 3, 31, '2019-12-04');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL,
  `name1` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sort` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `number` int(0) NOT NULL,
  `inputPrice` int(0) NOT NULL,
  `outputPrice` int(0) NOT NULL,
  `date_of_entry` date NOT NULL,
  `warehouse_id` int(0) NOT NULL,
  `supplier_id` int(0) NOT NULL,
  PRIMARY KEY (`id`, `warehouse_id`) USING BTREE,
  INDEX `supplier_id`(`supplier_id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `goods_ibfk_1`(`warehouse_id`) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplierId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '横拉杆总成', '汽车转向系统配件', 2707, 120, 150, '2020-06-13', 2, 1);
INSERT INTO `goods` VALUES (2, '中心拉杆', '汽车转向系统配件', 1510, 110, 150, '2020-06-13', 6, 1);
INSERT INTO `goods` VALUES (3, '转向主动臂', '汽车转向系统配件', 2301, 140, 150, '2020-06-14', 6, 1);
INSERT INTO `goods` VALUES (4, '转向从动臂', '汽车转向系统配件', 1630, 115, 150, '2020-06-15', 6, 1);
INSERT INTO `goods` VALUES (5, '转向器防尘套', '汽车转向系统配件', 1620, 110, 120, '2020-06-17', 6, 1);
INSERT INTO `goods` VALUES (6, '转向器总成', '汽车转向系统配件', 1610, 110, 120, '2020-06-18', 1, 1);
INSERT INTO `goods` VALUES (7, '转向减振器', '汽车转向系统配件', 1930, 120, 130, '2020-07-12', 1, 1);
INSERT INTO `goods` VALUES (8, '转向助力泵', '汽车转向系统配件', 1210, 120, 130, '2020-07-15', 1, 1);
INSERT INTO `goods` VALUES (9, '拉杆调节螺栓', '汽车转向系统配件', 1300, 120, 170, '2020-07-18', 1, 1);
INSERT INTO `goods` VALUES (10, '向从动臂衬套', '汽车转向系统配件', 2360, 120, 150, '2020-07-18', 1, 1);
INSERT INTO `goods` VALUES (11, '动力转向管', '汽车转向系统配件', 1151, 120, 150, '2020-07-19', 1, 1);
INSERT INTO `goods` VALUES (12, '转向器垫片', '汽车转向系统配件', 1530, 120, 150, '2020-07-20', 1, 1);
INSERT INTO `goods` VALUES (13, '变速箱支撑胶垫', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (14, '变速箱滤清器', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (15, '变速箱滤清器修包', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (16, '变速箱油底壳垫', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (17, '里程表线', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (18, '变速器操纵杆头', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (19, '选档杆', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (20, '变速器', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (21, '维动器轴', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (22, '里程表齿轮', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (23, '变速器油管', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (24, '换档操纵拉线', '汽车传动系统配件', 1121, 130, 150, '2020-08-12', 2, 2);
INSERT INTO `goods` VALUES (25, '油门线', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (26, '空气流量计', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (27, '化油器', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (28, '化油器法兰', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (29, '汽油滤清器', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (30, '汽油滤清器软管', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (31, '汽油泵', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (32, '汽油泵', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (33, '止动泵', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (34, '化油器垫片修理', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (35, '喷嘴', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (36, '燃油压力调节器', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (37, '喷油嘴', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (38, '油气分享器', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (39, '滤网', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (40, '油管', '汽车燃油系统配件', 980, 260, 300, '2020-10-20', 3, 3);
INSERT INTO `goods` VALUES (41, '水泵', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (42, '节温器', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (43, '节温器盖', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (44, '膨胀水箱', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (45, '风扇页', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (46, '风扇支架', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (47, '耦合器', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (48, '散热器', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (49, '散热器软管', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (50, '散热器风扇', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (51, '膨胀水箱盖', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (52, '水管', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (53, '水管座塞子', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (54, '管接头', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (55, '风扇护罩', '汽车冷却系统配件', 2032, 150, 220, '2020-11-18', 4, 4);
INSERT INTO `goods` VALUES (56, '转速传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (57, '相位传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (58, '刹车感应线', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (59, '水位传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (60, '温度传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (61, '机油压力开关', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (62, '热敏开关', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (63, '机油位置传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (64, '氧传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (65, '速度传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (66, '曲轴传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (67, '压力传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (68, '节气门位置传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (69, '爆震传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (70, '里程表传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (71, '凸轮轴传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);
INSERT INTO `goods` VALUES (72, '油压传感器', '汽车传感器系列配件', 3260, 30, 50, '2020-12-06', 5, 5);

-- ----------------------------
-- Table structure for in_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `in_warehouse`;
CREATE TABLE `in_warehouse`  (
  `goodsID` int(0) NOT NULL,
  `warehouseID` int(0) NOT NULL,
  `number` int(0) NOT NULL,
  `IN_time` date NOT NULL,
  `supplier_id` int(0) NOT NULL,
  PRIMARY KEY (`goodsID`, `warehouseID`, `number`, `IN_time`) USING BTREE,
  INDEX `warehouseID`(`warehouseID`) USING BTREE,
  CONSTRAINT `in_warehouse_ibfk_1` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `in_warehouse_ibfk_2` FOREIGN KEY (`warehouseID`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of in_warehouse
-- ----------------------------
INSERT INTO `in_warehouse` VALUES (1, 1, 3, '2021-01-29', 1);
INSERT INTO `in_warehouse` VALUES (1, 1, 8, '2021-01-29', 1);
INSERT INTO `in_warehouse` VALUES (1, 1, 10, '2021-01-29', 1);
INSERT INTO `in_warehouse` VALUES (1, 1, 36, '2020-12-04', 1);
INSERT INTO `in_warehouse` VALUES (1, 1, 45, '2021-01-29', 1);
INSERT INTO `in_warehouse` VALUES (1, 1, 56, '2021-01-29', 1);
INSERT INTO `in_warehouse` VALUES (1, 1, 100, '2020-12-04', 1);
INSERT INTO `in_warehouse` VALUES (3, 1, 36, '2020-12-04', 1);

-- ----------------------------
-- Table structure for manage
-- ----------------------------
DROP TABLE IF EXISTS `manage`;
CREATE TABLE `manage`  (
  `userID` int(0) NOT NULL,
  `goodsID` int(0) NOT NULL,
  `warehouseId` int(0) NOT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `warehouseId`(`warehouseId`) USING BTREE,
  INDEX `goodsID`(`goodsID`) USING BTREE,
  CONSTRAINT `manage_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `manage_ibfk_2` FOREIGN KEY (`warehouseId`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `manage_ibfk_3` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manage
-- ----------------------------
INSERT INTO `manage` VALUES (1, 1, 1);
INSERT INTO `manage` VALUES (2, 2, 1);
INSERT INTO `manage` VALUES (3, 3, 1);
INSERT INTO `manage` VALUES (4, 4, 1);
INSERT INTO `manage` VALUES (5, 5, 1);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `goodsID` int(0) NOT NULL,
  `current_number` int(0) NOT NULL,
  `warehouseId` int(0) NOT NULL,
  PRIMARY KEY (`goodsID`, `warehouseId`) USING BTREE,
  INDEX `warehouseId`(`warehouseId`) USING BTREE,
  CONSTRAINT `store_ibfk_1` FOREIGN KEY (`warehouseId`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `store_ibfk_2` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, 2610, 1);
INSERT INTO `store` VALUES (2, 1437, 1);
INSERT INTO `store` VALUES (3, 2301, 1);
INSERT INTO `store` VALUES (4, 1630, 1);
INSERT INTO `store` VALUES (5, 1620, 1);
INSERT INTO `store` VALUES (6, 1610, 1);
INSERT INTO `store` VALUES (7, 1930, 1);
INSERT INTO `store` VALUES (8, 1210, 1);
INSERT INTO `store` VALUES (9, 1300, 1);
INSERT INTO `store` VALUES (10, 2360, 1);
INSERT INTO `store` VALUES (11, 1151, 1);
INSERT INTO `store` VALUES (12, 1530, 1);
INSERT INTO `store` VALUES (13, 1121, 2);
INSERT INTO `store` VALUES (14, 1121, 2);
INSERT INTO `store` VALUES (15, 1121, 2);
INSERT INTO `store` VALUES (16, 1121, 2);
INSERT INTO `store` VALUES (17, 1121, 2);
INSERT INTO `store` VALUES (18, 1121, 2);
INSERT INTO `store` VALUES (19, 1121, 2);
INSERT INTO `store` VALUES (20, 1121, 2);
INSERT INTO `store` VALUES (21, 1121, 2);
INSERT INTO `store` VALUES (22, 1121, 2);
INSERT INTO `store` VALUES (23, 1121, 2);
INSERT INTO `store` VALUES (24, 1121, 2);
INSERT INTO `store` VALUES (25, 980, 3);
INSERT INTO `store` VALUES (26, 980, 3);
INSERT INTO `store` VALUES (27, 980, 3);
INSERT INTO `store` VALUES (28, 980, 3);
INSERT INTO `store` VALUES (29, 980, 3);
INSERT INTO `store` VALUES (30, 980, 3);
INSERT INTO `store` VALUES (31, 980, 3);
INSERT INTO `store` VALUES (32, 980, 3);
INSERT INTO `store` VALUES (33, 980, 3);
INSERT INTO `store` VALUES (34, 980, 3);
INSERT INTO `store` VALUES (35, 980, 3);
INSERT INTO `store` VALUES (36, 980, 3);
INSERT INTO `store` VALUES (37, 980, 3);
INSERT INTO `store` VALUES (38, 980, 3);
INSERT INTO `store` VALUES (39, 980, 3);
INSERT INTO `store` VALUES (40, 980, 3);
INSERT INTO `store` VALUES (41, 2032, 4);
INSERT INTO `store` VALUES (42, 2032, 4);
INSERT INTO `store` VALUES (43, 2032, 4);
INSERT INTO `store` VALUES (44, 2032, 4);
INSERT INTO `store` VALUES (45, 2032, 4);
INSERT INTO `store` VALUES (46, 2032, 4);
INSERT INTO `store` VALUES (47, 2032, 4);
INSERT INTO `store` VALUES (48, 2032, 4);
INSERT INTO `store` VALUES (49, 2032, 4);
INSERT INTO `store` VALUES (50, 2032, 4);
INSERT INTO `store` VALUES (51, 2032, 4);
INSERT INTO `store` VALUES (52, 2032, 4);
INSERT INTO `store` VALUES (53, 2032, 4);
INSERT INTO `store` VALUES (54, 2032, 4);
INSERT INTO `store` VALUES (55, 2032, 4);
INSERT INTO `store` VALUES (56, 3260, 5);
INSERT INTO `store` VALUES (57, 3260, 5);
INSERT INTO `store` VALUES (58, 3260, 5);
INSERT INTO `store` VALUES (59, 3260, 5);
INSERT INTO `store` VALUES (60, 3260, 5);
INSERT INTO `store` VALUES (61, 3260, 5);
INSERT INTO `store` VALUES (62, 3260, 5);
INSERT INTO `store` VALUES (63, 3260, 5);
INSERT INTO `store` VALUES (64, 3260, 5);
INSERT INTO `store` VALUES (65, 3260, 5);
INSERT INTO `store` VALUES (66, 3260, 5);
INSERT INTO `store` VALUES (67, 3260, 5);
INSERT INTO `store` VALUES (68, 3260, 5);
INSERT INTO `store` VALUES (69, 3260, 5);
INSERT INTO `store` VALUES (70, 3260, 5);
INSERT INTO `store` VALUES (71, 3260, 5);
INSERT INTO `store` VALUES (72, 3260, 5);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplierId` int(0) NOT NULL,
  `supplierName` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supplierAddress` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supplierPhone` int(0) NOT NULL,
  PRIMARY KEY (`supplierId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '宏新源机械设备公司', '陕西', 912345);
INSERT INTO `supplier` VALUES (2, '亚卡普机械设备公司', '广东', 963222);
INSERT INTO `supplier` VALUES (3, '荣鑫汽车配件公司', '北京', 932333);
INSERT INTO `supplier` VALUES (4, '盈辉汽车配件公司', '上海', 912341);
INSERT INTO `supplier` VALUES (5, '锦承电子有限公司', '宁夏', 922377);

-- ----------------------------
-- Table structure for supply
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply`  (
  `supplierID` int(0) NOT NULL,
  `goodsID` int(0) NOT NULL,
  `number` int(0) NOT NULL,
  `confirm` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`supplierID`) USING BTREE,
  INDEX `goodsID`(`goodsID`) USING BTREE,
  CONSTRAINT `supply_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `supply_ibfk_2` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supply
-- ----------------------------
INSERT INTO `supply` VALUES (1, 1, 2610, 1);
INSERT INTO `supply` VALUES (2, 13, 1121, 1);
INSERT INTO `supply` VALUES (3, 25, 980, 1);
INSERT INTO `supply` VALUES (4, 41, 2032, 1);
INSERT INTO `supply` VALUES (5, 56, 3260, 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(0) NOT NULL,
  `name` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` int(0) NOT NULL,
  `authority` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '段鑫绘', 123, '1');
INSERT INTO `users` VALUES (2, '陈鸿鑫', 1234, '2');
INSERT INTO `users` VALUES (3, '秦博', 2345, '3');
INSERT INTO `users` VALUES (4, '王红', 3456, '4');
INSERT INTO `users` VALUES (5, '李明', 6789, '2');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(0) NOT NULL,
  `name` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `area` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, '西安第一仓库', '西安');
INSERT INTO `warehouse` VALUES (2, '广州第二仓库', '广州');
INSERT INTO `warehouse` VALUES (3, '北京第三仓库', '北京');
INSERT INTO `warehouse` VALUES (4, '上海第四仓库', '上海');
INSERT INTO `warehouse` VALUES (5, '宁夏第五仓库', '宁夏');
INSERT INTO `warehouse` VALUES (6, '新疆第六仓库', '新疆');

-- ----------------------------
-- Procedure structure for check_ex_warehouse
-- ----------------------------
DROP PROCEDURE IF EXISTS `check_ex_warehouse`;
delimiter ;;
CREATE PROCEDURE `check_ex_warehouse`(in goodsid int,in startingdate date,in finaldate date)
begin
	select SUM(ex_warehouse.number),(SUM(ex_warehouse.number)*goods.outputPrice)
	FROM ex_warehouse,goods
	where ex_warehouse.EX_time >= startingdate and ex_warehouse.EX_time <= finaldate and ex_warehouse.goodsID = goods.id
	and ex_warehouse.goodsID = goodsid  ;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for check_in_warehouse
-- ----------------------------
DROP PROCEDURE IF EXISTS `check_in_warehouse`;
delimiter ;;
CREATE PROCEDURE `check_in_warehouse`(in goodsid int,in startingdate date,in finaldate date)
begin
	select SUM(in_warehouse.number),(SUM(in_warehouse.number)*goods.inputPrice)
	FROM in_warehouse,goods
	where in_warehouse.IN_time >= startingdate and in_warehouse.IN_time <= finaldate and in_warehouse.goodsID = goods.id
	and in_warehouse.goodsID = goodsid;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for in_param
-- ----------------------------
DROP PROCEDURE IF EXISTS `in_param`;
delimiter ;;
CREATE PROCEDURE `in_param`(in p_in int)
begin
   select p_in;
   set p_in=2;
   select P_in;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table ex_warehouse
-- ----------------------------
DROP TRIGGER IF EXISTS `ex_goods_to_goods`;
delimiter ;;
CREATE TRIGGER `ex_goods_to_goods` BEFORE INSERT ON `ex_warehouse` FOR EACH ROW UPDATE goods
SET goods.number = goods.number - new.number
where goods.id= new.goodsID and goods.warehouse_id = new.warehouseID
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table in_warehouse
-- ----------------------------
DROP TRIGGER IF EXISTS `in_goods_to_goods`;
delimiter ;;
CREATE TRIGGER `in_goods_to_goods` AFTER INSERT ON `in_warehouse` FOR EACH ROW update goods
set number = goods.number+(new.number)
where goods.id= new.goodsID and goods.warehouse_id = new.warehouseID
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
