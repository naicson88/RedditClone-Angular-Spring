package com.naicson.RedditClone.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicson.RedditClone.dto.SubredditDto;
import com.naicson.RedditClone.model.Subreddit;
import com.naicson.RedditClone.repository.SubredditRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class SubredditService {
	
	@Autowired
	private SubredditRepository subRepository;
	
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		
		Subreddit save = subRepository.save(mapSubredditDto(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}	
	
	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subRepository.findAll().stream().map(this::mapToDto)
		.collect(Collectors.toList());
	}
	
	private SubredditDto mapToDto(Subreddit subreddit) {
		return SubredditDto.builder().name(subreddit.getName())
				.id(subreddit.getId())
				.posts(subreddit.getPosts().size())
				.build();
	}
	
	private Subreddit mapSubredditDto(SubredditDto subredditDto) {
		
		return Subreddit.builder()
				.name(subredditDto.getSubredditName())
				.description(subredditDto.getDescription())
				.build();
			
	}
	

}
