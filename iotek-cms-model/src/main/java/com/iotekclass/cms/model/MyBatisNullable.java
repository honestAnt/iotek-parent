/**
 * 
 */
package com.iotekclass.cms.model;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识MyBatis的可能为空的数据库字段，方便分析字段
 * 
 * @author Jdonee
 * 
 */
@Target({ METHOD, FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyBatisNullable {
	String value() default "";
}
