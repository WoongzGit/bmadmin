package com.bmadmin.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmadmin.comment.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	Page<CommentEntity> findByBoardIdx(Pageable pageable, Long boardIdx);

	Page<CommentEntity> findAll(Pageable pageable);
}
