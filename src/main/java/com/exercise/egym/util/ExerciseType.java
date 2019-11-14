package com.exercise.egym.util;

public enum ExerciseType {
	RUNNING(2), SWIMMING(3), STRENGTH_TRAINING(3), CIRCUIT_TRAINING(4);
	
	int multiplciationFactor;
	
	public int getMultiplciationFactor() {
		return multiplciationFactor;
	}

	public void setMultiplciationFactor(int multiplciationFactor) {
		this.multiplciationFactor = multiplciationFactor;
	}

	ExerciseType(int multiFactor) {
		this.multiplciationFactor = multiFactor;
	}
	
	public static String getExerciseType(String type) {
		for (ExerciseType exerciseType : ExerciseType.values()) {
			if(exerciseType.name().equals(type)) {
				return type;
			}
		}
		return null;
	}
}
