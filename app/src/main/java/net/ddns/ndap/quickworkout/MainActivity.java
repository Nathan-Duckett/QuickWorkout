package net.ddns.ndap.quickworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ddns.ndap.quickworkout.model.Workout;
import net.ddns.ndap.quickworkout.workouts.WorkoutChoice;
import net.ddns.ndap.quickworkout.workouts.exercises.CoreExercise;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runButton = findViewById(R.id.core_button);
        runButton.setOnClickListener(this::startWorkout);

        // Set dark mode by default
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void startWorkout(View view) {
        if (view.getId() == R.id.core_button) {
            Intent intent = new Intent(this, WorkoutActivity.class);

            this.startActivity(intent);
        }
    }
}
