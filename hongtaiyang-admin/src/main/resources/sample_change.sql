--liquibase formatted sql

--changeset zhouyi:1
-- 创建用户表
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--rollback drop table user;

--changeset zhouyi:2
insert into user(id, name, age) values(1,'张三',29);
insert into user(id, name, age) values(2,'李四',20);
