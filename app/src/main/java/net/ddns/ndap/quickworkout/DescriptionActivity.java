package net.ddns.ndap.quickworkout;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        // Get display metrics to determine size to set popup
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.4));

        setText();
    }

    /**
     * Set the text within the pop up text view.
     */
    private void setText() {
        String descExtra = "WORKOUT_DESC";
        if (getIntent().hasExtra(descExtra)) {
            TextView textView = findViewById(R.id.popup_description);
            textView.setText(getIntent().getStringExtra(descExtra));
        }
    }
}
