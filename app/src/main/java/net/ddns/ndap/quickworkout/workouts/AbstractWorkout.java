package net.ddns.ndap.quickworkout.workouts;

import net.ddns.ndap.quickworkout.model.Workout;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorkout implements WorkoutChoice {
    private List<Workout> workoutList;

    public AbstractWorkout() {
        this.workoutList = new ArrayList<>();
    }

    public void addWorkout(Workout workout) {
        this.workoutList.add(workout);
    }

    public void getWorkout(int position) {

    }

    public int getNumberOfWorkouts() {
        return this.workoutList.size();
    }
}
