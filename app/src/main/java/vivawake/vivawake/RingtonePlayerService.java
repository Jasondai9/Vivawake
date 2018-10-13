package vivawake.vivawake;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

public class RingtonePlayerService extends Service {
    MediaPlayer ringtonePlayer;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(ringtonePlayer ==null){
            ringtonePlayer = MediaPlayer.create(this, R.raw.ringtone1);
            ringtonePlayer.start();
        }else {
            ringtonePlayer.release();
            ringtonePlayer = null;
        }


        return START_NOT_STICKY;
    }

    public void onDestroy(){
        Toast.makeText(this, "onDestroy in RingtonePlayerService called", Toast.LENGTH_SHORT).show();
    }
}
