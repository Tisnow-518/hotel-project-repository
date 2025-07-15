

-- 创建数据库

create database hoteldb;


-- 创建用户并授权

create user hoteladmin identified by 'abc123';

grant all on hoteldb.* to hoteladmin;


-- 创建分店表

create table branch (
    branch_id       int primary key auto_increment,
    branch_name     varchar(40) not null,
    branch_address  varchar(200) not null,
    branch_phone    varchar(30) not null,
    room_count      int not null default 0,
    branch_pic_url  varchar(300),
    create_time     datetime not null,
    update_time     datetime,
    created_by      char(6) not null,
    updated_by      char(6),
    is_deleted      int not null
);


-- 插入分店数据

insert into branch (branch_name, branch_address, branch_phone, created_by, updated_by)
values
    ('北京朝阳店', '北京市朝阳区建国门外大街1号', '010-12345678', '000001', '000001'),
    ('上海浦东店', '上海市浦东新区陆家嘴环路1000号', '021-87654321', '000001', '000001'),
    ('广州天河店', '广州市天河区天河路123号', '020-11111111', '000001', '000001');


-- 创建房间表

create table room (
    room_id         int not null auto_increment,
    branch_id       int not null,
    room_no         varchar(20) not null,
    room_type       varchar(50) not null,
    room_facilities varchar(255) default null,
    room_status     varchar(20) not null default '未入住',
    room_remark     varchar(255) default null,
    create_time     datetime not null default current_timestamp,
    update_time     datetime default current_timestamp on update current_timestamp,
    created_by      char(6) not null,
    updated_by      char(6),
    is_deleted      int not null default 0
);



-- ========================================
-- 3. 用户表（user）
-- ========================================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码（加密）',
  `role` varchar(20) NOT NULL DEFAULT 'USER' COMMENT '角色',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除（0未删，1已删）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
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



-- 插入示例房间数据
INSERT INTO `room` (`room_branch_id`, `room_no`, `room_type`, `room_facilities`, `room_status`, `room_remark`, `created_by`, `is_deleted`) VALUES
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
    b.branch_id as branch_id,
    b.branch_name as branch_name,
    b.room_count as total_rooms,
    COUNT(r.room_id) as actual_rooms,
    SUM(CASE WHEN r.room_status = '未入住' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as available_rooms,
    SUM(CASE WHEN r.room_status = '有住客' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as occupied_rooms,
    SUM(CASE WHEN r.room_status = '已预订' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as reserved_rooms,
    SUM(CASE WHEN r.room_status = '保洁中' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as cleaning_rooms,
    SUM(CASE WHEN r.room_status = '已退房未保洁' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as checkout_rooms,
    SUM(CASE WHEN r.room_status = '维修中' AND r.is_deleted = 0 THEN 1 ELSE 0 END) as maintenance_rooms
FROM `branch` b
LEFT JOIN `room` r ON b.branch_id = r.room_branch_id AND r.is_deleted = 0
WHERE b.is_deleted = 0
GROUP BY b.branch_id, b.branch_name, b.room_count;

-- ========================================
-- 数据库初始化完成
-- ========================================
