package vivawake.vivawake;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetActivity  extends AppCompatActivity{
    private static final String TAG = "ResetActivity";
    EditText hours;
    EditText minutes;
    EditText name;
    //LinearLayout mLayout;
    String ActivityID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityreset);
        hours = (EditText) findViewById(R.id.HoursTxt);
        minutes = (EditText) findViewById(R.id.MinutesTxt);
        name = (EditText) findViewById(R.id.ActivityNameTxt);
        Button save = (Button) findViewById(R.id.saveButton);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.parseInt(hours.getText().toString());
                int minute = Integer.parseInt(minutes.getText().toString());
                String activityName = name.getText().toString();
                activity activity = new activity(activityName, hour, minute);
                saveActivity(v, activity);
                Intent backIntent = new Intent(ResetActivity.this, ActivityActivity.class);
                startActivity(backIntent);
            }
        });


    }

    public String saveActivity(View view, activity act){

        SharedPreferences sharedAct = getSharedPreferences(ActivityID, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedAct.edit();
        editor.putInt("Hour", act.getHour());
        editor.putInt("Minute", act.getMinutes());
        editor.putString("Name", act.getName());
        editor.apply();

        //Toast.makeText(this, "Saved!" + sharedCounter.getInt("counter", 0), Toast.LENGTH_LONG).show();
        return ActivityID;
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("activity_name") && getIntent().hasExtra("hour_time") && getIntent().hasExtra("minute_time") && getIntent().hasExtra("counter")){
            Log.d(TAG, "getIncomingIntent: found intent extras");
            String activityName = getIntent().getStringExtra("activity_name");
            String hourTime = getIntent().getStringExtra("hour_time");
            String minuteTime = getIntent().getStringExtra("minute_time");
            String counter = getIntent().getStringExtra("counter");
            setWidgets(activityName, hourTime, minuteTime, counter);
        }
    }
    private void setWidgets(String activityName, String hourTime, String minuteTime, String counter){
        Log.d(TAG, "setWidgets: Setting Widgets");
        EditText activity = findViewById(R.id.ActivityNameTxt);
        EditText hour = findViewById(R.id.HoursTxt);
        EditText minute = findViewById(R.id.MinutesTxt);
        ActivityID = "Activity" + counter;
        activity.setText(activityName);
        hour.setText(hourTime);
        minute.setText(minuteTime);
    }
}
