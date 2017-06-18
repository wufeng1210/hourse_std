/* 修改状态字段*/
ALTER TABLE hourse_info CHANGE state STATUS VARCHAR(10) DEFAULT '0';

/* 添加朝向字段*/
alter table hourse_info add orientations varchar(20) default '' COMMENT '朝向';

/* 添加楼层字段*/
alter table hourse_info add floor varchar(10) default '' comment '楼层';

/* 添加收藏字段*/
alter table hourse_info add collect varchar(10) default '0' comment '收藏（1：收藏；0：不收藏）';