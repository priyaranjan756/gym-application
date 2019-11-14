package com.exercise.egym.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.exercise.egym.exception.InvalidDataException;
import com.exercise.egym.model.Exercise;
import com.exercise.egym.util.ExerciseType;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Component
public class ExerciseDtoToModelMapper {

	public Exercise dtoToModelMapper(com.exercise.egym.domain.Exercise exerciseDto) {
		Exercise exerciseModel = new Exercise();
		try {
			if(exerciseDto.getCalories()!=null)
				exerciseModel.setCalories(exerciseDto.getCalories());

			if(exerciseDto.getDescription()!=null)
				exerciseModel.setDescription(exerciseDto.getDescription());

			if(exerciseDto.getDuration()!=null)
				exerciseModel.setDuration(exerciseDto.getDuration());

			if(exerciseDto.getStartTime()!=null)
				isCorrectDateFormat(exerciseDto.getStartTime());
			exerciseModel.setStartTime(exerciseDto.getStartTime());

			if(exerciseDto.getType()!=null) {
				String eType = ExerciseType.getExerciseType(exerciseDto.getType());
				if(eType==null) {
					throw new InvalidDataException("Exercise type is not a valid type");
				}else {
					exerciseModel.setType(exerciseDto.getType());
				}
			}
			if(exerciseDto.getUserId()!=null)
				exerciseModel.setUserId(exerciseDto.getUserId());

		}catch(ParseException exception) {
			throw new InvalidDataException("Invalid Date-time format exception",exception);
		}

		return exerciseModel;
	}

	public void isCorrectDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.parse(date);  
	}
}
