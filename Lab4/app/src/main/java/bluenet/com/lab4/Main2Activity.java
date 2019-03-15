package bluenet.com.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    //宣告元件
    private EditText ed_drink;
    private RadioGroup radioGroup, radioGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //連結畫面元件
        ed_drink = findViewById(R.id.ed_drink);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        //『送出』按鈕點擊監聽
        findViewById(R.id.btn_send).setOnClickListener(v -> {
            //判斷使用者是否輸入飲料名稱
            if(ed_drink.length()<1)
                Toast.makeText(Main2Activity.this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show();
            else{
                //將飲料名稱、甜度、冰塊放入Bundle
                Bundle b = new Bundle();
                b.putString("drink", ed_drink.getText().toString());
                b.putString("sugar", ((RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
                b.putString("ice", ((RadioButton)radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId())).getText().toString());
                //透過setResult將資料回傳
                setResult(Activity.RESULT_OK, new Intent().putExtras(b));
                //結束Main2Activity
                finish();
            }
        });
    }
}
