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
    /**
     * Populate the workout with the required data from the assets folder. Automatically loads,
     * parses and sets the values within the Workout.
     *
     * @param appContext Android app context - access to assets folder.
     * @param workout Workout to populate the data with.
     */
    public static void load(Context appContext, WorkoutChoice workout) {
        try {
            if (appContext == null) {
                loadExerciseContents(workout, new FileReader(new File(buildFileName(workout))));
            } else {
                loadExerciseContents(workout, new InputStreamReader(openFileStream(appContext, workout)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a file stream from the file within assets.
     *
     * @param context Android app context.
     * @param workout Workout to retrieve the expected filename from.
     * @return InputStream opened from the corresponding file.
     * @throws IOException Thrown due to file issues.
     */
    private static InputStream openFileStream(Context context, WorkoutChoice workout) throws IOException {
        return context.getAssets().open("exercises/" + workout.getFileName());
    }

    /**
     * Build file name from root - Mainly for testing purposes.
     *
     * @param workout Workout to retrieve the expected filename from.
     * @return String built filename to the exercise file.
     */
    private static String buildFileName(WorkoutChoice workout) {
        return "src/main/assets/exercises/" + workout.getFileName();
    }

    /**
     * Load the exercises contents from the corresponding file.
     *
     * @param workout Workout to populate with the data from the reader.
     * @param reader Reader containing the data ready to parse from the file.
     */
    private static void loadExerciseContents(WorkoutChoice workout, Reader reader) {
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
