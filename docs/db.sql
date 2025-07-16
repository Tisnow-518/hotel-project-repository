

-- 创建数据库

create database hoteldb;


-- 创建用户并授权

create user hoteladmin identified by 'abc123';

grant all on hoteldb.* to hoteladmin;


-- 创建分店表

create table branch (
    branch_id       int primary key auto_increment,
    branch_name     varchar(100) not null,
    branch_address  varchar(255) not null,
    branch_phone    varchar(30) not null,
    room_count      int not null default 0,
    branch_pic_url  varchar(255),
    create_time     datetime not null default current_timestamp,
    update_time     datetime not null default current_timestamp on update current_timestamp,
    created_by      char(6) not null default '000001',
    updated_by      char(6) not null default '000001',
    is_deleted      int not null default 0
);


-- 插入分店数据

insert into branch (branch_name, branch_address, branch_phone)
values
    ('北京朝阳店', '北京市朝阳区建国门外大街1号', '010-12345678'),
    ('上海浦东店', '上海市浦东新区陆家嘴环路1000号', '021-87654321'),
    ('广州天河店', '广州市天河区天河路123号', '020-11111111');


-- 创建房间表

create table room (
    room_id         int primary key auto_increment,
    branch_id       int not null,
    room_no         varchar(20) not null,
    room_type       varchar(50) not null,
    room_facilities varchar(255),
    room_status     varchar(20) not null default '未入住',
    room_remark     varchar(255),
    create_time     datetime not null default current_timestamp,
    update_time     datetime not null default current_timestamp on update current_timestamp,
    created_by      char(6) not null default '000001',
    updated_by      char(6) not null default '000001',
    is_deleted      int not null default 0,
    foreign key (branch_id) references branch (branch_id)
);


-- 增删房间触发器

    delimiter //

    create trigger after_room_insert
    after insert on room
    for each row
    begin
        update branch
        set room_count = room_count + 1
        where branch_id = new.branch_id;
    end //

    create trigger after_room_delete
    after delete on room
    for each row
    begin
        update branch
        set room_count = room_count - 1
        where branch_id = old.branch_id;
    end //

    delimiter ;


-- 插入房间数据

insert into room (branch_id ,room_no, room_type)
values
    (1, '101', '普单人间'),
    (1, '102', '普双人间'),
    (1, '201', '商务套房'),
    (1, '301', '贵宾套房'),
    (2, '101', '普单人间'),
    (2, '102', '普双人间'),
    (2, '201', '三人间'),
    (2, '301', '贵宾套房'),
    (3, '101', '普单人间'),
    (3, '102', '普双人间'),
    (3, '201', '商务套房');


-- 创建用户表

create table user (
    user_id         int primary key auto_increment,
    user_name       varchar(50) not null,
    user_password   varchar(100) not null,
    user_role       varchar(20) not null default 'user',
    create_time     datetime not null default current_timestamp,
    update_time     datetime not null default current_timestamp on update current_timestamp,
    created_by      char(6) not null default '000001',
    updated_by      char(6) not null default '000001',
    is_deleted      int not null default 0
);


-- 插入用户数据
insert into user (user_name, user_password, user_role)
values
    ('admin', 'adc123', 'admin'),
    ('manager', 'abc123', 'manager');

