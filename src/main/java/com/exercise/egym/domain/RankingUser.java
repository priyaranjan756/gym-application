package com.exercise.egym.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class RankingUser  {

	public Long getUserId() {
		return userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	private Long userId;

	private float points;

	@JsonIgnore
	private String startTime;

	//	@Override
	//	public int compareTo(RankingUser usersRanking) {
	//		if (getPoints() == 0 || usersRanking.getPoints() == 0) {
	//		      return 0;
	//		    }
	//			if(Float.compare(usersRanking.getPoints(),getPoints())==0) {
	//				
	//			}
	//		    return Float.compare(usersRanking.getPoints(),getPoints());
	//	}
	//	


}
