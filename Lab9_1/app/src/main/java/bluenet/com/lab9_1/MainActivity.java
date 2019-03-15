package bluenet.com.lab9_1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //建立兩個計數佔存
    private int rabprogress = 0, torprogress = 0;
    //宣告元件
    private SeekBar seekBar, seekBar2;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);
        btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(v -> {
            //關閉Button
            btn_start.setEnabled(false);
            //初始化佔存與SeekBar
            rabprogress = 0;
            torprogress = 0;
            seekBar.setProgress(0);
            seekBar2.setProgress(0);
            //執行龜兔賽跑
            runThread();
            runAsyncTask();
        });
    }

    private void runThread(){
        new Thread(() -> {
            //重複執行到計數器不小於100為止
            while(rabprogress<100 && torprogress<100){
                try {
                    //延遲100ms
                    Thread.sleep(100);
                    //隨機增加計數器0~2的值
                    rabprogress += (int)(Math.random() * 3);
                    //建立Message物件
                    Message msg = new Message();
                    //加入代號
                    msg.what = 1;
                    //透過sendMessage傳送訊息
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();   //執行Thread
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //寫入計數器數值到SeekBar
                    seekBar.setProgress(rabprogress);
                    if(rabprogress >= 100 && torprogress < 100){
                        Toast.makeText(MainActivity.this, "兔子勝利", Toast.LENGTH_SHORT).show();
                        //啟動Button
                        btn_start.setEnabled(true);
                    }
                    break;
            }
            return false;
        }
    });

    @SuppressLint("StaticFieldLeak")
    private void runAsyncTask(){
        new AsyncTask<Void, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                //重複執行到計數器不小於100為止
                while (torprogress < 100 && rabprogress < 100) {
                    try {
                        //延遲100ms
                        Thread.sleep(100);
                        //隨機增加計數器0~2的值
                        torprogress += (int)(Math.random() * 3);
                        //更新進度
                        publishProgress(torprogress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                //寫入計數器數值到SeekBar
                seekBar2.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (torprogress >= 100 && rabprogress < 100) {
                    Toast.makeText(MainActivity.this,"烏龜勝利", Toast.LENGTH_SHORT).show();
                    //啟動Button
                    btn_start.setEnabled(true);
                }
            }
        }.execute();    //執行AsyncTask
    }
}
