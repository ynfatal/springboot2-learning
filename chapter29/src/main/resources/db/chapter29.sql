CREATE DATABASE /*!32312 IF NOT EXISTS*/`chapter29` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `chapter29`;

DROP TABLE IF EXISTS `sku`;

CREATE TABLE `sku` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `create_time` datetime(6) DEFAULT NULL,
    `goods_id` bigint(20) DEFAULT NULL,
    `goods_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `max` int(11) DEFAULT NULL,
    `picture` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `price` bigint(20) DEFAULT NULL,
    `properties` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `shop_id` bigint(20) DEFAULT NULL,
    `shop_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `status` int(11) DEFAULT NULL,
    `stock` int(11) DEFAULT NULL,
    `update_time` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sku` */

insert  into `sku`(`id`,`create_time`,`goods_id`,`goods_name`,`max`,`picture`,`price`,`properties`,`shop_id`,`shop_name`,`status`,`stock`,`update_time`) values (1,'2019-08-20 14:02:37.573000',111111,'帆布鞋',300,'http://...pic...123.png',55000,'白色;40',123457,'MiCai Shop',1,1000,'2019-08-20 14:02:37.573000'),(2,'2019-08-20 14:07:26.372000',222222,'太阳帽',300,'http://...pic...123.png',21000,'黑色',123457,'MiCai Shop',1,1000,'2019-08-20 14:07:26.372000'),(3,'2019-08-20 14:08:02.730000',222222,'太阳帽',200,'http://...pic...123.png',21000,'蓝色',123457,'MiCai Shop',1,1000,'2019-08-20 14:08:02.730000'),(4,'2019-08-20 14:20:06.744000',333333,'男士皮鞋',500,'http://...pic...123.png',221000,'40;黑色编织镂空款B1923913',123456,'Fatal Shop',1,1000,'2019-08-20 14:20:06.744000'),(5,'2019-08-20 14:21:00.800000',444444,'男士皮鞋',500,'http://...pic...123.png',221000,'39;黑色编织镂空款B1923913',123456,'Fatal Shop',0,1000,'2019-08-20 14:21:00.800000'),(6,'2019-08-20 14:21:56.445000',444444,'男士皮鞋',500,'http://...pic...123.png',221000,'43;黑色编织镂空款B1923913',123456,'Fatal Shop',-1,1000,'2019-08-20 14:21:56.445000'),(7,'2019-08-20 14:23:36.476000',444444,'限量款骑士领带',2,'http://...pic...123.png',521000,'',123456,'Fatal Shop',1,1000,'2019-08-20 14:23:36.476000');
