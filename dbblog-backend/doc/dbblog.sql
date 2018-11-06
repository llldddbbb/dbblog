-- we don't know how to generate schema dbblog (class Schema) :(
create table blog_article
(
	blog_id int auto_increment comment '主键'
		primary key,
	title varchar(50) not null comment '文章标题',
	description varchar(255) null comment '文章描述',
	author varchar(50) null comment '文章作者',
	content longtext null comment '文章内容',
	read_num mediumtext null comment '阅读量',
	comment_num mediumtext null comment '评论量',
	like_num mediumtext null comment '点赞量',
	create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp null comment '更新时间'
)
comment '文章'
;

create table sys_captcha
(
	uuid char(36) not null comment 'uuid'
		primary key,
	code varchar(6) not null comment '验证码',
	expire_time datetime null comment '过期时间'
)
comment '系统验证码' charset=utf8
;

create table sys_menu
(
	menu_id bigint auto_increment
		primary key,
	parent_id bigint null comment '父菜单ID，一级菜单为0',
	name varchar(50) null comment '菜单名称',
	url varchar(200) null comment '菜单URL',
	perms varchar(500) null comment '授权(多个用逗号分隔，如：user:list,user:create)',
	type int null comment '类型   0：目录   1：菜单   2：按钮',
	icon varchar(50) null comment '菜单图标',
	order_num int null comment '排序'
)
comment '菜单管理' charset=utf8
;

create table sys_role
(
	role_id bigint auto_increment
		primary key,
	role_name varchar(100) null comment '角色名称',
	remark varchar(100) null comment '备注',
	create_user_id bigint null comment '创建者ID',
	create_time datetime null comment '创建时间'
)
comment '角色' charset=utf8
;

create table sys_role_menu
(
	id bigint auto_increment
		primary key,
	role_id bigint null comment '角色ID',
	menu_id bigint null comment '菜单ID'
)
comment '角色与菜单对应关系' charset=utf8
;

create table sys_user
(
	user_id int auto_increment comment '主键'
		primary key,
	username varchar(45) null comment '用户名',
	password varchar(255) null comment '密码',
	email varchar(45) null comment '邮箱',
	salt varchar(45) null comment '密码盐',
	create_user_id varchar(45) null comment '创建者Id',
	create_time datetime null comment '创建时间',
	status tinyint(2) default '1' null comment '0禁用，1正常'
)
;

create table sys_user_role
(
	id bigint auto_increment
		primary key,
	user_id bigint null comment '用户ID',
	role_id bigint null comment '角色ID'
)
comment '用户与角色对应关系' charset=utf8
;

create table sys_user_token
(
	user_id bigint not null
		primary key,
	token varchar(100) not null comment 'token',
	expire_time datetime null comment '过期时间',
	update_time datetime null comment '更新时间',
	constraint token
		unique (token)
)
comment '系统用户Token' charset=utf8
;
