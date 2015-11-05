
drop table if exists tb_school;

/*==============================================================*/
/* Table: tb_goods_label                                        */
/*==============================================================*/
create table tb_school
(
   id                   int(11) not null auto_increment comment '主键ID',
   name              		varchar(100) comment '学校名称',
   intro			          varchar(1000) comment '学校简介',
   sort_number          int(11) comment '排序号',
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 comment '学校表';

drop table if exists tb_department;

/*==============================================================*/
/* Table: tb_goods_label                                        */
/*==============================================================*/
create table tb_department
(
   id                   int(11) not null auto_increment comment '主键ID',
   school_id            int(11) comment '所属学校ID',
   name              		varchar(100) comment '院系名称',
   intro			          varchar(1000) comment '院系简介',
   sort_number          int(11) comment '排序号',
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 comment '院系表';

drop table if exists tb_class;

/*==============================================================*/
/* Table: tb_goods_label                                        */
/*==============================================================*/
create table tb_class
(
   id                   int(11) not null auto_increment comment '主键ID',
   department_id        int(11) comment '所属院系ID',
   name              		varchar(100) comment '班级名称',
   intro			          varchar(1000) comment '班级简介',
   sort_number          int(11) comment '排序号',
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 comment '班级表';

drop table if exists tb_user_class_rel;

/*==============================================================*/
/* Table: tb_goods_label                                        */
/*==============================================================*/
create table tb_user_class_rel
(
   id                   int(11) not null auto_increment comment '主键ID',
   user_id              int(11) comment '用户ID',
   class_id             int(11) comment '班级ID',
   department_id        int(11) comment '院系ID',
   school_id            int(11) comment '学校ID',
   create_time          timestamp not null DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   create_user			    int(11) comment '创建人',
   primary key (id)
)ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 comment '用户班级表';

