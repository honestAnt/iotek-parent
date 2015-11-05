/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/16 17:39:32                          */
/*==============================================================*/


drop table if exists tb_courseware_comment;

drop table if exists tb_goods_comment;

/*==============================================================*/
/* Table: tb_courseware_comment                                 */
/*==============================================================*/
create table tb_courseware_comment
(
   id                   int(11) not null auto_increment,
   pid                  int(11),
   syllabus_id          int(11) not null,
   content              varchar(1000),
   user_id              int(11) not null,
   comment_time         timestamp not null default CURRENT_TIMESTAMP,
   useful_counts        int(11) not null default 0,
   comment_type         tinyint(1) not null,
   reply_user_id        int(11),
   reply_user           varchar(50),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_courseware_comment comment '视频评论表';

/*==============================================================*/
/* Table: tb_goods_comment                                      */
/*==============================================================*/
create table tb_goods_comment
(
   id                   int(11) not null auto_increment,
   goods_id             int(11) not null,
   content              varchar(1000),
   user_id              int(11) not null,
   comment_time         timestamp not null default CURRENT_TIMESTAMP,
   level                tinyint(1),
   useful_counts        int(11) not null default 0,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_goods_comment comment '商品评价表';

