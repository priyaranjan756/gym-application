package com.exercise.egym.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class DateComparator {

	public boolean compareTwoDate(String startTimeOfNewExercise, String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			if(sdf.parse(startTimeOfNewExercise).after(sdf.parse(startTime)) && 
					sdf.parse(startTimeOfNewExercise).before(sdf.parse(endTime)))
				return true;

			if(sdf.parse(startTimeOfNewExercise).equals(sdf.parse(startTime)) || 
					sdf.parse(startTimeOfNewExercise).equals(sdf.parse(endTime)))
				return true;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}
}
