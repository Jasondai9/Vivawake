package vivawake.vivawake;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetupActivity extends AppCompatActivity {

    TimePicker timePicker1;
    AlarmManager alarmManager1;
    Calendar calendar;
    Context context;
    Spinner ringtoneSpinner;
    ArrayAdapter<String> ringtoneAdapter;
    String alarmName, ringtone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setupActionBar();

        alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        ringtoneSpinner = findViewById(R.id.ringtoneSpinner);
        calendar = Calendar.getInstance();

        //temp alarm
        Button tempAlarmButton = (Button) findViewById(R.id.tempAlarmButton);
        tempAlarmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Playing alarm...", Toast.LENGTH_SHORT).show();
            }
        });

        //button that saves the alarm
        Button saveAlarmButton = (Button) findViewById(R.id.saveAlarmButton);
        saveAlarmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int hour = timePicker1.getHour();
                int minute = timePicker1.getMinute();
                //converts edit text to string
                EditText alarmNameEditText = (EditText) findViewById(R.id.alarmNameEditText);
                alarmName = alarmNameEditText.getText().toString();
     //           ringtone = ringtoneSpinner.getSelectedItem().toString();

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

   //             saveAlarm(v, hour, minute, alarmName, ringtone);

            }
        });
        this.context = this;
    }

    public void saveAlarm(View view, int hour, int minute, String alarmName, String ringtoneFileName){
        SharedPreferences sharedPref = getSharedPreferences(alarmName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
/*
        editor.putString("alarmName", alarmName);
        editor.putInt("hour", hour);
        editor.putInt("minute", minute);
        editor.putString("ringtone", ringtoneFileName);
        editor.apply();
*/
        Toast.makeText(getApplicationContext(),"Alarm saved", Toast.LENGTH_SHORT).show();
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
