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

public class ChecklistActivity extends AppCompatActivity {
    private static final String TAG = "ChecklistActivity";

    //vars
    private static ArrayList<String> activityNames;
    private static ArrayList<String> hourTimes;
    private static ArrayList<String> minuteTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);



        activityNames = new ArrayList<String>();
        hourTimes = new ArrayList<String>();
        minuteTimes = new ArrayList<String>();

        initActivities();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityCreation();
            }
        });
*/

    }

    private void initActivities(){
        SharedPreferences sharedCounter = getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        int activityCounter = sharedCounter.getInt("counter", 0);
        String activityName;
        //SharedPreferences pref;
        for(int i = 1; i <= activityCounter; i++){
            SharedPreferences pref = getSharedPreferences("Activity"+i, Context.MODE_PRIVATE);
            activityNames.add(pref.getString("Name", ""));
            hourTimes.add(Integer.toString(pref.getInt("Hour", 0)));
            minuteTimes.add(Integer.toString(pref.getInt("Minute", 0)));
        }
        initRecyclerView();
    }



    public void openActivityCreation(){
        startActivity(new Intent(this, ActivitycreationActivity.class));
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_checklist);
        RecyclerViewAdapterChecklist adapter = new RecyclerViewAdapterChecklist(ChecklistActivity.this, activityNames, hourTimes, minuteTimes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChecklistActivity.this));
    }
}
