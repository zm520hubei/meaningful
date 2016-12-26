package com.z.mif.util;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateFormat {
	public static final String DATE_ISO_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT = "000000";
	public static final String TIME_ISO_FORMAT = "HH:mm:ss";
	public static final String DATETIME_ISO_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_COMPACT_FORMAT = "yyyyMMdd";
	public static final String TIME_COMPACT_FORMAT = "HHmmss";
	public static final String DATETIME_COMPACT_FORMAT = "yyyyMMddHHmmss";

	private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>();
	private static Log log = LogFactory.getLog(DateFormat.class);

	/**
	 * <B>取得线程安全的SimpleDateFormat</B>
	 * 
	 * @author XYY
	 * @since 2013年12月18日
	 * 
	 * @param pattern
	 *            格式化参数
	 * @return
	 */
	public static SimpleDateFormat getDateFormat(String pattern) {
		if (sdf.get() == null) {
			sdf.set(new SimpleDateFormat());
		}
		SimpleDateFormat local = sdf.get();
		local.applyPattern(pattern);
		return local;
	}

	public static String format(Date date) {
		if (date instanceof Timestamp) {
			return getDateFormat(DATETIME_ISO_FORMAT).format(date);
		} else {
			return getDateFormat(DATE_ISO_FORMAT).format(date);
		}
	}

	public static String format(Date date, String format) {
		return getDateFormat(format).format(date);
	}
	
	public static Timestamp parseISODatetime(String dateStr) {
		return (java.sql.Timestamp) parse(dateStr, getDateFormat(DATETIME_ISO_FORMAT), java.sql.Timestamp.class);
	}

	public static Date parseISODate(String dateStr) {
		return (java.util.Date) parse(dateStr, getDateFormat(DATE_ISO_FORMAT), java.util.Date.class);
	}

	public static java.sql.Date parseISOSqlDate(String dateStr) {
		return (java.sql.Date) parse(dateStr, getDateFormat(DATE_ISO_FORMAT), java.sql.Date.class);
	}

	public static Date parseCompactDate(String dateStr) {
		return (Date) parse(dateStr, getDateFormat(DATE_COMPACT_FORMAT), java.util.Date.class);
	}

	@SuppressWarnings("all")
	public static Date parse(String dateStr, java.text.DateFormat dateFormat, Class clazz) {
		Date ret = null;
		try {
			ret = dateFormat.parse(dateStr);
			try {
				Constructor c = clazz.getConstructor(new Class[] { long.class });
				return (Date) c.newInstance(new Object[] { new Long(ret.getTime()) });
			} catch (Exception e) {
				return null;
			}
		} catch (ParseException e) {
			log.warn("date format error! format:" + ((SimpleDateFormat) dateFormat).toPattern() + ",input:" + dateStr, e);
			return null;
		}
	}

}
