create table tb_user_deal_info
(
   id                   int(11) not null auto_increment comment '主键ID',
   gold_count           int(11) not null comment '交易金币',
   deal_source          int(4) not null comment '如注册，回答问题等',
   deal_type            int(1) not null comment '收入/支出',
   deal_comment         varchar(200) comment '交易说明',
   create_user          int(11) not null comment '操作人id',
   create_time         timestamp not null comment '创建时间',
   update_user          int(11) not null comment '更新人',
   update_time          timestamp not null comment '更新时间',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户金币交易记录表';
