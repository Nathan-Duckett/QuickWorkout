package net.ddns.ndap.quickworkout.util;

import android.content.Context;

import net.ddns.ndap.quickworkout.model.Workout;
import net.ddns.ndap.quickworkout.workouts.WorkoutChoice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 * ExerciseLoader is designed to simplify the process of loading the contents of the exercise files
 * into a Workout instance. This will run off the app context to access the assets to read the
 * contents into the program. The context can be provided <code>null</code> to result in local loading
 * direct from the project root to the expected file.
 *
 * @author Nathan Duckett
 */
public class ExerciseLoader {
    private Context context;
    private WorkoutChoice workout;

    /**
     * Create a new ExerciseLoader. This will take the application context and load the corresponding
     * data into the provided workout instance.
     *
     * @param context Application context.
     * @param workout Workout to have its' data loaded.
     */
    public ExerciseLoader(Context context, WorkoutChoice workout) {
        this.context = context;
        this.workout = workout;
        loadContents();
    }

    /**
     * Create a file stream from the file within assets.
     *
     * @return InputStream opened from the corresponding file.
     * @throws IOException Thrown due to file issues.
     */
    private InputStream openFileStream() throws IOException {
        return context.getAssets().open("exercises/" + workout.getFileName());
    }

    /**
     * Build file name from root - Mainly for testing purposes.
     *
     * @return String built filename to the exercise file.
     */
    private String buildFileName() {
        return "src/main/assets/exercises/" + workout.getFileName();
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
                workout.addWorkout(new Workout(name, time, desc, imagePath));
                sc.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
