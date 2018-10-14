package vivawake.vivawake;

import android.content.Context;
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

    public static ArrayList<String> alarmNames = new ArrayList<>();
    EditText hours;
    EditText minutes;
    EditText name;
    LinearLayout mLayout;
    String ActivityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycreation);
        hours = (EditText) findViewById(R.id.HoursTxt);
        minutes = (EditText) findViewById(R.id.MinutesTxt);
        name = (EditText) findViewById(R.id.ActivityNameTxt);
        Button save = (Button) findViewById(R.id.saveButton);
        mLayout = (LinearLayout) findViewById(R.id.linearL);
        alarmNames.add(name.getText().toString());
        //Button test = (Button) findViewById(R.id.button3);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int hour = Integer.parseInt(hours.getText().toString());
                //Toast.makeText(ActivitycreationActivity.this, Integer.toString(hour), Toast.LENGTH_LONG).show();
                int minute = Integer.parseInt(minutes.getText().toString());
                String activityName = name.getText().toString();
                activity activity = new activity(activityName, hour, minute);
                saveActivity(view, activity);

                finish();
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
        SharedPreferences sharedAct = getSharedPreferences("ActivityID", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedAct.edit();
        editor.putInt("Hour", act.getHour());
        editor.putInt("Minute", act.getMinutes());
        editor.putString("Name", act.getName());
        editor.apply();

        Toast.makeText(this, "Saved!" + sharedCounter.getInt("counter", 0), Toast.LENGTH_LONG).show();
        return ActivityID;
    }

    public void displayActivity(View view){
        SharedPreferences sharedAct = getSharedPreferences("tester", Context.MODE_PRIVATE);
        Toast.makeText(this, sharedAct.getString("Name", ""), Toast.LENGTH_LONG).show();
    }

    public void setActivityCounter(View view, int numOfActivities){
        SharedPreferences sharedCounter = getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedCounter.edit();
        editor.putInt("counter", numOfActivities);
        editor.apply();
    }


}
