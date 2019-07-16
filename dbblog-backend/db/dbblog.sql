create table article
(
  id int auto_increment comment '主键'
    primary key,
  title varchar(50) not null comment '文章标题',
  description varchar(255) null comment '文章描述',
  author varchar(50) null comment '文章作者',
  content longtext null comment '文章内容',
  content_format longtext null comment 'html的content',
  read_num int default '0' null comment '阅读量',
  comment_num int default '0' null comment '评论量',
  like_num int default '0' null comment '点赞量',
  cover_type int null comment '文章展示类别,1:普通，2：大图片，3：无图片',
  cover text null comment '封面',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
  recommend tinyint(1) default '0' not null comment '是否推荐文章',
  category_id varchar(50) null comment '分类类别存在多级分类，用逗号隔开',
  publish tinyint default '0' null comment '发布状态',
  top tinyint(1) default '0' null comment '是否置顶'
)
  comment '文章'
;

create table book
(
  id int auto_increment comment '主键'
    primary key,
  title varchar(100) null comment '标题',
  cover varchar(255) null comment '封面',
  author varchar(50) null comment '作者',
  category_id varchar(20) null comment '分类类别',
  recommend tinyint(1) default '0' null comment '是否推荐',
  publisher varchar(100) null comment '出版社',
  publish_date date null comment '出版日期',
  page_num int null comment '页数',
  grade double null comment '评分',
  description text null comment '简介',
  catalogue text null comment '原书目录',
  create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
  update_time datetime default CURRENT_TIMESTAMP null comment '更新时间',
  read_num int default '0' null comment '阅读量',
  comment_num int default '0' null comment '评论量',
  like_num int default '0' null comment '点赞量',
  publish tinyint(1) default '0' null comment '是否发布',
  progress int default '0' null comment '读书状态',
  reading tinyint(1) null comment '是否阅读'
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
  content_format longtext null comment 'html的context',
  read_num int default '0' null comment '阅读量',
  comment_num int default '0' null comment '评论量',
  like_num int default '0' null comment '点赞量',
  cover text null comment '封面',
  book_id int null comment '所属书本',
  chapter varchar(255) null comment '所属章节',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
  recommend tinyint(1) default '0' not null comment '是否推荐笔记',
  category_id varchar(50) null comment '分类类别存在多级分类，用逗号隔开',
  publish tinyint default '0' null comment '发布状态',
  cover_type int null comment '封面类型',
  top tinyint(1) null comment '是否置顶'
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

create table log_like
(
  id bigint auto_increment
    primary key,
  type varchar(50) null comment '点赞类型',
  params varchar(5000) null comment '请求参数',
  time bigint not null comment '执行时长(毫秒)',
  ip varchar(64) null comment 'IP地址',
  create_date datetime null comment '创建时间'
)
  comment '点赞日志'
;

create table log_view
(
  id bigint auto_increment
    primary key,
  type varchar(50) null comment '浏览类型',
  method varchar(200) null comment '请求方法',
  params varchar(5000) null comment '请求参数',
  time bigint not null comment '执行时长(毫秒)',
  ip varchar(64) null comment 'IP地址',
  create_date datetime null comment '创建时间'
)
  comment '阅读日志'
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

create table recommend
(
  id int auto_increment comment '主键'
    primary key,
  link_id int null comment '推荐的文章Id',
  type int null comment '推荐类型',
  order_num int default '0' null comment '顺序',
  title varchar(100) null comment '标题',
  top tinyint(1) default '0' null comment '置顶'
)
  comment '推荐'
;

create table sys_menu
(
  menu_id bigint null,
  parent_id bigint null,
  name tinytext null,
  url varchar(200) null,
  perms varchar(500) null,
  type int null,
  icon tinytext null,
  order_num int null
)
  comment '菜单管理'
;

create table sys_param
(
  id int null,
  par_key int null,
  par_value varchar(255) null,
  menu_url varchar(255) null,
  type varchar(255) null
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
  user_id int null,
  username tinytext null,
  password varchar(255) null,
  email tinytext null,
  salt tinytext null,
  create_user_id tinytext null,
  create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  status tinyint null
)
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

