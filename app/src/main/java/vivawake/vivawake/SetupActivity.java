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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ScrollView;

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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final Calendar calendar = Calendar.getInstance();

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        ringtoneSpinner = (Spinner) findViewById(R.id.ringtoneSpinner);
        ringtoneAdapter = ArrayAdapter.createFromResource(this, R.array.ringtoneArray,  android.R.layout.simple_spinner_item);
        ringtoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ringtoneSpinner.setAdapter(ringtoneAdapter);

        Button activitiesButton = (Button) findViewById(R.id.ActivitesButton);
        activitiesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupActivity.this, ActivityActivity.class));
            }
         });

        //button that saves the alarm
        Button saveAlarmButton = (Button) findViewById(R.id.saveAlarmButton);
        saveAlarmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent alarmReceiverIntent = new Intent(SetupActivity.this, AlarmReceiver.class);

                //AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);

                //Gets the input time
                int hour = timePicker1.getHour();
                int minute = timePicker1.getMinute();
                //Stores input text
                EditText alarmNameEditText = (EditText) findViewById(R.id.alarmNameEditText);
                alarmName = alarmNameEditText.getText().toString();
                //Selects ringtone
                ringtone = ringtoneSpinner.getSelectedItem().toString();
                alarmID = saveAlarm(v, hour, minute, alarmName, ringtone);

                //calendar.set(Calendar.HOUR_OF_DAY, hour);
               // calendar.set(Calendar.MINUTE, minute);

                Alarm alarm = new Alarm(alarmName, ringtone, hour, minute, alarmID);
                MainActivity.alarmArrayList.add(alarm);

                alarm.turnMeOn(context);

                Intent backIntent = new Intent(SetupActivity.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
        this.context = this;
    }


    //returns alarmID
    public String saveAlarm(View view, int hour, int minute, String alarmName, String ringtoneFileName){
        int alarmCounter = getAlarmCounter(view);


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

        String min;
        if(minute/10 == 0)
            min = "0"+minute;
        else
            min = ""+minute;

        Toast.makeText(getApplicationContext(),"Alarm will sound at " + hour%12 + ":" + min + ampm, Toast.LENGTH_SHORT).show();
        return alarmID;
    }


    public int getAlarmCounter(View view){
        SharedPreferences sharedCounter = getSharedPreferences("alarmCounter", Context.MODE_PRIVATE);
        return sharedCounter.getInt("counter", 0);
    }

    public void setAlarmCounter(View view, int numOfAlarms){
        SharedPreferences sharedCounter = getSharedPreferences("alarmCounter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedCounter.edit();
        editor.putInt("counter", numOfAlarms);
        editor.apply();
    }


}
