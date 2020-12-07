package com.bmadmin.post.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="post")
public class PostEntity {
	/* 게시물순번 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "postIdx", unique = true)
	private Long postIdx;
	
	/* 게시물명 */
	@Column(name = "postTitle", nullable=false)
	private String postTitle;

	/* 게시판순번 */
	@Column(name = "boardIdx", nullable=false)
	private Long boardIdx;
	
	/* 게시판명 */
	@Column(name = "boardName", nullable=false)
	private String boardName;
	
	/* 회원순번 */
	@Column(name = "memberIdx", nullable=false)
	private Long memberIdx;
	
	/* 이메일 */
	@Column(name = "email", nullable=false)
	private String email;
	
	/* 게시글 */
	@Column(name = "postContents", nullable=false)
	private String postContents;
	
	/* 등록일자 */
	@Column(name = "regDate", nullable=true)
	private LocalDateTime regDate;
	
	/* 수정일자 */
	@Column(name = "modDate", nullable=true)
	private LocalDateTime modDate;
	
	/* 수정관리자 */
	@Column(name = "modAdmin", nullable=true)
	private String modAdmin;
	
	/* 게시물상태 */
	@Column(name = "postState", nullable=false)
	private String postState;
	
	public PostEntity() {
		
	}
	
	public PostEntity(Long postIdx, Long boardIdx, String boardName, Long memberIdx, String email, String postContents,
						LocalDateTime regDate, LocalDateTime modDate, String postState, String postTitle, String modAdmin) {
		this.postIdx = postIdx;
		this.boardIdx = boardIdx;
		this.boardName = boardName;
		this.memberIdx = memberIdx;
		this.email = email;
		this.postContents = postContents;
		this.regDate = regDate;
		this.modDate = modDate;
		this.postState = postState;
		this.postTitle = postTitle;
		this.modAdmin = modAdmin;
	}

	public Long getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(Long postIdx) {
		this.postIdx = postIdx;
	}

	public Long getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(Long boardIdx) {
		this.boardIdx = boardIdx;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Long getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(Long memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostContents() {
		return postContents;
	}

	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}

	public String getPostState() {
		return postState;
	}

	public void setPostState(String postState) {
		this.postState = postState;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getModAdmin() {
		return modAdmin;
	}

	public void setModAdmin(String modAdmin) {
		this.modAdmin = modAdmin;
	}
}
