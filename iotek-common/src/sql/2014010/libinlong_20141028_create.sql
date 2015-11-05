drop table if exists tb_user_exper_log;

/*==============================================================*/
/* Table: tb_user_exper_log                                     */
/*==============================================================*/
create table tb_user_exper_log
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   goods_id             int(11),
   exper_date           timestamp,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
