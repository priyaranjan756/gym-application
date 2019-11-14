package com.exercise.egym.domain;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Exercise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6213068883970713702L;

	@Null(groups = {PostValidator.class,PatchValidator.class},message = "Exercise Id should not allowed to create/update manually")
	private Long id;

	@NotNull(groups = PostValidator.class,message = "User Id can't be Null")
	@Null(groups = {PatchValidator.class}, message = "User Id not allowed to update")
	private Long userId;

	@NotNull(groups = PostValidator.class, message = "Description can't be Null")
	@NotEmpty(groups = PostValidator.class, message = "Description can't be empty")
	@Pattern(groups = {PostValidator.class,PatchValidator.class},message = "A valid description is a non empty string containing "
			+ "only alphanumeric characters and spaces in between",regexp = "^[a-zA-Z0-9 ]*$" )
	private String description;

	@NotNull(groups = PostValidator.class, message = "Exercise Type can't be Null")
	@Null(groups = PatchValidator.class, message = "Exercise Type not allowed to update")
	private String type;

	@NotNull(groups = PostValidator.class, message = "Start time can't be Null")
	@NotEmpty(groups = PostValidator.class, message = "Start time can't be empty")
	private String startTime;

	@NotNull(groups = PostValidator.class, message = "duration can't be Null")
	private long duration;

	@NotNull(groups = PostValidator.class, message = "calories can't be Null")
	private long calories;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Long getCalories() {
		return calories;
	}
	public void setCalories(Long calories) {
		this.calories = calories;
	}

	public interface PatchValidator{

	}

	public interface PostValidator{

	}
}