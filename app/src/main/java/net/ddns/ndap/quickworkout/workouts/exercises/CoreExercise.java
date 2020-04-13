package net.ddns.ndap.quickworkout.workouts.exercises;

import android.content.Context;

import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;

/**
 * Representation of Core Exercises.
 *
 * @author Nathan Duckett
 */
public class CoreExercise extends AbstractWorkout {
    @Override
    public String getFileName() {
        return "core.csv";
    }
}
