package net.ddns.ndap.quickworkout;

import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;
import net.ddns.ndap.quickworkout.workouts.exercises.CoreExercise;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the functionality of Workouts. Loading in the data and verifying contents.
 *
 * @author Nathan Duckett
 */
public class WorkoutTest {
    @Test
    public void test_import() {
        AbstractWorkout workout = new CoreExercise();

        assertEquals(1, workout.getNumberOfWorkouts());
    }
}