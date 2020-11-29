package com.naicson.RedditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naicson.RedditClone.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
