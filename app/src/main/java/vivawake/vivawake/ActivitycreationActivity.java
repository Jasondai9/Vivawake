package vivawake.vivawake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitycreationActivity extends AppCompatActivity {
    EditText hours;
    EditText minutes;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycreation);
        hours = (EditText) findViewById(R.id.HoursTxt);
        minutes = (EditText) findViewById(R.id.MinutesTxt);
        name = (EditText) findViewById(R.id.ActivityNameTxt);
        Button save = (Button) findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int hour = Integer.parseInt(hours.getText().toString());
                int minute = Integer.parseInt(minutes.getText().toString());
                String activityName = name.getText().toString();
                activity activity = new activity(activityName, hour, minute);

            }
        });
    }
}
