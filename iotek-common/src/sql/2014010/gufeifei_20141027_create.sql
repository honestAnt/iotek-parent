/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/27 11:16:34                          */
/*==============================================================*/


drop table if exists tb_exp_project;

drop table if exists tb_subject;

/*==============================================================*/
/* Table: tb_exp_project                                        */
/*==============================================================*/
create table tb_exp_project
(
   id                   int(11) not null auto_increment,
   goods_id             int(11),
   subject_id           int(11),
   suit_crowd           varchar(100),
   difficulty           varchar(2),
   see_syllabus         varchar(500),
   get_teacher          varchar(500),
   full_study           varchar(500),
   check_score          varchar(500),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_exp_project comment '体验课项目表';

/*==============================================================*/
/* Table: tb_subject                                            */
/*==============================================================*/
create table tb_subject
(
   id                   int(11) not null auto_increment,
   name                 varchar(100),
   status               tinyint(1) not null,
   sort_number          int(11) not null,
   intro                varchar(200),
   content              text,
   image_url            varchar(200),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_subject comment '学科表';

