package vivawake.vivawake;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class RecyclerViewAdapter_Checklist extends RecyclerView.Adapter<RecyclerViewAdapter_Checklist.ViewHolder> {
    private ArrayList<String> mActivityNames = new ArrayList<>();
    private ArrayList<String> mHourTimes = new ArrayList<>();
    private ArrayList<String> mMinuteTimes = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter_Checklist(Context context, ArrayList<String> activityNames, ArrayList<String> hourTimes, ArrayList<String> minuteTimes) {
        mActivityNames = activityNames;
        mHourTimes = hourTimes;
        mMinuteTimes = minuteTimes;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checklistlayout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        holder.activityName.setText(mActivityNames.get(i));
        holder.hour.setText(mHourTimes.get(i));
        holder.minutes.setText(mMinuteTimes.get(i));
        holder.colon.setText(":");

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedCounter = mContext.getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
                int numActivities = sharedCounter.getInt("counter", 0);

                long timeToAlarmInMS = 0;

                for(int i = 1; i <= numActivities; i++){
                    if(holder.checkbox.isChecked()){
                        SharedPreferences sharedActivities = mContext.getSharedPreferences("Activity"+i, Context.MODE_PRIVATE);
                        timeToAlarmInMS += sharedActivities.getInt("Hour", 0)*3600000;
                        timeToAlarmInMS += sharedActivities.getInt("Minute", 0)*60000;
                    }
                }

                ChecklistActivity.preparationTime = timeToAlarmInMS;
                mContext.startActivity(new Intent(mContext, SetupActivity.class));
            }
        });

    }

    @Override
    public int getItemCount () {
        return mActivityNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView activityName, hour, minutes, colon;
        RelativeLayout activity_layout;
        CheckBox checkbox;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.ch_activity_name);
            hour = itemView.findViewById(R.id.ch_hour_text);
            minutes = itemView.findViewById(R.id.ch_minutes_text);
            activity_layout = itemView.findViewById(R.id.checklistLayout);
            colon = itemView.findViewById(R.id.ch_colon);
            checkbox = itemView.findViewById(R.id.checkbox);
            button = itemView.findViewById(R.id.checklistButton);
        }
    }
}


