
-- create schema --
CREATE SCHEMA `template` DEFAULT CHARACTER SET utf8 ;




CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` char(32) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  `state` char(1) DEFAULT '0' COMMENT '0:lock  1:unlock',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` int(11) NOT NULL,
  `brief` varchar(255) DEFAULT NULL,
  `content` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




