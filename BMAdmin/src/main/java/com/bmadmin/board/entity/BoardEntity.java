package com.bmadmin.board.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="board")
public class BoardEntity implements UserDetails{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "memberIdx", unique = true)
	private Long memberIdx;

	/* 이름 */
	@Column(name = "name", nullable=false)
	private String name;
	
	/* 이메일 */
	@Column(name = "email", unique = true, nullable=false)
	private String email;
	
	/* 아이디 */
	@Column(name = "id", unique = true, nullable=false)
	private String id;
	
	/* 비밀번호 */
	@Column(name = "password", nullable=false)
	private String password;
	
	/* 권한 */
	@Column(name = "auth", nullable=false)
	private String auth;
	
	/* 랭킹 */
	@Column(name = "ranking", nullable=true)
	private Integer ranking;
	
	/* 등록일자 */
	@Column(name = "regDate", nullable=true)
	private LocalDateTime createDate;
	
	/* 수정일자 */
	@Column(name = "modDate", nullable=true)
	private LocalDateTime modDate;
	
	/* 계정상태 */
	@Column(name = "memberState", nullable=false)
	private String memberState;
	
	public BoardEntity() {
		
	}
	
	public BoardEntity(Long memberIdx, String name, String email, String id, String password, 
						String auth, Integer ranking, LocalDateTime createDate,
						LocalDateTime modDate, String memberState) {
		this.memberIdx = memberIdx;
		this.name = name;
		this.email = email;
		this.id = id;
		this.password = password;
		this.auth = auth;
		this.ranking = ranking;
		this.createDate = createDate;
		this.modDate = modDate;
		this.memberState = memberState;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
	    for (String role : auth.split(",")) {
	      roles.add(new SimpleGrantedAuthority(role));
	    }
	    return roles;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}

	public String getMemberState() {
		return memberState;
	}

	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}
}
