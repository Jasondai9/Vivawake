package vivawake.vivawake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;


import java.util.ArrayList;

public class RecyclerViewAdapter_Activity extends RecyclerView.Adapter<RecyclerViewAdapter_Activity.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter_Act";

    public ArrayList<String> mActivityNames = new ArrayList<>();
    public ArrayList<String> mHourTimes = new ArrayList<>();
    public ArrayList<String> mMinuteTimes = new ArrayList<>();
    public Context mContext;

    public RecyclerViewAdapter_Activity(Context context, ArrayList<String> activityNames, ArrayList<String> hourTimes, ArrayList<String> minuteTimes){
        mActivityNames = activityNames;
        mHourTimes = hourTimes;
        mMinuteTimes = minuteTimes;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.activityName.setText(mActivityNames.get(i));
        holder.hour.setText(mHourTimes.get(i));
        holder.minutes.setText(mMinuteTimes.get(i));
        holder.colon.setText(":");
        holder.activity_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "OnClick: clicked on: " + mActivityNames.get(i));
                Intent intent = new Intent(mContext, ResetActivity.class);
                intent.putExtra("activity_name", mActivityNames.get(i));
                intent.putExtra("hour_time", mHourTimes.get(i));
                intent.putExtra("minute_time", mMinuteTimes.get(i));
                intent.putExtra("counter", Integer.toString(i+1));
                mContext.startActivity(intent);
            }
        });
        

    }

    @Override
    public int getItemCount() {
        return mActivityNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView activityName, hour, minutes, colon;
        RelativeLayout activity_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.activity_name);
            hour = itemView.findViewById(R.id.hour_text);
            minutes = itemView.findViewById(R.id.minutes_text);
            activity_layout = itemView.findViewById(R.id.activity_layout);
            colon = itemView.findViewById(R.id.colon);
        }
    }
}
