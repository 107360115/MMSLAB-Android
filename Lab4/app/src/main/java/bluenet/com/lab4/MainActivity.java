package bluenet.com.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //宣告元件
    private TextView tv_meal;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null) return;
        //驗證發出對象與回傳狀態
        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            //讀取Bundle中的資料
            Bundle b = data.getExtras();
            tv_meal.setText(String.format("飲料: %s\n\n甜度: %s\n\n冰塊: %s\n\n", b.getString("drink"),
                    b.getString("sugar"), b.getString("ice")));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        tv_meal = findViewById(R.id.tv_meal);
        //『選擇』按鈕點擊監聽
        findViewById(R.id.btn_choice).setOnClickListener(v -> {
            //透過startActivityForResult發出Intent，並紀錄請求對象
            startActivityForResult(new Intent(MainActivity.this, Main2Activity.class),1);
        });
    }
}
