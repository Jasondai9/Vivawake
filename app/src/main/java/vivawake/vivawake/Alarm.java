package vivawake.vivawake;

import java.time.OffsetTime;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;


public class Alarm {
    String alarmName, ringtone, alarmID;
    int hour, minute;
    AlarmManager alarmManager;
    final Calendar calendar = Calendar.getInstance();
    PendingIntent pendingIntent;

    final static public long MS_IN_MIN = 60000;
    final static public long MS_IN_HOUR = 3600000;


    Alarm(String alarmName, String ringtone, int hour, int minute, String alarmID){
        this.alarmName = alarmName;
        this.ringtone = ringtone;
        this.hour = hour;
        this.minute = minute;
        this.alarmID = alarmID;
    }

    public void turnMeOn(Context context){
        Intent intent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);


        SharedPreferences alarmShare = context.getSharedPreferences(alarmID, Context.MODE_PRIVATE);
        calendar.set(Calendar.HOUR_OF_DAY, alarmShare.getInt("hour", 0));
        calendar.set(Calendar.MINUTE, alarmShare.getInt("minute", 0));

        long alarmTime = calculateAlarmTime(calendar.getTimeInMillis(), context);
        alarmTime -= alarmTime%MS_IN_MIN;
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
    }
    
    public void cancel(Context context){
        pendingIntent.cancel();
    }

    public void turnMeOff(Context context){
        Intent intent = new Intent(context, RingtonePlayerService.class);
        context.startService(intent);
    }

    private long getTotalActivityTime(Context context){
        long totalActivityTime = 0;
        SharedPreferences activityCounter = context.getSharedPreferences("activityCounter", Context.MODE_PRIVATE);
        for(int i = 1; i <=activityCounter.getInt("counter", 0); i++) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("Activity"+i, Context.MODE_PRIVATE);
            totalActivityTime += sharedPreferences.getInt("Minute", 0) * MS_IN_MIN;
            totalActivityTime += sharedPreferences.getInt("Hour", 0) * MS_IN_HOUR;
        }
        return totalActivityTime;
    }

    public long calculateAlarmTime(long arrivalTime, Context context){
        return arrivalTime - getTotalActivityTime(context);
    }
}
