package bluenet.com.lab11;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class MyService extends Service {
    //計數器狀態
    static Boolean flag = false;
    //計數器數值
    private int hour=0, minute=0, second=0;

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
                second++;
                //秒數大於60進位
                if (second >= 60) {
                    second = 0;
                    minute++;
                    //分鐘數大於60進位
                    if (minute >= 60) {
                        minute = 0;
                        hour++;
                    }
                }
                //發送帶有『MyMessage』識別字串的廣播
                Intent intent1 = new Intent("MyMessage");
                //將時間放入Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("H", hour);
                bundle.putInt("M", minute);
                bundle.putInt("S", second);
                //發送廣播
                sendBroadcast(intent1.putExtras(bundle));
            }
        }).start();
        //自動重啟，但不會保留Intent
        return Service.START_STICKY;
    }
}
