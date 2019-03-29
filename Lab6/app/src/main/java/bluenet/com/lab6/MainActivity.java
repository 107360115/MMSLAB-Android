package bluenet.com.lab6;

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

        findViewById(R.id.btn_toast).setOnClickListener(v -> showToast("預設Toast"));

        findViewById(R.id.btn_custom).setOnClickListener(v -> {
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
        });

        findViewById(R.id.btn_dialog1).setOnClickListener(v -> {
            //宣告AlertDialog物件，setButton可以在Dialog對應位置顯示按鈕
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("按鍵式對話框") //顯示標題
                    .setMessage("對話框內容")    //顯示文字內容
                    .setNeutralButton("取消", (dialog, which) -> showToast("拒絕"))
                    .setNegativeButton("拒絕", (dialog, which) -> showToast("拒絕"))
                    .setPositiveButton("確定", (dialog, which) -> showToast("確定"))
                    .show();    //顯示Dialog
        });

        findViewById(R.id.btn_dialog2).setOnClickListener(v -> {
            //宣告AlertDialog物件，setItems可以在Dialog顯示列表
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("列表式對話框") //顯示標題
                    .setItems(array, (dialog, which) -> showToast("你選的是" + array[which]))
                    .show();    //顯示Dialog

        });

        findViewById(R.id.btn_dialog3).setOnClickListener(v -> {
            //宣告變數用以保存選擇位置
            final int[] position = {0};
            //宣告AlertDialog物件，setSingleChoiceItems可以在Dialog顯示單選框
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("單選式對話框") //顯示標題
                    .setSingleChoiceItems(array, 0, (dialog, which) -> position[0] = which)
                    .setPositiveButton("確定", (dialog, which) -> showToast("你選的是" + array[position[0]]))
                    .show();    //顯示Dialog

        });
    }
    //使用預設Toast顯示文字訊息
    private void showToast(String text){
        Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();
    }
}
