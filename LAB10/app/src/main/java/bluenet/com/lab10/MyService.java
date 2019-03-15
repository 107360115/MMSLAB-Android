package bluenet.com.lab10;

import android.app.Service;
import android.content.Intent;
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //延遲5秒
                    Thread.sleep(5000);
                    //宣告Intent啟動Main2Activity
                    Intent intent = new Intent(MyService.this, Main2Activity.class);
                    //Service要啟動Activity要加入Flag定義要去產生一個新的Activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyService.this.startActivity(intent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        //結束後不再重啟
        return Service.START_NOT_STICKY;
    }
}
