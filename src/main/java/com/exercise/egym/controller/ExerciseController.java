package com.exercise.egym.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.egym.domain.Exercise;
import com.exercise.egym.service.ExerciseService;

@RequestMapping(path = "/exercise")
@RestController
public class ExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);

	@Autowired
	private ExerciseService exerciseService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Exercise> insertExercise(@Validated(Exercise.PostValidator.class) @RequestBody Exercise exercise){
		logger.info("Inserting exercise for user id "+exercise.getUserId());
		return exerciseService.insertExercise(exercise);
	}

	@RequestMapping(value = "/{exerciseId}",method = RequestMethod.PATCH)
	public ResponseEntity<Exercise> updateExercise(@PathVariable(value = "exerciseId", required = true) Long  exerciseId,
			@Validated(Exercise.PatchValidator.class) @RequestBody Exercise exercise){
		logger.info("Updating exercise for user id "+exercise.getUserId()+ " and exercise Id "+exerciseId);
		return exerciseService.updateExercise(exerciseId, exercise);
	}

}
