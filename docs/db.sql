-- ========================================
-- Hotel Management System Database Schema
-- ========================================

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS hotel_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE hotel_management;

-- ========================================
-- 1. 分店表（branch）
-- ========================================
DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `branchid` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `name` varchar(100) NOT NULL COMMENT '分店名称',
  `address` varchar(255) NOT NULL COMMENT '分店地址',
  `phone` varchar(30) NOT NULL COMMENT '分店电话',
  `room_count` int NOT NULL DEFAULT 0 COMMENT '房间总数',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '分店照片URL',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除（0未删，1已删）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`branchid`),
  UNIQUE KEY `uk_branch_name` (`name`),
  KEY `idx_branch_name` (`name`),
  KEY `idx_branch_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分店信息表';

-- ========================================
-- 2. 房间表（room）
-- ========================================
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomid` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `room_branchid` int NOT NULL COMMENT '分店ID，外键',
  `room_no` varchar(20) NOT NULL COMMENT '房号',
  `room_type` varchar(50) NOT NULL COMMENT '房间类型',
  `facilities` varchar(255) DEFAULT NULL COMMENT '设施（逗号分隔或JSON）',
  `status` varchar(20) NOT NULL DEFAULT '未入住' COMMENT '当前状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除（0未删，1已删）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`roomid`),
  UNIQUE KEY `uk_room_branch_no` (`room_branchid`, `room_no`),
  KEY `idx_room_type` (`room_type`),
  KEY `idx_room_status` (`status`),
  KEY `idx_room_deleted` (`is_deleted`),
  CONSTRAINT `fk_room_branch` FOREIGN KEY (`room_branchid`) REFERENCES `branch` (`branchid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='房间信息表';

-- ========================================
-- 3. 用户表（user）
-- ========================================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码（加密）',
  `role` varchar(20) NOT NULL DEFAULT 'USER' COMMENT '角色',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除（0未删，1已删）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `uk_user_username` (`username`),
  KEY `idx_user_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

-- ========================================
-- 初始化数据
-- ========================================

-- 插入默认管理员用户（密码：admin123，实际使用时应该加密）
INSERT INTO `user` (`username`, `password`, `role`, `is_deleted`) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXIGkqJGtIBPTYBJTzVmyaOZIna', 'ADMIN', 0),
('manager', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXIGkqJGtIBPTYBJTzVmyaOZIna', 'MANAGER', 0);

-- 插入示例分店数据
INSERT INTO `branch` (`name`, `address`, `phone`, `room_count`, `photo_url`, `created_by`, `is_deleted`) VALUES
('北京朝阳店', '北京市朝阳区建国门外大街1号', '010-12345678', 50, 'https://example.com/photos/beijing.jpg', 'admin', 0),
('上海浦东店', '上海市浦东新区陆家嘴环路1000号', '021-87654321', 80, 'https://example.com/photos/shanghai.jpg', 'admin', 0),
('广州天河店', '广州市天河区天河路123号', '020-11111111', 60, 'https://example.com/photos/guangzhou.jpg', 'admin', 0);

-- 插入示例房间数据
INSERT INTO `room` (`room_branchid`, `room_no`, `room_type`, `facilities`, `status`, `remark`, `created_by`, `is_deleted`) VALUES
-- 北京朝阳店房间
(1, '101', '普单人间', '平面液晶电视,空调,互联网接入', '未入住', '朝南房间，采光良好', 'admin', 0),
(1, '102', '普双人间', '平面液晶电视,冰箱,空调,互联网接入', '有住客', '', 'admin', 0),
(1, '201', '商务套房', '平面液晶电视,冰箱,空调,卫星电视,互联网接入', '未入住', '商务区域，适合商务人士', 'admin', 0),
(1, '301', '贵宾套房', '平面液晶电视,冰箱,空调,卫星电视,互联网接入,冲浪浴缸,观海景', '已预订', 'VIP房间', 'admin', 0),

-- 上海浦东店房间
(2, '101', '普单人间', '平面液晶电视,空调,互联网接入', '未入住', '', 'admin', 0),
(2, '102', '普双人间', '平面液晶电视,冰箱,空调,互联网接入', '保洁中', '', 'admin', 0),
(2, '201', '三人间', '平面液晶电视,冰箱,空调,互联网接入', '未入住', '适合家庭入住', 'admin', 0),
(2, '301', '贵宾套房', '平面液晶电视,冰箱,空调,卫星电视,互联网接入,冲浪浴缸', '维修中', '空调需要维修', 'admin', 0),

-- 广州天河店房间
(3, '101', '普单人间', '平面液晶电视,空调,互联网接入', '未入住', '', 'admin', 0),
(3, '102', '普双人间', '平面液晶电视,冰箱,空调,互联网接入', '已退房未保洁', '', 'admin', 0),
(3, '201', '商务套房', '平面液晶电视,冰箱,空调,卫星电视,互联网接入', '未入住', '', 'admin', 0);

-- ========================================
-- 创建视图和索引优化
-- ========================================

-- 创建房间统计视图
CREATE VIEW `v_branch_room_stats` AS
SELECT
    b.branchid as branch_id,
    b.name as branch_name,
    b.room_count as total_rooms,
    COUNT(r.roomid) as actual_rooms,
    SUM(CASE WHEN r.status = '未入住' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as available_rooms,
    SUM(CASE WHEN r.status = '有住客' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as occupied_rooms,
    SUM(CASE WHEN r.status = '已预订' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as reserved_rooms,
    SUM(CASE WHEN r.status = '保洁中' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as cleaning_rooms,
    SUM(CASE WHEN r.status = '已退房未保洁' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as checkout_rooms,
    SUM(CASE WHEN r.status = '维修中' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as maintenance_rooms
FROM `branch` b
LEFT JOIN `room` r ON b.branchid = r.room_branchid AND r.is_deleted = 0
WHERE b.is_deleted = 0
GROUP BY b.branchid, b.name, b.room_count;

-- ========================================
-- 数据库初始化完成
-- ========================================
