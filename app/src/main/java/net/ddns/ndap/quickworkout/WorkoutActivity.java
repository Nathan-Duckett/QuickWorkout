package net.ddns.ndap.quickworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ddns.ndap.quickworkout.model.Workout;
import net.ddns.ndap.quickworkout.workouts.WorkoutChoice;
import net.ddns.ndap.quickworkout.workouts.exercises.CoreExercise;

public class WorkoutActivity extends AppCompatActivity {
    private WorkoutChoice coreExercises;
    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_progress);
        this.coreExercises = new CoreExercise(getApplication().getApplicationContext());

        Workout choice = coreExercises.getRandom();
        System.out.println(choice);
        timeLeftInMilliseconds = choice.getTime() * 1000;
        TextView exerciseNameField = findViewById(R.id.ExerciseName);
        exerciseNameField.setText(choice.getName());
        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);

        countdownButton.setOnClickListener(view1 -> {
            startStop();
        });

        countdownButton.setText("START");

        updateTimer();
    }

    public void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText("START");
        timerRunning = false;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        countdownButton.setText("PAUSE");
        timerRunning = true;
    }

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
