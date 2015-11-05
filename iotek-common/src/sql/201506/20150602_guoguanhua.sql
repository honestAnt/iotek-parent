
ALTER TABLE tb_goods ADD goods_level TINYINT(1) comment '商品等级 1 初级 2 中级 3 高级';

ALTER TABLE tb_goods_attribute ADD gold_ratio int(11) comment '赠送金币比例';

update tb_manage_resource set url = '/goodsManage/addGoods,/goodsManage/getLevelByparent,/goodsManage/uploadGoodsImage,/goodsManage/checkGoodsIsExist,/communityAnswerManage/uploadPicture,/goodsManage/saveGoods' where id= 284 ;

update tb_manage_resource set url = '/goodsManage/editGoods,/goodsManage/goodsView,/goodsManage/getLevelByparent,/goodsManage/uploadGoodsImage,/goodsManage/checkGoodsIsExist,/communityAnswerManage/uploadPicture,/goodsManage/saveGoods' where id= 286 ;