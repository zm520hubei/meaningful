package com.z.mif.PageContext;

public class MySqlDialect implements Dialect {

	
	/**
	 * <B>是否支持分页</B>
	 * @return {@link java.lang.Boolean}
	 */
	public boolean supportsLimit() {
		return true;
	}
	
	/**
	 * <B>获得分页SQL</B>
	 * @param sql 原SQL
	 * @param hasOffset {@link java.lang.Boolean} 是否包含分页起始位置
	 * @return {@link java.lang.String}
	 */
	public String getLimitString(String sql, boolean hasOffset) {
		return new StringBuffer(sql.trim()).append(hasOffset ? " limit ?,?" : " limit ?").toString();
	}
	/**
	 * <B>获得分页SQL</B>
	 * @param sql 原SQL
	 * @param offset {@link java.lang.Integer} 分页起始位置
	 * @param limit {@link java.lang.Integer} 每页显示记录数
	 * @return {@link java.lang.String}
	 */
	public String getLimitString(String sql, int offset, int limit) {
		return new StringBuffer(sql.trim()).append(" limit ").append(offset).append(",").append(limit).toString();
	}

}
