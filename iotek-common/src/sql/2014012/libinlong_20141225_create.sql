drop table if exists tb_user_exper_file_log;

/*==============================================================*/
/* Table: tb_user_exper_file_log                                */
/*==============================================================*/
create table tb_user_exper_file_log
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   file_id              int(11),
   goods_id             int(11),
   topic_id             int(11),
   type                 tinyint(1),
   operate_time         timestamp default CURRENT_TIMESTAMP,
   upload_count         tinyint(1),
   primary key (id)
)ENGINE = InnoDB
charset = UTF8;
