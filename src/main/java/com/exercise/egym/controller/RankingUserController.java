package com.exercise.egym.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.egym.domain.RankingUser;
import com.exercise.egym.service.ExerciseService;
import com.exercise.egym.service.RankingUserService;

@RestController
@RequestMapping(path = "/ranking")
public class RankingUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(RankingUserController.class);
	
	@Autowired
	private RankingUserService rankingUserService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RankingUser>> getRanking(@RequestParam(name = "usersList", required = true) String[] usersList){
		logger.info("Ranking user service");
		List<RankingUser>  rankingUsers = rankingUserService.getUserRankings(usersList);
		Collections.sort(rankingUsers, new Comparator<RankingUser>() {  
		    @Override  
		    public int compare(RankingUser secondUser, RankingUser firstUser) {  
		        int pointComparison = Float.compare(firstUser.getPoints(),secondUser.getPoints());  
		        if (pointComparison != 0) {  
		            return pointComparison;  
		        }  
		        rankingUserService.sortUserOnStartTime(firstUser, secondUser);
		        return firstUser.getStartTime().compareTo(secondUser.getStartTime());   
		    }  
		});  
		return new ResponseEntity<List<RankingUser>>(rankingUsers,HttpStatus.OK);
	}
}