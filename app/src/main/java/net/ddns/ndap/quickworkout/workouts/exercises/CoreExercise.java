package net.ddns.ndap.quickworkout.workouts.exercises;

import android.content.Context;

import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;

/**
 * Representation of Core Exercises.
 *
 * @author Nathan Duckett
 */
public class CoreExercise extends AbstractWorkout {
    /**
     * Create a new CoreExercise instance.
     *
     * @param context Context from Android Application.
     */
    public CoreExercise(Context context) {
        super(context);
    }

    @Override
    protected String getFileName() {
        return "core.csv";
    }
}
