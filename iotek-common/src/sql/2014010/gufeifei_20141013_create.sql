/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/14 9:59:26                           */
/*==============================================================*/


drop table if exists tb_course;

drop table if exists tb_syllabus;

drop table if exists tb_courseware;

drop table if exists tb_course_syllabus_rel;

drop table if exists tb_courseware_syllabus_rel;

drop table if exists tb_dictionary;


/*==============================================================*/
/* Table: tb_course                                             */
/*==============================================================*/
create table tb_course
(
   id                   int(11) not null auto_increment,
   course_code          varchar(50) not null,
   course_name          varchar(100) not null,
   course_label         varchar(50),
   course_desc          text,
   picture_url          varchar(200),
   course_hours         int(11),
   course_count         int(11),
   objective            text,
   teacher_id           int(11),
   teacher_name         varchar(20),
   category_id          tinyint(2) not null,
   status               tinyint(1) not null,
   create_time          timestamp not null DEFAULT CURRENT_TIMESTAMP,
   create_user          varchar(20),
   update_time          timestamp,
   update_user          varchar(20),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_course comment '课程表';

/*==============================================================*/
/* Table: tb_syllabus                                           */
/*==============================================================*/
create table tb_syllabus
(
   id                   int(11) not null auto_increment,
   syllabus_code        varchar(20) not null,
   syllabus_pid         int(11),
   item_name            varchar(100),
   item_type            tinyint(1),
   emphasis             tinyint(1),
   status               tinyint(1) not null,
   sort_number          int(11) not null,
   if_free          	int(11) not null,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_syllabus comment '大纲表';

/*==============================================================*/
/* Table: tb_courseware                                         */
/*==============================================================*/
create table tb_courseware
(
   id                   int(11) not null auto_increment,
   courseware_code      varchar(20) not null,
   courseware_name      varchar(100),
   courseware_desc      text,
   courseware_type      tinyint(2) not null,
   duration             int(11),
   teacher_id           int(11),
   teacher_name         varchar(20),
   image_url            varchar(200),
   path_url             varchar(200),
   visit_count          int(11) not null default 0,
   knowledge_id			int(11),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_courseware comment '课件表';

/*==============================================================*/
/* Table: tb_course_syllabus_rel                                */
/*==============================================================*/
create table tb_course_syllabus_rel
(
   id                   int(11) not null auto_increment,
   course_id            varchar(20),
   syllabus_id          varchar(20),
   sort_number          int(11) not null,
   primary key (id),
   INDEX (course_id),
   INDEX (syllabus_id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_course_syllabus_rel comment '课程大纲关系表';

/*==============================================================*/
/* Table: tb_courseware_syllabus_rel                            */
/*==============================================================*/
create table tb_courseware_syllabus_rel
(
   id                   int(11) not null auto_increment,
   syllabus_id          varchar(20),
   courseware_id        varchar(20),
   sort_number          int(11) not null,
   primary key (id),
   INDEX (syllabus_id),
   INDEX (courseware_id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_courseware_syllabus_rel comment '课件大纲关系表';


/*==============================================================*/
/* Table: tb_dictionary                                         */
/*==============================================================*/
create table tb_dictionary
(
   id                   int(11) not null auto_increment,
   dic_code             tinyint(2),
   dic_name             varchar(50),
   dic_type             tinyint(3) not null,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table tb_dictionary comment '字典表';

