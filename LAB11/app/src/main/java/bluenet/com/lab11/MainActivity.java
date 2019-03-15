package bluenet.com.lab11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //宣告元件
    private TextView tv_clock;
    private Button btn_start;
    //計數器狀態
    private Boolean flag = false;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();
            tv_clock.setText(String.format("%02d:%02d:%02d", b.getInt("H"), b.getInt("M"), b.getInt("S")));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        tv_clock = findViewById(R.id.tv_clock);
        btn_start = findViewById(R.id.btn_start);
        //註冊Receiver來接收有『MyMessage』識別字串的廣播
        registerReceiver(receiver, new IntentFilter("MyMessage"));
        //取得Service狀態
        flag = MyService.flag;
        if(flag)
            btn_start.setText("暫停");
        else
            btn_start.setText("開始");

        btn_start.setOnClickListener(v -> {
            flag = !flag;
            if(flag) {
                btn_start.setText("暫停");
                Toast.makeText(MainActivity.this, "計時開始", Toast.LENGTH_SHORT).show();
            }else {
                btn_start.setText("開始");
                Toast.makeText(MainActivity.this, "計時暫停", Toast.LENGTH_SHORT).show();
            }
            //啟動Service
            startService(new Intent(MainActivity.this, MyService.class).putExtra("flag", flag));
        });
    }

    public void onDestroy(){
        super.onDestroy();
        //註銷Receiver
        unregisterReceiver(receiver);
    }
}
