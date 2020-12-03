package com.bmadmin.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmadmin.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	MemberEntity findByEmail(String email);
	
	MemberEntity findById(String Id);
	
	Page<MemberEntity> findAll(Pageable pageable);
}
