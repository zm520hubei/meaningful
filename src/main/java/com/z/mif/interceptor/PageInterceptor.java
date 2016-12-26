package com.z.mif.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.z.mif.PageContext.Dialect;
import com.z.mif.PageContext.MySqlDialect;
import com.z.mif.PageContext.PageContext;
import com.z.mif.entity.Pagination;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
	MappedStatement.class, Object.class, RowBounds.class,
	ResultHandler.class }) })
public class PageInterceptor implements Interceptor {
	
	private final static Logger logger = LoggerFactory
			.getLogger(PageInterceptor.class);

	Dialect dialect = new MySqlDialect();

	public Object intercept(Invocation invocation) throws Throwable {

		MappedStatement mappedStatement = (MappedStatement) invocation
				.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String originalSql = boundSql.getSql().trim();
		RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
		Object parameterObject = boundSql.getParameterObject();
		int total = 0;
		
		if (boundSql == null || boundSql.getSql() == null || "".equals(boundSql.getSql())) return null;

		// 从分页上下文中获得分页参数
		Pagination page = PageContext.getPagination();

		// 看是否存在分页参数
		if (page != null) {
			logger.debug(mappedStatement.getId() + "进行分页查询.");
			String countSql = formatCountSQL(originalSql);
			logger.debug("############## count sql:" + countSql);
			Connection conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			PreparedStatement countStmt = conn.prepareStatement(countSql);
			BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql,countSql);
			DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBS);
			parameterHandler.setParameters(countStmt);
			ResultSet rs = countStmt.executeQuery();
			
			if (rs != null && rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			countStmt.close();
			conn.close();
			page.setTotal(total);
			if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
				rowBounds = new RowBounds(page.getStart(), page.getLimit());
			}
			// 分页查询 本地化对象 修改数据库注意修改实现
			String pagesql = dialect.getLimitString(originalSql,
					rowBounds.getOffset(), rowBounds.getLimit());
			invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);
			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pagesql,boundSql.getParameterMappings(),boundSql.getParameterObject());
			MetaObject boundSqlMeta = SystemMetaObject.forObject(boundSql);
			MetaObject newBoundSqlMeta = SystemMetaObject.forObject(newBoundSql);
			newBoundSqlMeta.setValue("metaParameters",boundSqlMeta.getValue("metaParameters"));
			MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));
			invocation.getArgs()[0] = newMs;
		}
		// 卸载上下文中的分页信息
		PageContext.unload();
		return invocation.proceed();

	}

	/**
	 * 复制BoundSql对象
	 */
	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
			String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
				boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop,
						boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	public String formatCountSQL(String originalSql) {
		String sql = originalSql.toUpperCase().replaceAll("\n", "");
		int end = sql.indexOf("FROM");
		return "SELECT COUNT(1) FROM ( SELECT 1 " + sql.substring(end) + " ) TOTALAS";
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties arg0) {

	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}

}
