-- we don't know how to generate schema dbblog (class Schema) :(
create table article
(
	id int auto_increment comment '主键'
		primary key,
	title varchar(50) not null comment '文章标题',
	description varchar(255) null comment '文章描述',
	author varchar(50) null comment '文章作者',
	content longtext null comment '文章内容',
	read_num int default '0' null comment '阅读量',
	comment_num int default '0' null comment '评论量',
	like_num int default '0' null comment '点赞量',
	cover_type int null comment '文章展示类别,1:普通，2：大图片，3：无图片',
	cover text null comment '封面',
	create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
	recommend tinyint(1) default '0' not null comment '是否推荐文章',
	category_id varchar(50) null comment '分类类别存在多级分类，用逗号隔开',
	publish tinyint default '0' null comment '发布状态'
)
comment '文章'
;

create table book
(
	id int auto_increment comment '主键'
		primary key,
	title int null comment '标题',
	cover varchar(255) null comment '封面',
	author varchar(50) null comment '作者',
	category_id varchar(20) null comment '分类类别',
	recommend tinyint(1) default '0' null comment '是否推荐',
	publisher varchar(100) null comment '出版社',
	publish_date date null comment '出版日期',
	page_num int null comment '页数',
	grade double null comment '评分',
	introduction text null comment '简介',
	catalogue text null comment '原书目录',
	create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
	update_time datetime default CURRENT_TIMESTAMP null comment '更新时间',
	read_num int default '0' null comment '阅读量',
	comment_num int default '0' null comment '评论量',
	like_num int default '0' null comment '点赞量',
	publish tinyint(1) default '0' null comment '是否发布',
	progress int default '0' null comment '读书状态'
)
comment '图书表'
;

create table book_note
(
	id int auto_increment comment '主键'
		primary key,
	title varchar(50) not null comment '笔记标题',
	description varchar(255) null comment '笔记描述',
	author varchar(50) null comment '笔记作者',
	content longtext null comment '笔记内容',
	read_num int default '0' null comment '阅读量',
	comment_num int default '0' null comment '评论量',
	like_num int default '0' null comment '点赞量',
	cover text null comment '封面',
	book_id int null comment '所属书本',
	chapter varchar(255) null comment '所属章节',
	create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
	recommend tinyint(1) default '0' not null comment '是否推荐笔记',
	category_id varchar(50) null comment '分类类别存在多级分类，用逗号隔开',
	publish tinyint default '0' null comment '发布状态'
)
comment '笔记'
;

create table book_sense
(
	id int auto_increment comment '主键'
		primary key,
	author varchar(20) null comment '作者',
	content text null comment '内容',
	book_id int null comment '关联图书Id',
	create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment '读后感'
;

create table category
(
	id int auto_increment comment '主键',
	name varchar(255) null comment '名称',
	type int null comment '类型：0文章，1阅读',
	rank int null comment '级别',
	parent_id int default '0' null comment '父主键',
	constraint operation_category_id_uindex
		unique (id)
)
;

alter table category
	add primary key (id)
;

create table comment
(
	id int auto_increment comment '主键'
		primary key,
	nick_name varchar(50) null comment '昵称',
	email varchar(255) null comment '邮箱',
	content text null comment '评论内容',
	parent_id int null comment '关联父Id',
	link_id int null comment '关联Id',
	like_num int default '0' null comment '点赞数量',
	dislike_num int default '0' null comment '不喜欢数量',
	comment_level int default '0' null comment '评论层级: 0：第一层，1：第二层，2：第三层',
	create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	type int null comment '评论类型：0文章，1，阅读'
)
comment '评论'
;

create table link
(
	id int auto_increment comment '主键'
		primary key,
	title varchar(50) null comment '链接名称',
	url varchar(500) null comment '链接地址',
	avatar varchar(255) null comment '头像'
)
comment '友链'
;

create table oss_resource
(
	id int auto_increment comment '主键'
		primary key,
	name varchar(255) null comment '名称',
	url varchar(255) null
)
comment '云存储资源表'
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
comment '菜单管理'
;

create table sys_param
(
	id int auto_increment comment '主键'
		primary key,
	par_key int null comment '参数键',
	par_value varchar(255) null comment '参数值',
	menu_url varchar(255) null comment '参数url',
	type varchar(255) null comment '参数类型'
)
comment '系统参数'
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
comment '角色'
;

create table sys_role_menu
(
	id bigint auto_increment
		primary key,
	role_id bigint null comment '角色ID',
	menu_id bigint null comment '菜单ID'
)
comment '角色与菜单对应关系'
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
charset=latin1
;

create table sys_user_role
(
	id bigint auto_increment
		primary key,
	user_id bigint null comment '用户ID',
	role_id bigint null comment '角色ID'
)
comment '用户与角色对应关系'
;

create table tag
(
	id int auto_increment
		primary key,
	name varchar(50) null comment '标签名字',
	type int null comment '所属类别：0文章，1类别'
)
comment '标签'
;

create table tag_link
(
	id int auto_increment comment '主键'
		primary key,
	tag_id int null comment '标签Id',
	link_id int null comment '关联Id',
	type int null comment '所属类别：0文章，1阅读'
)
comment '标签多对多维护表'
;

