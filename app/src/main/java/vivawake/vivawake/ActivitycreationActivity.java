package vivawake.vivawake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class ActivitycreationActivity extends AppCompatActivity {
    Context context;


    public static ArrayList<String> alarmNames = new ArrayList<>();
    EditText hours;
    EditText minutes;
    EditText name;
    //LinearLayout mLayout;
    String ActivityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycreation);
        hours = (EditText) findViewById(R.id.HoursTxt);
        minutes = (EditText) findViewById(R.id.MinutesTxt);
        name = (EditText) findViewById(R.id.ActivityNameTxt);
        Button save = (Button) findViewById(R.id.saveButton);
        //mLayout = (LinearLayout) findViewById(R.id.linearL);
        alarmNames.add(name.getText().toString());
        //Button test = (Button) findViewById(R.id.button3);


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int hour = Integer.parseInt(hours.getText().toString());
                if(hour > 23){
                    hour = 23;
                }

                int minute = Integer.parseInt(minutes.getText().toString());
                if(minute > 59){
                    minute = 59;
                }
                String activityName = name.getText().toString();
                if(activityName.length()> 15){
                    activityName = activityName.substring(0, 15);
                }
                activity activity = new activity(activityName, hour, minute);
                saveActivity(view, activity);


                Intent backIntent = new Intent(ActivitycreationActivity.this, ActivityActivity.class);
                startActivity(backIntent);
            }
        });

    }


    public String saveActivity(View view, activity act){
        SharedPreferences sharedCounter = getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        int activityCounter = sharedCounter.getInt("counter", 0);
        activityCounter++;
        SharedPreferences.Editor counterEditor = sharedCounter.edit();
        counterEditor.putInt("counter", activityCounter);

        setActivityCounter(view, activityCounter);
        ActivityID = "Activity" + activityCounter;
        SharedPreferences sharedAct = getSharedPreferences(ActivityID, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedAct.edit();
        editor.putInt("Hour", act.getHour());
        editor.putInt("Minute", act.getMinutes());
        editor.putString("Name", act.getName());
        editor.apply();

        return ActivityID;
    }

    public void displayActivity(View view){
        SharedPreferences sharedAct = getSharedPreferences("tester", Context.MODE_PRIVATE);
    }

    public void setActivityCounter(View view, int numOfActivities){
        SharedPreferences sharedCounter = getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedCounter.edit();
        editor.putInt("counter", numOfActivities);
        editor.apply();
    }


}
