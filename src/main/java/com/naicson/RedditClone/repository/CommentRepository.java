package com.naicson.RedditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naicson.RedditClone.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
