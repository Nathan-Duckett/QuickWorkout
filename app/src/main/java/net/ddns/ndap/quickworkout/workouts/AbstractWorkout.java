package net.ddns.ndap.quickworkout.workouts;

import net.ddns.ndap.quickworkout.model.Workout;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract representation of a Workout. Basic implementation of required methods which are
 * generic between different exercise choices. This is the superclass of any Exercise classes
 * which implement the same functionality between versions.
 *
 * @author Nathan Duckett
 */
public abstract class AbstractWorkout implements WorkoutChoice {
    private List<Workout> workoutList;

    /**
     * Create a new AbstractWorkout instance.
     */
    public AbstractWorkout() {
        this.workoutList = new ArrayList<>();
    }

    /**
     * Add a workout into the workout list.
     *
     * @param workout Workout to be added to the list.
     */
    public void addWorkout(Workout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout provided was null");
        }
        this.workoutList.add(workout);
    }

    /**
     * Get the workout from the specified position in the list.
     *
     * @param position integer position to retrieve the Workout from the list.
     */
    public Workout getWorkout(int position) {
        if (position < 0 || position >= getNumberOfWorkouts()) {
            return null;
        }

        return this.workoutList.get(position);
    }

    @Override
    public Workout getRandom() {
        int random = (int)(Math.random() * getNumberOfWorkouts());

        return getWorkout(random);
    }

    /**
     * Get the number of workouts contained in the list of workouts.
     *
     * @return integer count of how many Workouts are in the list.
     */
    public int getNumberOfWorkouts() {
        return this.workoutList.size();
    }

    /**
     * Get the filename of contents to load for this Exercise.
     *
     * @return String filename for the contents of the Exercise.
     */
    protected abstract String getFileName();
}
