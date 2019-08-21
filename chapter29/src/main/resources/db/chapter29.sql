/*!40101 SET NAMES utf8 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`chapter29` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `chapter29`;

DROP TABLE IF EXISTS `sku`;

CREATE TABLE `sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'skuId',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
  `max` int(11) DEFAULT NULL COMMENT '购物车单种sku允许最大个数',
  `picture` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku图片',
  `price` bigint(20) DEFAULT NULL COMMENT 'sku单价',
  `properties` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku规格',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `shop_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名',
  `status` int(11) DEFAULT NULL COMMENT 'sku状态：-1 下架; 0 删除; 1 在架',
  `stock` int(11) DEFAULT NULL COMMENT 'sku库存',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sku` */

insert  into `sku`(`id`,`goods_id`,`goods_name`,`max`,`picture`,`price`,`properties`,`shop_id`,`shop_name`,`status`,`stock`,`create_time`,`update_time`) values (1,111111,'帆布鞋',300,'http://...pic...123.png',55000,'白色;40',123457,'MiCai Shop',1,1000,'2019-08-20 14:02:38','2019-08-20 14:02:38'),(2,222222,'太阳帽',300,'http://...pic...123.png',21000,'黑色',123457,'MiCai Shop',1,1000,'2019-08-20 14:07:26','2019-08-20 14:07:26'),(3,222222,'太阳帽',200,'http://...pic...123.png',21000,'蓝色',123457,'MiCai Shop',1,1000,'2019-08-20 14:08:03','2019-08-20 14:08:03'),(4,333333,'男士皮鞋',500,'http://...pic...123.png',221000,'40;黑色编织镂空款B1923913',123456,'Fatal Shop',1,1000,'2019-08-20 14:20:07','2019-08-20 14:20:07'),(5,444444,'男士皮鞋',500,'http://...pic...123.png',221000,'39;黑色编织镂空款B1923913',123456,'Fatal Shop',0,1000,'2019-08-20 14:21:01','2019-08-20 14:21:01'),(6,444444,'男士皮鞋',500,'http://...pic...123.png',221000,'43;黑色编织镂空款B1923913',123456,'Fatal Shop',-1,1000,'2019-08-20 14:21:56','2019-08-20 14:21:56'),(7,444444,'限量款骑士领带',2,'http://...pic...123.png',521000,'',123456,'Fatal Shop',1,1000,'2019-08-20 14:23:36','2019-08-20 14:23:36'),(8,431235,'女生西装',100,'http://...pic...123.png',1121000,'L;灰色',123458,'MiQI Shop',1,1000,'2019-08-21 16:46:19','2019-08-21 16:46:19'),(9,431312,'男士西装',100,'http://...pic...123.png',911000,'XL;灰色',123458,'MiQI Shop',1,1000,'2019-08-21 16:47:50','2019-08-21 16:47:50'),(10,221312,'男士中邦皮靴',200,'http://...pic...123.png',111000,'40;豹纹',123458,'MiQI Shop',1,1000,'2019-08-21 16:49:34','2019-08-21 16:49:34'),(11,151353,'胸针',10,'http://...pic...123.png',211000,'',123458,'MiQI Shop',1,1000,'2019-08-21 16:51:15','2019-08-21 16:51:15'),(12,151312,'胸针',10,'http://...pic...123.png',191000,'骑士蓝',123457,'MiCai Shop',1,1000,'2019-08-21 16:53:05','2019-08-21 16:53:05');
