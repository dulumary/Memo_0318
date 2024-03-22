package com.marondal.memo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marondal.memo.post.domain.Post;
import com.marondal.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/list-view")
	public String memoList(
			Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		
		List<Post> postList = postService.getPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "post/list";
	}

	@GetMapping("/create-view")
	public String inputMemo() {
		return "post/input";
	}
	
	@GetMapping("/detail-view")
	public String memoDetail(
			@RequestParam("id") int id
			, Model model) {
		
		Post post = postService.getPost(id);
		
		model.addAttribute("post", post);
		
		
		return "post/detail";
	}
}
