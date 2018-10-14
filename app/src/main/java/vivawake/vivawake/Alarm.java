package vivawake.vivawake;

import java.time.OffsetTime;
import java.util.Calendar;

public class Alarm {
    String alarmName, ringtone;
    int hour, minute, alarmID;

    Alarm(String alarmName, int hour, int minute){
        this.alarmName = alarmName;
        this.hour = hour;
        this.minute = minute;
    }

    Alarm(String alarmName, String ringtone, int hour, int minute, int alarmID){
        this.alarmName = alarmName;
        this.ringtone = ringtone;
        this.hour = hour;
        this.minute = minute;
        this.alarmID = alarmID;
    }
}
