package net.ddns.ndap.quickworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ddns.ndap.quickworkout.model.Workout;
import net.ddns.ndap.quickworkout.util.ExerciseLoader;
import net.ddns.ndap.quickworkout.workouts.AbstractWorkout;
import net.ddns.ndap.quickworkout.workouts.WorkoutChoice;
import net.ddns.ndap.quickworkout.workouts.exercises.CoreExercise;
import net.ddns.ndap.quickworkout.workouts.exercises.StretchExercise;

public class WorkoutActivity extends AppCompatActivity {
    private WorkoutChoice exercises;
    private Workout choice;
    private TextView countdownText;
    private TextView exerciseNameField;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create view in android mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_progress);

        // Dynamically generate which exercises should be displayed
        Intent intent = getIntent();
        String intentExtra = "WORKOUT_TYPE";
        if (intent.hasExtra(intentExtra)) {
            initExercises(intent.getStringExtra(intentExtra));
        }

        exerciseNameField = findViewById(R.id.ExerciseName);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);

        countdownButton.setOnClickListener(view1 -> {
            startStop();
        });

        findViewById(R.id.workout_next_button).setOnClickListener(view1 -> {
            setExercise();
        });

        setExercise();
    }

    /**
     * Initialize the exercises for this activity.
     *
     * @param workoutType Passed intent workout_type when creating this activity.
     */
    private void initExercises(String workoutType) {
        if (workoutType == null) {
            throw new NullPointerException("Intent workout type was null");
        }

        switch (workoutType) {
            case "STRETCH":
                this.exercises = new StretchExercise();
                break;
            case "CORE":
                this.exercises = new CoreExercise();
        }

        // Populate the class with content.
        ExerciseLoader.load(getApplicationContext(), this.exercises);
    }

    /**
     * Set the exercise which is supposed to be performed next.
     */
    private void setExercise() {
        Workout lastChoice = this.choice;
        if (this.choice != null) {
            // Keep changing as long as exercise isn't the same.
            while (lastChoice.equals(this.choice)) {
                this.choice = exercises.getRandom();
            }
        } else {
            this.choice = exercises.getRandom();
        }
        timeLeftInMilliseconds = choice.getTime() * 1000;

        exerciseNameField.setText(choice.getName());
        updateTimer();

        countdownButton.setVisibility(View.VISIBLE);
        countdownButton.setText(R.string.timer_button);
    }

    /**
     * StartStop is set for controlling the timer to enable/disable it.
     */
    private void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    /**
     * Handle stopping the timer after a button press.
     */
    private void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText(R.string.timer_button);
        timerRunning = false;
    }

    /**
     * Handle creating a timer/starting a timer.
     */
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                    timerRunning = false;
                    countdownButton.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        countdownButton.setText("PAUSE");
        timerRunning = true;
    }

    /**
     * Handle updating the timer with the new value of remaining time.
     */
    private void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000; // Convert millis to minutes
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000; // Remainder above - Times 1000x from milli to seconds.

        String timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }
}
