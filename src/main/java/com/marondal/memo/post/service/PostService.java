package com.marondal.memo.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.memo.common.FileManager;
import com.marondal.memo.post.domain.Post;
import com.marondal.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public int addPost(int userId, String title, String contents, MultipartFile file) {
		
		// 파일을 특정 위치에 저장한다. 
		String imagePath = FileManager.saveFile(userId, file);
		
		return postRepository.insertPost(userId, title, contents, imagePath);
	}
	
	public List<Post> getPostList(int userId) {
		
		return postRepository.selectPostList(userId);
	}
	
	public Post getPost(int id) {
		return postRepository.selectPost(id);
	}

}
