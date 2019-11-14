package com.exercise.egym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exercise.egym.model.Exercise;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long>{

	@Query(value = "select * from exercise e where e.user_Id = ?1 and e.start_time < CURRENT_TIMESTAMP()  - 28", nativeQuery = true)
	List<Exercise> findExerciseByDateAndTime(long userId, int dateAndTimeRange);

	@Query(value = "select  DATEDIFF(day, ?1, ?2) from Exercise  where id = ?3")
	int findByTimeDifference(String dateAndTime, String fromDate, Long exerciseId);

	@Query(value = "select * from exercise e where e.user_Id = ?1", nativeQuery = true)
	List<Exercise> findByUserId(Long userId);

	@Query(value = "select * from Exercise where user_Id = ?1 order by start_time desc", nativeQuery = true)
	List<Exercise> findByStartTime(Long userId);
	
	@Query(value = "select * from Exercise where user_Id = ?1 and start_time >= ?2 and start_time <= ?3", nativeQuery = true)
	Exercise findBetweenStartAndEndTime(Long userId, String startTime, String endTime);
}
