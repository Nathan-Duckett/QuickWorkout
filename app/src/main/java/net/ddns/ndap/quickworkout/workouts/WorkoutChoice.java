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
     * @return
     */
    Workout getRandom();
}
