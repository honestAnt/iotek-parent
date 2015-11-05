drop table if exists tb_goods;

/*==============================================================*/
/* Table: tb_goods                                              */
/*==============================================================*/
create table tb_goods
(
   id                   int(11) not null auto_increment,
   goods_code           varchar(45) not null,
   goods_name           varchar(50),
   goods_label          varchar(50),
   image_url            varchar(100),
   status               tinyint(1) comment '1：正常
            0：停用
            ',
   sort_number          int(11),
   guidance             longtext,
   type_id              tinyint(1) comment '1：学习
            2：考试
            ',
   category_id          tinyint(1) comment '1：java
            2：android
            3：C语言
            4：c++
            ',
   create_time          timestamp not null  DEFAULT CURRENT_TIMESTAMP,
   create_user          varchar(50),
   update_time          timestamp,
   update_user          varchar(50),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

drop table if exists tb_goods_attribute;

/*==============================================================*/
/* Table: tb_goods_attribute                                    */
/*==============================================================*/
create table tb_goods_attribute
(
   id                   int(11) not null auto_increment,
   goods_id             int(11) not null,
   buy_url              varchar(300),
   days                 int(11) comment '从激活开始计算',
   deadline             timestamp comment '商品停止服务的截至时间',
   period               int(11),
   price                int(11),
   sale_price           int(11),
   discount_rate        double(3,2),
   content              longtext,
   intro                varchar(1000),
   exp_salary           varchar(200),
   promotion_url        varchar(300),
   page_views           int(11) comment 'xx人有兴趣',
   comments_number      int(11),
   Free_number          int(11) comment 'xx人试看',
   level                int(11),
   feedback_rate        tinyint(2),
   purchase_number      int(11) comment 'xx人购买',
   promotion_visits     int(11),
   pay_method           tinyint(1) comment '1：支付宝
            2：网银
            ',
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


drop table if exists tb_goods_label;

/*==============================================================*/
/* Table: tb_goods_label                                        */
/*==============================================================*/
create table tb_goods_label
(
   id                   int(11) not null auto_increment,
   content              varchar(100) not null,
   category_id          int(1),
   heat                int(11),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

drop table if exists tb_goods_label_rel;

/*==============================================================*/
/* Table: tb_goods_label_rel                                    */
/*==============================================================*/
create table tb_goods_label_rel
(
   id                   int(11) not null auto_increment,
   goods_id             int(11) not null,
   label_id             int(11) not null,
   primary key (id),
   INDEX ( goods_id) ,
   INDEX ( label_id) 
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

drop table if exists tb_goods_element_rel;

/*==============================================================*/
/* Table: tb_goods_element_rel                                  */
/*==============================================================*/
create table tb_goods_element_rel
(
   id                   int(11) not null auto_increment,
   goods_id             int(11) not null,
   element_id           int(11) not null,
   type                 tinyint(1) not null comment '1:课程
            2:认证
            3:服务',
   sort_number          int(11) not null,
   primary key (id),
   INDEX ( goods_id) ,
   INDEX ( element_id) 
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

