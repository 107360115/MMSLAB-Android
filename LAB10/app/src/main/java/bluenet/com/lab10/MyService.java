package bluenet.com.lab10;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();

        new Handler().postDelayed(() -> {
            //宣告Intent啟動Main2Activity
            Intent intent = new Intent(MyService.this, Main2Activity.class);
            //Service要啟動Activity要加入Flag定義要去產生一個新的Activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyService.this.startActivity(intent);
        }, 5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        //結束後不再重啟
        return Service.START_NOT_STICKY;
    }
}
