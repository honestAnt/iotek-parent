CREATE TABLE `tb_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `trade_status` int(11) DEFAULT NULL,
  `pay_fee` int(11) DEFAULT NULL,
  `actual_fee` varchar(11) DEFAULT NULL,
  `trade_type` varchar(11) DEFAULT NULL,
  `consign_time` timestamp NULL DEFAULT NULL,
  `trade_no` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_trade_rel_fk_idx` (`user_id`),
  KEY `order_trade_rel_fk_idx` (`order_id`),
  CONSTRAINT `order_trade_rel_fk` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_trade_rel_fk` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;