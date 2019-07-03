package bluenet.com.lab14;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //解析Intent取得JSON字串，把json物件以Data格式做轉換
            Data data = new Gson().fromJson(intent.getExtras().getString("json"), Data.class);
            final String[] items = new String[data.result.results.length];
            //建立一個字串陣列，用於提取『站名』與『目的地』資訊
            for(int i=0; i<items.length; i++)
                items[i] = "\n列車即將進入 :"+ data.result.results[i].Station +
                        "\n列車行駛目的地 :" + data.result.results[i].Destination;
            //使用者介面的操作必須在UI Thread上執行
            //使用Dialog呈現結果
            MainActivity.this.runOnUiThread(() -> new AlertDialog.Builder(MainActivity.this)
                    .setTitle("台北捷運列車到站站名")
                    .setItems(items,null)
                    .show());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //註冊Receiver，用來接收Http Response
        registerReceiver(receiver, new IntentFilter("MyMessage"));

        findViewById(R.id.btn_query).setOnClickListener(v -> {
            //建立一個Request物件，並使用url()方法加入URL
            Request req = new Request.Builder()
                    .url("https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b").build();
            //建立okHttpClient物件，newCall()送出請求，enqueue()接收回傳
            new OkHttpClient().newCall(req).enqueue(new Callback() {
                //發送失敗執行此方法
                @Override
                public void onFailure(@Nullable Call call, @Nullable IOException e) {
                    Log.e("查詢失敗", e.toString());
                }
                //發送成功執行此方法
                @Override
                public void onResponse(@Nullable Call call, @Nullable Response response) throws IOException {
                    if(response==null) return;

                    if(response.code()==200){
                        //判斷回傳是否為空
                        if(response.body()==null) return;
                        //用response.body()?.string()取得Json字串，並使用廣播發送
                        sendBroadcast(new Intent("MyMessage")
                                .putExtra("json", response.body().string()));
                    }else if(!response.isSuccessful())
                        Log.e("伺服器錯誤", response.code() + " " + response.message());
                    else
                        Log.e("其他錯誤", response.code() + " " + response.message());
                }
            });
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //註銷Receiver
        unregisterReceiver(receiver);
    }
}

class Data {
    Result result;

    class Result {
        Results[] results;

        class Results {
            String Station; //站名
            String Destination; //目的地
        }
    }
}