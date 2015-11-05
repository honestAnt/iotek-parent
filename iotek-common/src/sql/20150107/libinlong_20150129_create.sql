create table tb_goods_teacher_rel
(
   id                   int(11) not null auto_increment comment '主键ID',
   goods_id             int(11) comment '商品ID',
   teacher_id          	int(11) comment '教师ID',
   sort_number          int(11) comment '排序号',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商品教师关系表';
