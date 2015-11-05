drop table if exists tb_exper_topic;

/*==============================================================*/
/* Table: tb_exper_topic                                        */
/*==============================================================*/
create table tb_exper_topic
(
   id                   int(11) not null auto_increment,
   goods_id             int(11),
   topic_id             int(11),
   primary key (id)
)ENGINE = InnoDB
charset = UTF8;

drop table if exists tb_exper_guidance;

/*==============================================================*/
/* Table: tb_exper_guidance                                     */
/*==============================================================*/
create table tb_exper_guidance
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   goods_id             int(11),
   guidance             varchar(2000),
   teacher_id           int(11),
   create_time          timestamp default CURRENT_TIMESTAMP,
   primary key (id)
)ENGINE = InnoDB
charset = UTF8;

drop table if exists tb_user_exper_topic_rel;

/*==============================================================*/
/* Table: tb_user_exper_topic_rel                               */
/*==============================================================*/
create table tb_user_exper_topic_rel
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   goods_id             int(11),
   topic_id             int(11),
   create_time          timestamp default CURRENT_TIMESTAMP,
   submit_time          timestamp,
   upload_count         tinyint(1),
   status               tinyint(1) comment '1:未上传
            2:待批阅
            3:批阅中
            4:已批阅
            ',
   score                tinyint(3),
   teacher_comment      varchar(2000),
   read_teacher         int(11),
   read_time            timestamp,
   primary key (id)
)ENGINE = InnoDB
charset = UTF8;
