package vivawake.vivawake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Alarm> alarmArrayList;
    Context context;
    private ArrayList<String> alarmNames = new ArrayList<>();
    private ArrayList<String> alarmHours = new ArrayList<>();
    private ArrayList<String> alarmMinutes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        createAlarmArrayList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SetupActivity.class));
            }
        });

        initAlarms();
        this.context = context;
    }
    private void initAlarms(){
        SharedPreferences sharedCounter = getSharedPreferences("alarmCounter", Context.MODE_PRIVATE);
        int alarmCounter = sharedCounter.getInt("counter", 0);
        String alarmName;
        //SharedPreferences pref;
        for(int i = 1; i <= alarmCounter; i++){
            SharedPreferences pref = getSharedPreferences("alarm"+i, Context.MODE_PRIVATE);
            alarmNames.add(pref.getString("alarmName", ""));
            alarmHours.add(Integer.toString(pref.getInt("hour", 0)));
            alarmMinutes.add(Integer.toString(pref.getInt("minute", 0)));
        }
        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_alarm);
        RecyclerViewAdapter_Alarm adapter = new RecyclerViewAdapter_Alarm(this, alarmNames, alarmHours, alarmMinutes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void createAlarmArrayList(){
        SharedPreferences alarmCounter = getSharedPreferences("alarmCounter", Context.MODE_PRIVATE);
        int numAlarms = alarmCounter.getInt("counter", 0);

        alarmArrayList = new ArrayList<Alarm>(numAlarms+1);

        if(numAlarms == 0)
            return;

        SharedPreferences sharedAlarms;

        for(int i = 1; i <= numAlarms; i++) {
            String alarmID = "alarm" + i;
            sharedAlarms = getSharedPreferences(alarmID, Context.MODE_PRIVATE);
            alarmArrayList.add(new Alarm(sharedAlarms.getString("alarmName", ""),sharedAlarms.getString("ringtone", ""),
                    sharedAlarms.getInt("hour", 0), sharedAlarms.getInt("minute", 0), sharedAlarms.getString("alarmID", "")));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        startActivity(new Intent(this, ActivityActivity.class));

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
