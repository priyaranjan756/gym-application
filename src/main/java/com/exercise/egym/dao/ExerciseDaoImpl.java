package com.exercise.egym.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.egym.exception.DataNotFoundException;
import com.exercise.egym.model.Exercise;
import com.exercise.egym.repository.ExerciseRepository;

@Component
public class ExerciseDaoImpl implements ExerciseDao{

	@Autowired
	private ExerciseRepository employeeRepository;

	@Transactional
	public void insertExercise(com.exercise.egym.model.Exercise exercise) {
		if(null!=exercise) {
			employeeRepository.save(exercise);
		}
	}

	@Transactional
	public void updateExercise(Long exerciseId , com.exercise.egym.model.Exercise exercise) {

		Optional<Exercise> savedExercise = employeeRepository.findById(exerciseId);
		if(savedExercise.isPresent()) {
			Exercise savedData = savedExercise.get();

			if(exercise.getCalories()!=null) 	
				savedData.setCalories(exercise.getCalories());

			if(exercise.getDescription()!=null)
				savedData.setDescription(exercise.getDescription());

			if(exercise.getDuration()!=null)
				savedData.setDuration(exercise.getDuration());

			if(exercise.getStartTime()!=null)
				savedData.setStartTime(exercise.getStartTime());

			employeeRepository.saveAndFlush(savedData);

		}
		else {
			throw new DataNotFoundException("Exercise Id not found");
		}

	}

	@Override
	public List<Exercise> findAllExercise() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Exercise> findByDateAndTime(long userId, int dateAndTimeRange) {
		return employeeRepository.findExerciseByDateAndTime(userId, dateAndTimeRange);

	}

	@Override
	public int findByDateAndTime(String dateAndTime, String fromDate, Long exerciseId) {
		return employeeRepository.findByTimeDifference(dateAndTime, fromDate, exerciseId);
	}

	@Override
	public List<Exercise> findByUserId(long userId) {
		return employeeRepository.findByUserId(userId);
	}

	@Override
	public Exercise findByStartTime(Long userId) {
		return employeeRepository.findByStartTime(userId).get(0);
	}

	@Override
	public Exercise findBetweenStartAndEndTime(Long userId, String startTime, String endTime) {
		return employeeRepository.findBetweenStartAndEndTime(userId,startTime, endTime);
	}

}
