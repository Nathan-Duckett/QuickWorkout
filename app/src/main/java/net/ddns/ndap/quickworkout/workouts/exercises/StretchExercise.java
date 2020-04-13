package net.ddns.ndap.quickworkout.workouts.exercises;

import android.content.Context;

import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;

/**
 * Representation of a Stretch Exercise.
 *
 * @author Nathan Duckett
 */
public class StretchExercise extends AbstractWorkout {
    /**
     * Create a new StretchExercise instance.
     *
     * @param context Context for this Android App.
     */
    public StretchExercise(Context context) {
        super(context);
    }

    @Override
    protected String getFileName() {
        return "exercises/stretch.csv";
    }
}
