package net.ddns.ndap.quickworkout.workouts;

import net.ddns.ndap.quickworkout.model.Workout;

public class Stretch extends AbstractWorkout {
    
    public Stretch() {
        super();
        addStretches();
    }

    private void addStretches() {
        addWorkout(new Workout("Leg Stretch", 30, "Stretch Legs somehow", "/src/images"));
    }

    @Override
    public Workout getRandom() {
        return null;
    }
}
