package com.bmadmin.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmadmin.post.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
	Page<PostEntity> findByBoardIdx(Pageable pageable, Long boardIdx);

	Page<PostEntity> findAll(Pageable pageable);
}
