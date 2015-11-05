/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/2/2 13:08:11                            */
/*==============================================================*/


drop table if exists tb_buy_goods_log;

/*==============================================================*/
/* Table: tb_buy_goods_log                                      */
/*==============================================================*/
create table tb_buy_goods_log
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   goods_id             int(11),
   buy_type             int(4),
   buy_time             timestamp,
   primary key (id),
   INDEX (user_id)
)ENGINE = InnoDB
charset = UTF8;

alter table tb_buy_goods_log comment '�û�������Ʒ��¼��';


drop table if exists tb_user_compile_log;

/*==============================================================*/
/* Table: tb_user_compile_log                                   */
/*==============================================================*/
create table tb_user_compile_log
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   compile_id           int(11),
   submit_time          timestamp,
   primary key (id),
   INDEX (user_id)
);

alter table tb_user_compile_log comment '�û�ʹ�����߱�����־��';


