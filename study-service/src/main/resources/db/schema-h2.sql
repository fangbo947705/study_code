DROP TABLE if exists t_user;

CREATE TABLE t_user(
id BIGINT(20) not null  comment '主键',
name VARCHAR(30) null default  null comment '姓名',
age int(11) null default null comment '年龄',
email varchar(50) null default  null comment '邮箱',
primary key(id)
)