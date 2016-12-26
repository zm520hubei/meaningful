package com.z.mif.PageContext;

/**
 * <B>数据库方言接口</B>
 * @author henry
 * @since 2013-11-10
 */
public interface Dialect {
	
	public boolean supportsLimit();

	public String getLimitString(String sql, boolean hasOffset);

	public String getLimitString(String sql, int offset, int limit);

}