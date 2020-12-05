package com.bmadmin.post.entity;

import java.util.List;

import com.bmadmin.common.vo.ResultVo;

public class PostVo {
	List<PostEntity> posts;
	
	PostEntity post;
	
	ResultVo resultVo;

	public List<PostEntity> getPosts() {
		return posts;
	}

	public void setPosts(List<PostEntity> posts) {
		this.posts = posts;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	public ResultVo getResultVo() {
		return resultVo;
	}

	public void setResultVo(ResultVo resultVo) {
		this.resultVo = resultVo;
	}
}
