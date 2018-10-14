package vivawake.vivawake;

import java.time.OffsetTime;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;


public class Alarm {
    String alarmName, ringtone, alarmID;
    int hour, minute;
    AlarmManager alarmManager;
    final Calendar calendar = Calendar.getInstance();


    Alarm(String alarmName, String ringtone, int hour, int minute, String alarmID){
        this.alarmName = alarmName;
        this.ringtone = ringtone;
        this.hour = hour;
        this.minute = minute;
        this.alarmID = alarmID;
    }

    public void turnMeOn(Context context){
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        long alarmTime = calendar.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
    }

    public void turnMeOff(Context context){
        Intent intent = new Intent(context, RingtonePlayerService.class);
        context.startService(intent);
    }
}
