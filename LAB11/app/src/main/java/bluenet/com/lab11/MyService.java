package bluenet.com.lab11;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class MyService extends Service {
    //計數器狀態
    static Boolean flag = false;
    //計數器數值
    private int h=0, m=0, s=0;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        flag = intent.getBooleanExtra("flag", false);

        new Thread(() -> {
            while(flag){
                try {
                    //延遲1s
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //計數器加一
                s++;
                //秒數大於60進位
                if (s >= 60) {
                    s = 0;
                    m++;
                    //分鐘數大於60進位
                    if (m >= 60) {
                        m = 0;
                        h++;
                    }
                }
                //發送帶有『MyMessage』識別字串的廣播
                Intent intent1 = new Intent("MyMessage");
                //將時間放入Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("H", h);
                bundle.putInt("M", m);
                bundle.putInt("S", s);
                //發送廣播
                sendBroadcast(intent1.putExtras(bundle));
            }
        }).start();
        //自動重啟，但不會保留Intent
        return Service.START_STICKY;
    }
}
