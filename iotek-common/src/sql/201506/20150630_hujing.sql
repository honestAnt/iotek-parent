ALTER TABLE `iotek_v2`.`tb_goods_attribute` ADD COLUMN `promotion_effect` timestamp NULL comment '促销活动开始日期';
ALTER TABLE `iotek_v2`.`tb_goods_attribute` ADD COLUMN `promotion_expire` timestamp NULL comment '促销活动结束日期';
ALTER TABLE `iotek_v2`.`tb_goods_attribute` ADD COLUMN `promotion_price` double(11,2) NULL comment '促销活动价格';  