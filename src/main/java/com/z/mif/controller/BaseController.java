package com.z.mif.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.z.mif.constants.Constants;
import com.z.mif.entity.Pagination;
import com.z.mif.entity.UserEObj;

public class BaseController {

	protected Pagination getPagination(HttpServletRequest request) {
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		if (start == null)
			start = "0";
		if (limit == null)
			limit = "10";
		if (page == null)
			page = "1";
		return new Pagination(Integer.parseInt(start), Integer.parseInt(limit));
	}

	protected UserEObj getSessionUser(HttpSession session) {
		return (UserEObj) session.getAttribute(Constants.SESSION_USER);
	}
	
	public boolean isLogined(HttpSession session,String skipUrl){
		if(getSessionUser(session) == null){
			session.setAttribute(Constants.SESSION_URI, skipUrl);
			return false;
		}
		else return true;
	}
}
