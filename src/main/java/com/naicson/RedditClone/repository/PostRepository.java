package com.naicson.RedditClone.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naicson.RedditClone.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
}
