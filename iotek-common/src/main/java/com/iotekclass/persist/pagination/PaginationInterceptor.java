/**
 * 
 */
package com.iotekclass.persist.pagination;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过拦截<code>StatementHandler</code>的<code>prepare</code>方法，重写sql语句实现物理分页。
 * 老规矩，签名里要拦截的类型只能是接口。
 * 该拦截器仅针对MySql
 * 
 * @author 王啸
 * 
 */
@Component
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final String DEFAULT_PAGE_SQL_ID = ".*Page$"; // 需要拦截的ID(正则匹配)

	@Override
	public Object intercept(Invocation invocation) throws Exception {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}

		// property在mybatis settings文件内配置
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		// 设置pageSqlId
		String pageSqlId = configuration.getVariables().getProperty("pageSqlId");
		if ((null == pageSqlId) || "".equals(pageSqlId)) {
			logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
			pageSqlId = DEFAULT_PAGE_SQL_ID;
		}

		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		// 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
		if (mappedStatement.getId().matches(pageSqlId)) {
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
				throw new Exception("parameterObject is null!");
			} else {
				String sql = boundSql.getSql();
				// 重写sql
				String pageSql = sql + " LIMIT " + rowBounds.getOffset() + "," + rowBounds.getLimit();
				metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
			}
		}
		// 将执行权交给下一个拦截器
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		logger.debug(properties.toString());
	}

}
