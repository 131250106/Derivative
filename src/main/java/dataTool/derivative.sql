create schema derivative;
use derivative;


CREATE TABLE `user` (
  `account` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`account`)
);


create  table `order_id_store`
(
 id long
);

insert into `order_id_store`(id) values(0);

CREATE TABLE `order` (
  `order_id` bigint not null,
  `client_account` varchar(50) not NULL,
  `deadLine` bigint DEFAULT NULL,
  `dealPrice` double DEFAULT NULL,
  `date` bigint DEFAULT NULL,
  `executePrice` double DEFAULT NULL,
  `updown` binary(8) DEFAULT NULL,
  `area` binary(8) DEFAULT NULL,
  `type` binary(8) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `payOff` double DEFAULT NULL,
  `obstacleRate` double DEFAULT NULL,
  `open` binary(8) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`client_account`) REFERENCES `user` (`account`)
) ;



CREATE TABLE `price_record` (
  `time` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`time`)
);

