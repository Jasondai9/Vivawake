package vivawake.vivawake;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

public class RingtonePlayerService extends Service {
    MediaPlayer ringtonePlayer;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        
        //ringtonePlayer = MediaPlayer.create(this, R.);
        ringtonePlayer.start();
        return START_NOT_STICKY;
    }
}
