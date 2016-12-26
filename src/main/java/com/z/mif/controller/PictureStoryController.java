package com.z.mif.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z.mif.constants.Constants;
import com.z.mif.entity.CommentEObj;
import com.z.mif.entity.Pagination;
import com.z.mif.entity.PictureStoryEObj;
import com.z.mif.entity.UserEObj;
import com.z.mif.service.ICommentService;
import com.z.mif.service.IPictureStoryService;
import com.z.mif.util.JSONUtil;
import com.z.mif.util.MathUtil;

@Controller
public class PictureStoryController extends BaseController {

	private final static Logger log = LoggerFactory
			.getLogger(PictureStoryController.class);

	@Autowired
	@Qualifier("pictureStoryService")
	private IPictureStoryService pictureStoryService;

	@Autowired
	@Qualifier("commentService")
	private ICommentService commentService;

	@RequestMapping("/list")
	public String list(PictureStoryEObj entity, HttpServletRequest request,HttpSession session,
			ModelMap map) throws Exception {
		PictureStoryEObj p;
		Document doc;
		String text;
		StringBuilder builder;
		int index = 0;
		char tmp;
		Pagination paging = pictureStoryService.list(entity,
				getPagination(request));
		for (Object o : paging.getDatas()) {
			p = (PictureStoryEObj) o;
			doc = Jsoup.parse(p.getContent());
			text = doc.text();
			// remove extra white space
			builder = new StringBuilder(text);
			index = 0;
			while (builder.length() > index) {
				tmp = builder.charAt(index);
				if (Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)) {
					builder.setCharAt(index, ' ');
				}
				index++;
			}
			text = builder.toString().replaceAll(" +", " ").trim();
			p.setContent(text);
		}
		map.addAttribute("paging", paging);
		map.addAttribute("entity", entity);
		return "index";
	}

	@RequestMapping("/s/masonry")
	public @ResponseBody String listAjax(PictureStoryEObj entity,
			HttpServletRequest request) throws Exception {
		Pagination paging = pictureStoryService.list(entity,
				getPagination(request));
		 String json = JSONUtil.uncode(paging.getDatas());
		 return json;
	}

	@RequestMapping("/u/story/w")
	public String write() {
		return "story-add";
	}

	@RequestMapping("/u/story/save")
	public @ResponseBody String add(PictureStoryEObj entity, HttpSession session)
			throws Exception {
		// TODO id生成的时候要杂乱无章 不能从1开始 id看起来要长 越长越叼
		UserEObj sessionUser = getSessionUser(session);
		entity.setId(MathUtil.getUnId(pictureStoryService));
		entity.setUserId(sessionUser.getId());
		entity.setBackgroundInd(entity.getBackgroundInd() == null ? Constants.N
				: Constants.Y);
		pictureStoryService.add(entity);
		return null;
	}

	@RequestMapping("/story/{id}")
	public String storty(@PathVariable String id, ModelMap map)
			throws Exception {
		List<CommentEObj> roots = null;
		Map<Integer, List<CommentEObj>> child = null;

		PictureStoryEObj story = pictureStoryService.get(id);
		CommentEObj c = new CommentEObj();
		c.setStoryId(id);
		c.setParentId(0);
		roots = commentService.list(c);// 得到所有根评论 先不用做分页吧？ 应该也不用做分页 毕竟小网站
											// )_(

		if (roots != null && roots.size() > 0) {
			child = new HashMap<Integer, List<CommentEObj>>();
			for (CommentEObj r : roots) {
				child.put(r.getId(), commentService.recursion(r.getId()));
			}

		}

		map.put("story", story);
		map.put("roots", roots);
		map.put("child", child);
		return "story";
	}

	@RequestMapping("/story/comment")
	public @ResponseBody Integer comment(CommentEObj comment, HttpSession session)
			throws Exception {
		Integer noticerId = null;
		comment.setUserId(getSessionUser(session).getId());
		commentService.add(comment);
		if(comment.getParentId() == 0){
			noticerId = pictureStoryService.get(comment.getStoryId()).getUserId();
		}else{
			noticerId = commentService.get(comment.getParentId()).getUserId();
		}
		return noticerId;
	}
}
