/**
 * 
 */
package com.iotekclass.persist.common.spring;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * ClassName: BaseTestNGSTTestCase
 * Description： 所有事务单元测试的基类
 * Author：ZengAihui
 * Date：2013年8月30日 上午11:03:08
 * 
 * @version
 */
@DirtiesContext
@ActiveProfiles(Profiles.DEVELOPMENT)
@ContextConfiguration(locations = { "/conf/spring-context.xml", "/conf/spring-mybatis.xml" })
public abstract class BaseMapperTestNGSTTestCase extends TestNGSpringMapperTestCase {

}
