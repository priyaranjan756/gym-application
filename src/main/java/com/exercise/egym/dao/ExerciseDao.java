package com.exercise.egym.dao;

import java.util.List;

import com.exercise.egym.model.Exercise;

public interface ExerciseDao {
	void insertExercise(Exercise exercise);

	void updateExercise(Long exerciseId, Exercise exercise);

	List<Exercise> findAllExercise();

	List<Exercise> findByDateAndTime(long userId, int dateAndTimeRange);

	int findByDateAndTime(String dateAndTime,String fromDate, Long exerciseId );

	List<Exercise> findByUserId(long userId);

	Exercise findByStartTime(Long userId);

	Exercise findBetweenStartAndEndTime(Long userId, String startTime, String endTime);

}
