-- 课程表分类,1为主课程 0 为普通课程
ALTER TABLE tb_course ADD is_main TINYINT(1) comment '是否主课程';
update tb_course set is_main = 0 ;
update tb_course set is_main = 1 where id in (1,2,3,4,5,6,7) ;