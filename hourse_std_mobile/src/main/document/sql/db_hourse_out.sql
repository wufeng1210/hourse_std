/*
SQLyog Ultimate v8.32 
MySQL - 5.7.17-log : Database - db_hourse_out
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_hourse_out` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_hourse_out`;

/*Table structure for table `activity_info` */

DROP TABLE IF EXISTS `activity_info`;

CREATE TABLE `activity_info` (
  `activityId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `activityTitle` varchar(20) DEFAULT NULL COMMENT '活动标题',
  `activityImagePath` varchar(20) DEFAULT NULL COMMENT '活动图片路径',
  `activityImageUrl` varchar(20) DEFAULT NULL COMMENT '活动图片地址',
  `target` varchar(20) DEFAULT NULL COMMENT '内链还是外链',
  `state` varchar(20) DEFAULT NULL COMMENT '状态（上架：1，下架：0）',
  PRIMARY KEY (`activityId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `hourse_info` */

DROP TABLE IF EXISTS `hourse_info`;

CREATE TABLE `hourse_info` (
  `hourseId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `userId` int(11) NOT NULL COMMENT '外键(用户id)',
  `hourseAddr` varchar(40) DEFAULT '' COMMENT '房屋地址',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '房屋所在经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '房屋所在纬度',
  `province` varchar(20) DEFAULT '' COMMENT '省份',
  `city` varchar(20) DEFAULT '' COMMENT '城市',
  `area` varchar(20) DEFAULT '' COMMENT '区域',
  `residentialQuarters` varchar(20) DEFAULT '' COMMENT '房屋所在小区',
  `roomNum` int(11) DEFAULT '0' COMMENT '房间数量',
  `toiletNum` int(11) DEFAULT '0' COMMENT '卫生间数量',
  `hallNum` int(11) DEFAULT '0' COMMENT '大厅数量',
  `kitchenNum` int(11) DEFAULT '0' COMMENT '厨房数量',
  `monthly` int(11) DEFAULT '0' COMMENT '月租（元）',
  `packingingLot` tinyint(1) DEFAULT '0' COMMENT '是否有车位',
  `rentingWay` varchar(20) DEFAULT '' COMMENT '租房方式',
  `limitType` varchar(20) DEFAULT '' COMMENT '限制方式（限制男生还是女生还是不限制）',
  `fixtureType` varchar(20) DEFAULT NULL COMMENT '装修方式',
  `brokerMobile` varchar(20) DEFAULT '' COMMENT '经纪人手机号',
  `brokerCode` varchar(20) DEFAULT '' COMMENT '经纪人编号',
  `brokerName` varchar(20) DEFAULT '' COMMENT '经纪人姓名',
  `areaCovered` int(11) DEFAULT '0' COMMENT '占比面积（平方）',
  `squarePrice` varchar(20) DEFAULT '' COMMENT '价格（平方）',
  `furniture` varchar(20) DEFAULT '' COMMENT '家具',
  `near` varchar(20) DEFAULT '' COMMENT '周边',
  `traffic` varchar(20) DEFAULT '' COMMENT '交通',
  `state` varchar(20) DEFAULT '0' COMMENT '状态（0：未审核；1：审核通过；2：审核打回）',
  `description` varchar(20) DEFAULT '' COMMENT '描述',
  `recommend` varchar(10) DEFAULT '0' COMMENT '是否推荐（1：为推荐，其他数据不推荐）',
  `isLend` varchar(10) DEFAULT '0' COMMENT '是否已出租（1：为已出租，其他数据未出租）',
  PRIMARY KEY (`hourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `image_info` */

DROP TABLE IF EXISTS `image_info`;

CREATE TABLE `image_info` (
  `imageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `hourseId` varchar(11) DEFAULT NULL COMMENT '房屋id',
  `imagePath` varchar(200) DEFAULT '' COMMENT '图片存储路径',
  `imageUrl` varchar(200) DEFAULT '' COMMENT '图片访问链接',
  `imageName` varchar(20) DEFAULT '' COMMENT '图片名称',
  `imageType` varchar(20) DEFAULT '' COMMENT '图片类型',
  `imageDesc` varchar(20) DEFAULT '' COMMENT '图片备注',
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `user_auth` */

DROP TABLE IF EXISTS `user_auth`;

CREATE TABLE `user_auth` (
  `authId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `authName` varchar(20) NOT NULL COMMENT '权限名称',
  `authPath` varchar(20) NOT NULL COMMENT '权限跳转链接',
  `parentId` varchar(20) NOT NULL COMMENT '父节点',
  `authDescription` varchar(20) NOT NULL COMMENT '角色描述',
  `state` varchar(20) DEFAULT NULL COMMENT '是否展开子节点',
  PRIMARY KEY (`authId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `userName` varchar(20) NOT NULL COMMENT '用户名',
  `userPassWord` varchar(20) NOT NULL COMMENT '用户密码',
  `secretKey` varchar(20) NOT NULL COMMENT '密钥',
  `userType` varchar(20) NOT NULL COMMENT '用户类型（1：前台用户；2：后台用户）',
  `roleId` int(11) NOT NULL COMMENT '外键（角色id）',
  `userDescription` varchar(20) DEFAULT '',
  `deptName` varchar(20) DEFAULT '' COMMENT '部门',
  `agent` varchar(20) DEFAULT '' COMMENT '公司',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `roleName` varchar(20) NOT NULL COMMENT '角色名称',
  `authIds` varchar(20) NOT NULL COMMENT '权限id集合',
  `roleDescription` varchar(20) DEFAULT '' COMMENT '角色描述',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/* Procedure structure for procedure `test` */

/*!50003 DROP PROCEDURE IF EXISTS  `test` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `test`(in s int)
select max(hourseId) from hourse_info where hourseId = s */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
