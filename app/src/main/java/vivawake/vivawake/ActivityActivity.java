package vivawake.vivawake;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import java.util.ArrayList;

public class ActivityActivity extends AppCompatActivity {
    private static final String TAG = "ActivityActivity";

    //vars
    public static ArrayList<String> activityNames;
    public static ArrayList<String> hourTimes;
    public static ArrayList<String> minuteTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityNames = new ArrayList<String>();
        hourTimes = new ArrayList<String>();
        minuteTimes = new ArrayList<String>();

        initActivities();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityCreation();
            }
        });


    }

    private void initActivities(){
        SharedPreferences sharedCounter = getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        int activityCounter = sharedCounter.getInt("counter", 0);
        String activityName;
        //SharedPreferences pref;
        for(int i = 1; i <= activityCounter; i++){
            SharedPreferences pref = getSharedPreferences("Activity"+i, Context.MODE_PRIVATE);
            activityNames.add(pref.getString("Name", ""));
            int hour = pref.getInt("Hour", 0);
            int minute = pref.getInt("Minute", 0);
            if(hour/10 == 0)
                hourTimes.add(""+0+Integer.toString(hour));
            else
                hourTimes.add(Integer.toString(hour));
            if(minute/10 == 0)
                minuteTimes.add(""+0+Integer.toString(minute));
            else
                minuteTimes.add(Integer.toString(minute));
        }
        initRecyclerView();
    }



    public void openActivityCreation(){
       startActivity(new Intent(this, ActivitycreationActivity.class));
    }

    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: int recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter_Activity adapter = new RecyclerViewAdapter_Activity(this, activityNames, hourTimes, minuteTimes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityActivity.this));
    }
}
