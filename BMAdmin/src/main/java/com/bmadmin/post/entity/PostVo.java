package com.bmadmin.post.entity;

import java.util.List;

import com.bmadmin.comment.entity.CommentEntity;
import com.bmadmin.common.vo.ResultVo;

public class PostVo {
	List<PostEntity> posts;
	
	PostEntity post;
	
	List<CommentEntity> comments;
	
	CommentEntity commentEntity;
	
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

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public CommentEntity getCommentEntity() {
		return commentEntity;
	}

	public void setCommentEntity(CommentEntity commentEntity) {
		this.commentEntity = commentEntity;
	}
}
