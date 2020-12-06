package com.naicson.RedditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubredditDto {
	
	private Long id;
	private String name;
	private String description;
	private Integer posts;
	
	public SubredditDto() {
		
	}

	public SubredditDto(Long id, String subredditName, String description, Integer numberOfPosts) {
		this.id = id;
		this.name = subredditName;
		this.description = description;
		this.posts = numberOfPosts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubredditName() {
		return name;
	}

	public void setSubredditName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfPosts() {
		return posts;
	}

	public void setNumberOfPosts(Integer posts) {
		this.posts = posts;
	}
	
	
}
