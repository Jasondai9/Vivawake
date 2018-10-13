package vivawake.vivawake;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ActivitycreationActivity extends AppCompatActivity {
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

    public void saveActivity(View view, activity activity){
        SharedPreferences sharedAct = getSharedPreferences(activity.getName(), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedAct.edit();
        editor.putInt("Hour", activity.getHour());
        editor.putInt("Minute", activity.getMinutes());
        editor.putString("Name", activity.getName());

        Toast.makeText(this, "Saved!" + sharedAct.getString("Name", ""), Toast.LENGTH_LONG).show();
    }


}
