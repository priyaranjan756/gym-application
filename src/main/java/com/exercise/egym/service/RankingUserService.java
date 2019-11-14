package com.exercise.egym.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exercise.egym.dao.ExerciseDao;
import com.exercise.egym.domain.RankingUser;
import com.exercise.egym.model.Exercise;
import com.exercise.egym.util.ExerciseType;

@Component
public class RankingUserService {
	@Autowired
	private ExerciseDao exerciseDao;

	public List<RankingUser>  getUserRankings(String[] usersList) {
		List<RankingUser>  rankingUsers = new ArrayList<>();
		List<List<Exercise>> listOfExerciseOfAllUser = new ArrayList<>();

		for(int i = 0;i<usersList.length;i++) {
			long userId = Long.parseLong(usersList[i]);
			List<Exercise>  exercises =  getAllExerciseOfUser(userId);
			List<Exercise>  filteredListOfExercise = null;
			if(exercises.size()>0) {
				filteredListOfExercise =  filteredListOfUser(exercises);					
				listOfExerciseOfAllUser.add(filteredListOfExercise);
			}
		}
		for(int i = 0;i<listOfExerciseOfAllUser.size();i++) {
			RankingUser rankingUser = calculatePoint(listOfExerciseOfAllUser.get(i));
			rankingUsers.add(rankingUser);
		}
		return rankingUsers;
	}

	public List<Exercise> getAllExerciseOfUser(long userId){
		return exerciseDao.findByUserId(userId);
	}
	public List<Exercise> filteredListOfUser(List<Exercise> exercises){
		List<Exercise> filteredList  = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String fromDate = simpleDateFormat.format(new Date());
		for(int i = 0;i<exercises.size();i++) {
			Long exerciseId= exercises.get(i).getId();
			int dayDiffValue = exerciseDao.findByDateAndTime(exercises.get(i).getStartTime(), fromDate, exerciseId);
			if(dayDiffValue<=28) {
				filteredList.add(exercises.get(i));
			}
		}
		return filteredList;
	}
	public RankingUser calculatePoint(List<Exercise> exercises) {
		long totalPoints = 0;
		RankingUser rankingUser = new RankingUser();
		rankingUser.setUserId(exercises.get(0).getUserId());
		for(int i = 0;i<exercises.size();i++) {

			String exerciseType = exercises.get(i).getType();
			Long calories = exercises.get(i).getCalories();
			Long duration = exercises.get(i).getDuration();
			long durationPoints = Math.floorDiv(duration, 60) + 1;
			long multiplicationFactor = ExerciseType.valueOf(exerciseType).getMultiplciationFactor();
			totalPoints += (calories + durationPoints)* multiplicationFactor;
			rankingUser.setPoints(totalPoints);

		}
		return rankingUser;
	}

	public void sortUserOnStartTime(RankingUser firstUser, RankingUser secondUser) {
		Exercise startTimeForUserOne = exerciseDao.findByStartTime(firstUser.getUserId());
		firstUser.setStartTime(startTimeForUserOne.getStartTime());

		Exercise startTimeForUserTwo = exerciseDao.findByStartTime(secondUser.getUserId());
		secondUser.setStartTime(startTimeForUserTwo.getStartTime());
	}
}
