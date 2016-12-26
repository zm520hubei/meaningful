package com.z.mif.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z.mif.entity.NoteEObj;
import com.z.mif.entity.Pagination;
import com.z.mif.service.INoteService;
import com.z.mif.util.JSONUtil;
import com.z.mif.util.MathUtil;

/**
 * 
 * @author Sayid
 *
 */
@Controller
public class NoteController  extends BaseController{
	
	@Autowired
	@Qualifier("noteService")
	private INoteService noteService;
	
	@RequestMapping("/n/ls")
	public String list(NoteEObj entity, HttpServletRequest request, ModelMap map) throws Exception{
		Pagination pagination = noteService.list(entity, getPagination(request));
		map.put("paging", pagination);
		map.put("entity", entity);
		return "note";
	}
	
	@RequestMapping("/n/masonry")
	public @ResponseBody String listAjax(NoteEObj entity,HttpServletRequest request) throws Exception{
		Pagination paging = noteService.list(entity,getPagination(request));
		String json = JSONUtil.uncode(paging.getDatas());
		return json;
	}
	
	/**
	 * 检测当前用户是否登录
	 */
	@RequestMapping("/n/il")
	public @ResponseBody boolean isLogined(HttpSession session, @RequestParam("skipUrl") String skipUrl){
		return super.isLogined(session, skipUrl);
	}
	
	
	@RequestMapping(value="/u/n/sv",method=RequestMethod.POST)
	public @ResponseBody String save(NoteEObj entity, HttpSession session) throws Exception{
		entity.setId(MathUtil.getUnId(noteService));
		entity.setUserId(getSessionUser(session).getId());
		noteService.add(entity);
		return null;
	}
}
