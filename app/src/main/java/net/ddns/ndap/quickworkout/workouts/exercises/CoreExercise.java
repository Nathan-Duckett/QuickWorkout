package net.ddns.ndap.quickworkout.workouts.exercises;

import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;

/**
 * Representation of Core Exercises.
 *
 * @author Nathan Duckett
 */
public class CoreExercise extends AbstractWorkout {
    @Override
    protected String getFileName() {
        return "exercises/core.csv";
    }
}
