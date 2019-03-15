package bluenet.com.lab6;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //宣告要顯示在的列表上的字串
        final String[] array = {"項目1", "項目2", "項目3", "項目4", "項目5"};

        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用預設Toast顯示文字訊息
                Toast.makeText(MainActivity.this,"預設Toast", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //宣告Toast物件
                Toast toast = new Toast(MainActivity.this);
                //設定Toast的顯示位置
                toast.setGravity(Gravity.TOP, 0, 50);
                //設定Toast持續時間
                toast.setDuration(Toast.LENGTH_SHORT);
                //設定自定義的Toast畫面
                toast.setView(View.inflate(MainActivity.this, R.layout.toast_custom,null));
                //顯示Toast
                toast.show();
            }
        });

        findViewById(R.id.btn_dialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //宣告AlertDialog物件，setButton可以在Dialog對應位置顯示按鈕
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("按鍵式對話框") //顯示標題
                        .setMessage("對話框內容")    //顯示文字內容
                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("拒絕", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"拒絕", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"確定", Toast.LENGTH_SHORT).show();
                            }
                        }).show();    //顯示Dialog
            }
        });

        findViewById(R.id.btn_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //宣告AlertDialog物件，setItems可以在Dialog顯示列表
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("列表式對話框") //顯示標題
                        .setItems(array, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"你選的是" + array[which], Toast.LENGTH_SHORT).show();
                            }
                        }).show();    //顯示Dialog
            }
        });

        findViewById(R.id.btn_dialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //宣告變數用以保存選擇位置
                final int[] position = {0};
                //宣告AlertDialog物件，setSingleChoiceItems可以在Dialog顯示單選框
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("單選式對話框") //顯示標題
                        .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                position[0] = which;
                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"你選的是" + array[position[0]], Toast.LENGTH_SHORT).show();
                            }
                        }).show();    //顯示Dialog
            }
        });
    }
}
