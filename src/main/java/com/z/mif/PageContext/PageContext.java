package com.z.mif.PageContext;

import com.z.mif.entity.Pagination;


public class PageContext {
	
	private static ThreadLocal<Pagination> context = new ThreadLocal<Pagination>();

	public static void push(Pagination pagination) {
		context.set(pagination);
	}

	public static Pagination getPagination() {
		return context.get();
	}

	public static void unload() {
		context.remove();
	}
}
