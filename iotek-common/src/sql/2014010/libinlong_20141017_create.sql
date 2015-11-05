drop table if exists tb_goods_rel;

/*==============================================================*/
/* Table: tb_goods_rel                                          */
/*==============================================================*/
create table tb_goods_rel
(
   id                   int(11) not null auto_increment,
   goods_id             int(11),
   related_id           varchar(200) comment '相关商品id，多个以 '','' 隔开',
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

drop table if exists tb_user_view_log;

/*==============================================================*/
/* Table: tb_user_view_log                                      */
/*==============================================================*/
create table tb_user_view_log
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   user_ip              varchar(100),
   goods_id             int(11),
   operate_time         timestamp not null  DEFAULT CURRENT_TIMESTAMP,,
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
