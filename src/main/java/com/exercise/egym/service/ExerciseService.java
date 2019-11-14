package com.exercise.egym.service;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.exercise.egym.dao.ExerciseDao;
import com.exercise.egym.domain.ErrorResponse;
import com.exercise.egym.domain.Exercise;
import com.exercise.egym.domain.HttpResponseObject;
import com.exercise.egym.exception.DataNotFoundException;
import com.exercise.egym.exception.InvalidDataException;
import com.exercise.egym.mapper.ExerciseDtoToModelMapper;
import com.exercise.egym.util.DateComparator;

@Component
public class ExerciseService {

	private static final Logger logger = LoggerFactory.getLogger(ExerciseService.class);

	@Autowired
	private ExerciseDao exerciseDao;

	@Autowired
	private ExerciseDtoToModelMapper dtoToModelMapper;

	@Autowired
	private DateComparator dateComparator;

	public <T> ResponseEntity insertExercise(Exercise exercise) {	
		try {
			com.exercise.egym.model.Exercise exerciseModel = dtoToModelMapper.dtoToModelMapper(exercise);
			List<com.exercise.egym.model.Exercise> allUsersExeriseList = getAllExerciseOfUser(exercise.getUserId());
			boolean isExerciseExist = isExerciseExistBetweenTimeRange(allUsersExeriseList, exerciseModel);
			if(isExerciseExist==false) {
				exerciseDao.insertExercise(exerciseModel);
				return new ResponseEntity<HttpResponseObject>(new  HttpResponseObject("Exercise created",HttpStatus.CREATED.value()),HttpStatus.CREATED);
			}
			else {
				logger.info("Exercise exist between the starttime and duration");
				return new ResponseEntity<HttpResponseObject>(new  HttpResponseObject("Exercise exist between the starttime and duration ",HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
			}	
		}
		catch(InvalidDataException e) {	
			logger.error("Bad request "+e.getMessage());
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			logger.error("Bad request "+e.getMessage());
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}

	public <T> ResponseEntity updateExercise(Long exerciseId,Exercise exercise) {
		try {
			com.exercise.egym.model.Exercise exerciseModel = dtoToModelMapper.dtoToModelMapper(exercise);
			exerciseDao.updateExercise(exerciseId, exerciseModel);
			return new ResponseEntity(HttpStatus.OK);
		}catch (DataNotFoundException e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
		} catch (InvalidDataException e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
		} 
	}
	public List<com.exercise.egym.model.Exercise> getAllExerciseOfUser(long userId){
		return exerciseDao.findByUserId(userId);
	}

	public boolean isExerciseExistBetweenTimeRange(List<com.exercise.egym.model.Exercise> exercises, com.exercise.egym.model.Exercise exercise) {
		String newExerciseStartTime = exercise.getStartTime();
		for(int i = 0;i<exercises.size();i++) {
			String startTime = exercises.get(i).getStartTime();
			OffsetDateTime odt = OffsetDateTime.parse(startTime);
			Duration duration = Duration.ofSeconds(exercises.get(i).getDuration());
			OffsetDateTime odtLater = odt.plus(duration);
			Instant instant = odtLater.toInstant();
			String endTime = instant.toString();
			boolean isExerciseExist = dateComparator.compareTwoDate(newExerciseStartTime, startTime, endTime);
			if(isExerciseExist)
				return true;
		}
		return false;
	}
}
