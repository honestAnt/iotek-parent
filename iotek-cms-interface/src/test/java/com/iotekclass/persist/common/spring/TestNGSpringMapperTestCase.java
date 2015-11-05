package com.iotekclass.persist.common.spring;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Spring的支持数据库访问, 事务控制和依赖注入的Test 集成测试基类.
 * 相比Spring原基类名字更短并保存了dataSource变量.
 * 
 * 子类需要定义applicationContext文件的位置, 如:
 * 
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 * @author ZengAihui
 * 
 */
@ActiveProfiles(Profiles.UNIT_TEST)
public abstract class TestNGSpringMapperTestCase extends AbstractTestNGSpringContextTests {

}
