package com.exercise.egym.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
