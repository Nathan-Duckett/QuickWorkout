package net.ddns.ndap.quickworkout.workouts;

import android.content.Context;

import net.ddns.ndap.quickworkout.model.Workout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @Override
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
}
