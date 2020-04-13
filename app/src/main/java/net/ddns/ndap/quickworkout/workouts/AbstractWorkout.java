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
    private Context context;

    /**
     * Create a new AbstractWorkout instance.
     */
    public AbstractWorkout(Context context) {
        this.workoutList = new ArrayList<>();
        this.context = context;
        loadContents();
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

    /**
     * Create a file stream from the file within assets.
     *
     * @return InputStream opened from the corresponding file.
     * @throws IOException Thrown due to file issues.
     */
    private InputStream openFileStream() throws IOException {
        return context.getAssets().open("exercises/" + getFileName());
    }

    /**
     * Build file name from root - Mainly for testing purposes.
     *
     * @return String built filename to the exercise file.
     */
    private String buildFileName() {
        return "src/main/assets/exercises/" + getFileName();
    }

    /**
     * Load the corresponding exercise contents from the files. Location is based on whether
     * this has context or not.
     */
    private void loadContents() {
        try {
            if (this.context == null) {
                loadExerciseContents(new FileReader(new File(buildFileName())));
            } else {
                loadExerciseContents(new InputStreamReader(openFileStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the exercises contents from the corresponding file.
     */
    private void loadExerciseContents(Reader reader) {
        try (BufferedReader br = new BufferedReader(reader)) {
            br.lines().forEach(s -> {
                Scanner sc = new Scanner(s);
                sc.useDelimiter(",");

                // Assuming default structure "name,time,description,image_path"
                String name = sc.next();
                long time = sc.nextLong();
                String desc = sc.next();
                String imagePath = sc.next();
                addWorkout(new Workout(name, time, desc, imagePath));
                sc.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
