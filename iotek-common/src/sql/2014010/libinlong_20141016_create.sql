drop table if exists tb_teacher_show;

/*==============================================================*/
/* Table: tb_teacher                                            */
/*==============================================================*/
create table tb_teacher_show
(
   id                   int(11) not null auto_increment,
   teacher_id           int(11),
   teacher_name         varchar(50),
   type                 tinyint(1) comment '1:java
            2:androi
            3:C
            4:C++',
   level                tinyint(1) comment '1:普通
            2:中级
            3:高级
            4:资深',
   introduction         varchar(300),
   image_url            varchar(300),
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
