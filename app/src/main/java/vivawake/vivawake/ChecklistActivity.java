package vivawake.vivawake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ChecklistActivity extends AppCompatActivity {

    public static ArrayList<String> activityNames;
    public static ArrayList<String> hourTimes;
    public static ArrayList<String> minuteTimes;
    public static long preparationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        preparationTime = 0;

        activityNames = new ArrayList<String>();
        hourTimes = new ArrayList<String>();
        minuteTimes = new ArrayList<String>();


        initActivities();
    }

    private void initActivities(){
        SharedPreferences sharedCounter = getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        int activityCounter = sharedCounter.getInt("counter", 0);
        String activityName;

        for(int i = 1; i <= activityCounter; i++){
            SharedPreferences pref = getSharedPreferences("Activity"+i, Context.MODE_PRIVATE);
            activityNames.add(pref.getString("Name", ""));
            hourTimes.add(Integer.toString(pref.getInt("Hour", 0)));
            minuteTimes.add(Integer.toString(pref.getInt("Minute", 0)));
        }
        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_checklist);
        RecyclerViewAdapter_Checklist adapter = new RecyclerViewAdapter_Checklist(this, activityNames, hourTimes, minuteTimes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChecklistActivity.this));
    }
}
