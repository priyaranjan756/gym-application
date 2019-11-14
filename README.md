## Tasks

- Insert new exercises.
    - All exercise fields except the id should be mandatory for inserting.
  
- Update exercises.
    - While updating an exercise, the user id and type can't change, please return an appropriate error in this case.
    - All other fields are mandatory.
  
- Define a way to rank a list of user ids by the user's points:
    - A user gets points for each exercise he has started in the past 28 days.
        - Example: Let's assume that today is 24 June 2019 at 2PM, then the range will be from the beginning of 27 May 2019 till the end of 23 June 2019. 
    - The points are calculated as follows:
        - A user gets one point per started minute of the duration of the exercise plus the burnt calories.
            - Example: One exercise lasting 80 seconds plus 80 calories burnt will result in 82 points. 
        - Looking from the newest to the oldest, each time a user has performed the same type of exercise, the exercise is worth 10% less.
            - Example: A user ran 4 times in the past 28 days.
              Today's run is worth 100% of the points. The oldest run is worth only 70% of the calculated points for the exercise.
        - An exercise can't count for less than 0 points.
        - Each exercise type has a multiplication factor for the point calculation:
            - `RUNNING` - 2
            - `SWIMMING` - 3
            - `STRENGTH_TRAINING` - 3
            - `CIRCUIT_TRAINING` - 4
    - Return a JSON containing a list of user id and point combinations. Order them according to the user's points in descending order.
    - When two users have the same amount of points, the user with the latest done exercise is ranked first in the list.

## Exercise Validation
 
  - Use an appropriate HTTP status code if the input is invalid. Document your decisions in the Open API specification.
  - Check if the provided description is valid. A valid description is a non empty string containing only alphanumeric 
    characters and spaces in between.
  - Only UTC date-times are allowed in input.
  - During exercise persistence it should be checked that there
    is no other exercise already present for the user id, in the
    period (start + duration) where the new exercise will take place.
