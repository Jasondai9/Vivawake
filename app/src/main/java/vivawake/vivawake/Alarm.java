package vivawake.vivawake;

import java.io.File;
import java.io.Serializable;
import java.time.OffsetTime;

public class Alarm implements Serializable {
    OffsetTime timeOfAlarm;
    //Ringtone declaration

    OffsetTime timeOfArrival;   //user input

    public void writeToFile(File file){
        
    }
}
