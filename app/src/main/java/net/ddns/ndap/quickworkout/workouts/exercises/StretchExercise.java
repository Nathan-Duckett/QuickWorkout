package net.ddns.ndap.quickworkout.workouts.exercises;

import android.content.Context;

import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;

/**
 * Representation of a Stretch Exercise.
 *
 * @author Nathan Duckett
 */
public class StretchExercise extends AbstractWorkout {
    @Override
    public String getFileName() {
        return "exercises/stretch.csv";
    }
}
