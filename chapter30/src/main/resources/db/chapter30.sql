CREATE DATABASE /*!32312 IF NOT EXISTS*/`chapter30` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `chapter30`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_bin,
  `name` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `shop_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `max` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `goods` */

insert  into `goods`(`id`,`content`,`name`,`price`,`shop_id`,`shop_name`,`stock`,`picture`,`create_time`,`update_time`,`status`,`max`) values (1,'冻的','雪碧',22000,123456,'Fatal Shop',100,'http://...pic...123.png','2019-08-14 18:08:17','2019-08-14 18:08:17',1,1000),(2,'也是冻的','柠檬茶',11000,123456,'Fatal Shop',100,'http://...pic...123.png','2019-08-15 17:54:52','2019-08-15 17:54:52',1,1000),(3,'冻的冻的。。。','果粒奶优',12000,123456,'Fatal Shop',100,'http://...pic...123.png','2019-08-15 17:57:31','2019-08-15 17:57:31',0,1000),(4,'冻的。','果粒奶优',11000,123457,'MiCai Shop',100,'http://...pic...123.png','2019-08-15 17:58:45','2019-08-15 17:58:45',1,1000),(5,'鲜牛奶好喝又健康...','纯牛奶',8000,123457,'MiCai Shop',100,'http://...pic...123.png','2019-08-15 18:00:13','2019-08-15 18:00:13',1,1000),(6,'爽快...','菠萝啤',15000,123457,'MiCai Shop',100,'http://...pic...123.png','2019-08-15 18:02:21','2019-08-15 18:02:21',1,1000);
