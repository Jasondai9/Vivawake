package vivawake.vivawake;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetupActivity extends AppCompatActivity {

    TimePicker timePicker1;
    Context context;
    Spinner ringtoneSpinner;
    ArrayAdapter<CharSequence> ringtoneAdapter;
    String alarmName, ringtone, alarmID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);


        final Calendar calendar = Calendar.getInstance();

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        ringtoneSpinner = (Spinner) findViewById(R.id.ringtoneSpinner);
        ringtoneAdapter = ArrayAdapter.createFromResource(this, R.array.ringtoneArray,  android.R.layout.simple_spinner_item);
        ringtoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ringtoneSpinner.setAdapter(ringtoneAdapter);


        //button that saves the alarm
        Button saveAlarmButton = (Button) findViewById(R.id.saveAlarmButton);
        saveAlarmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent alarmReceiverIntent = new Intent(SetupActivity.this, AlarmReceiver.class);

                AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);

                int hour = timePicker1.getHour();
                int minute = timePicker1.getMinute();
                EditText alarmNameEditText = (EditText) findViewById(R.id.alarmNameEditText);
                alarmName = alarmNameEditText.getText().toString();
                ringtone = ringtoneSpinner.getSelectedItem().toString();

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);


                /*
                subtract the activity time from this
                 */
                long alarmTime = calendar.getTimeInMillis();
                alarmID = saveAlarm(v, hour, minute, alarmName, ringtone);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);


                finish();
            }
        });
        this.context = this;
    }


    //returns alarmID
    public String saveAlarm(View view, int hour, int minute, String alarmName, String ringtoneFileName){
        int alarmCounter = getAlarmCounter(view);
        if(alarmCounter == -1) {    //then its the first alarm
            alarmCounter = 0;
        }

        alarmCounter++;
        setAlarmCounter(view, alarmCounter);

        alarmID = "alarm" + alarmCounter;

        SharedPreferences sharedAlarm = getSharedPreferences(alarmID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedAlarm.edit();

        editor.putString("alarmID", alarmID);
        editor.putString("alarmName", alarmName);
        editor.putInt("hour", hour);
        editor.putInt("minute", minute);
        editor.putString("ringtone", ringtoneFileName);
        editor.apply();

        String ampm;
        if(hour/12 == 1)
            ampm = "PM";
        else
            ampm = "AM";

        Toast.makeText(getApplicationContext(),"Alarm will sound at " + hour%12 + ":" + minute + ampm, Toast.LENGTH_SHORT).show();
        return alarmID;
    }


    public int getAlarmCounter(View view){
        SharedPreferences sharedCounter = getSharedPreferences("alarmCounter", Context.MODE_PRIVATE);
        return sharedCounter.getInt("counter", -1);
    }

    public void setAlarmCounter(View view, int numOfAlarms){
        SharedPreferences sharedCounter = getSharedPreferences("alarmCounter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedCounter.edit();
        editor.putInt("counter", numOfAlarms);
        editor.apply();
    }
/*
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setTitle("Setup");
    }
*/

}
