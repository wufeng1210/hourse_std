ALTER TABLE hourse_info ADD preLendUserId VARCHAR(200) DEFAULT '' COMMENT '预租人id';
            
ALTER TABLE hourse_info ADD nowLendUserId VARCHAR(200) DEFAULT '' COMMENT '已租人id';

ALTER TABLE activity_info CHANGE state STATUS VARCHAR(10) DEFAULT '0';

CREATE TABLE collect_info (
  collectId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键id',
  userId VARCHAR(20) NOT NULL COMMENT '用户id',
  hourseId VARCHAR(20) NOT NULL COMMENT '房屋id',
  collectTime DATETIME NOT NULL
);


alter table collect_info add column isCollect varchar(10) default '' comment '收藏，0：不收藏；1：收藏';
