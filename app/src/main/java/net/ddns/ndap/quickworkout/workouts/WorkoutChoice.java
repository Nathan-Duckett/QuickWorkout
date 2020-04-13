package net.ddns.ndap.quickworkout.workouts;

import net.ddns.ndap.quickworkout.model.Workout;

/**
 * Interface to represent the different WorkoutChoice categories.
 *
 * @author Nathan Duckett
 */
public interface WorkoutChoice {
    /**
     * Get a random workout from the possible choices of this type.
     *
     * @return Workout from the list of workouts.
     */
    Workout getRandom();

    /**
     * Get the filename of contents to load for this Exercise.
     *
     * @return String filename for the contents of the Exercise.
     */
    String getFileName();

    /**
     * Add a workout into the workout list.
     *
     * @param workout Workout to be added to the list.
     */
    void addWorkout(Workout workout);
}
