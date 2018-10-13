package vivawake.vivawake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Intent ringtoneServiceIntent = new Intent(context, RingtonePlayerService.class);
        context.startService(ringtoneServiceIntent);
    }
}

