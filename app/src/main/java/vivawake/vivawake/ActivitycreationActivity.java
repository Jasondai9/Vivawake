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

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int hour = Integer.parseInt(hours.getText().toString());
                int minute = Integer.parseInt(minutes.getText().toString());
                String activityName = name.getText().toString();
                activity activity = new activity(activityName, hour, minute);
                saveActivity(view, activity);
            }
        });
    }

    /*public void activityCounter(View view){
        SharedPreferences activityCount = getSharedPreferences("ActivityCounter", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = activityCount.edit();


    }*/

    public void saveActivity(View view, activity act){
        SharedPreferences sharedAct = getSharedPreferences(act.getName(), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedAct.edit();
        editor.putInt("Hour", act.getHour());
        editor.putInt("Minute", act.getMinutes());
        editor.putString("Name", act.getName());


        Toast.makeText(this, "Saved!" + sharedAct.getString("Name", ""), Toast.LENGTH_LONG).show();
    }


}
