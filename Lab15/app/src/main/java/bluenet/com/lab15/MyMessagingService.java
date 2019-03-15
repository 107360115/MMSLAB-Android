package bluenet.com.lab15;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyMessagingService extends FirebaseMessagingService {
    //APP取得新token時呼叫，通常是在第一次啟動APP時會自動與Firebase註冊
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("Firebase", "onNewToken  " + token);
    }
    //APP在前景時收到Notification Message會呼叫
    @Override
    public void onMessageReceived(RemoteMessage msg) {
        super.onMessageReceived(msg);
        Log.e("Firebase","onMessageReceived");
        Log.e("Firebase", msg.getFrom());
        //透過for loop將msg夾帶的資料輸出
        for(Map.Entry<String, String> entry : msg.getData().entrySet())
            Log.e("message",entry.getKey() + "/" + entry.getValue());
    }
}
