package vivawake.vivawake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Intent ringtoneServiceIntent = new Intent(context, RingtonePlayerService.class);
        context.startService(ringtoneServiceIntent);
        Vibrator vibe = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibe.vibrate(2000);                                                     //vibration duration in ms
    }
}

