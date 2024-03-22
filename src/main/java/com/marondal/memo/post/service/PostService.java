package com.marondal.memo.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.memo.post.domain.Post;
import com.marondal.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public int addPost(int userId, String title, String contents) {
		return postRepository.insertPost(userId, title, contents);
	}
	
	public List<Post> getPostList(int userId) {
		
		return postRepository.selectPostList(userId);
	}
	
	public Post getPost(int id) {
		return postRepository.selectPost(id);
	}

}
