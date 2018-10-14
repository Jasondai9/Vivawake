package vivawake.vivawake;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class RecyclerViewAdapter_Alarm extends RecyclerView.Adapter<RecyclerViewAdapter_Alarm.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter_Alarm";

    private ArrayList<String> mAlarmNames = new ArrayList<>();
    private ArrayList<String> mAlarmHours = new ArrayList<>();
    private ArrayList<String> mAlarmMinutes = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter_Alarm(Context context, ArrayList<String> alarmName, ArrayList<String> alarmHour, ArrayList<String> alarmMinute){
        mAlarmNames = alarmName;
        mAlarmHours = alarmHour;
        mAlarmMinutes = alarmMinute;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alarm_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.alarmHour.setText(mAlarmHours.get(i));
        viewHolder.minute.setText(mAlarmMinutes.get(i));
        viewHolder.colon.setText(";");
        if(Integer.parseInt(mAlarmHours.get(i))/12 == 1) {
            viewHolder.AMPM.setText("PM");
        }else{
            viewHolder.AMPM.setText("AM");
        }
        viewHolder.alarmName.setText(mAlarmNames.get(i));
        viewHolder.alarmLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(mContext, i, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlarmNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView alarmHour, minute, colon, AMPM, alarmName;
        Switch switch1;
        RelativeLayout alarmLayout;

        public ViewHolder(View itemView){
            super(itemView);
            alarmHour = itemView.findViewById(R.id.alarmHour2);
            minute = itemView.findViewById(R.id.alarmMinute);
            colon = itemView.findViewById(R.id.alarmColon);
            AMPM = itemView.findViewById(R.id.AMPM);
            alarmName = itemView.findViewById(R.id.alarmName);
        }
    }
}
