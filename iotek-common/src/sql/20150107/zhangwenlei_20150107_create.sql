/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/1/7 17:02:46                            */
/*==============================================================*/


drop table if exists tb_experience;

drop table if exists tb_experience_item;

drop table if exists tb_log_user_experience;

drop table if exists tb_probation;

/*==============================================================*/
/* Table: tb_experience                                         */
/*==============================================================*/
create table tb_experience
(
   id                   int(11) not null auto_increment,
   goods_ids            varchar(500),
   memo                 varchar(200),
   days_limit          int(11),
   experience_day       int(11),
   experience_count     int(11),
   active               int(2),
   active_time          timestamp,
   active_count         int(6),
   sale_count           int(6),
   update_user          int(11),
   update_time          timestamp,
   create_user          int(11),
   create_time          timestamp,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*==============================================================*/
/* Table: tb_experience_item                                    */
/*==============================================================*/
create table tb_experience_item
(
   id                   int(11) not null auto_increment,
   experience_id        int(11),
   experience_number    varchar(20),
   coupexperience_code  varchar(20),
   start_time           timestamp,
   end_time             timestamp,
   active               int(2),
   user_name            varchar(100),
   active_time          timestamp,
   create_time          timestamp,
   update_time          timestamp,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*==============================================================*/
/* Table: tb_log_user_experience                                */
/*==============================================================*/
create table tb_log_user_experience
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   coupexperience_code  varchar(20),
   status               int(4),
   create_time          timestamp,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*==============================================================*/
/* Table: tb_probation                                          */
/*==============================================================*/
create table tb_probation
(
   id                   int(11) not null auto_increment,
   experience_id       int(11),
   course_id            int(11),
   chapter_numbers      varchar(200),
   update_user          int(11),
   update_time          timestamp,
   create_user          int(11),
   create_time          timestamp,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

