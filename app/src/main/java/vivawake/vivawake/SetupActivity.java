package vivawake.vivawake;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setupActionBar();
        TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setTitle("Setup");
    }
}
