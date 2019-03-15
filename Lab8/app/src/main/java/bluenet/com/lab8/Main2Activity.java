package bluenet.com.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    //宣告元件
    private EditText ed_name, ed_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //連結畫面元件
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判斷是否輸入姓名與電話
                if(ed_name.length()<1)
                    Toast.makeText(Main2Activity.this,"請輸入姓名",Toast.LENGTH_SHORT).show();
                else if(ed_phone.length()<1)
                    Toast.makeText(Main2Activity.this,"請輸入電話",Toast.LENGTH_SHORT).show();
                else{
                    //將姓名與電話放入Bundle
                    Bundle b = new Bundle();
                    b.putString("name", ed_name.getText().toString());
                    b.putString("phone", ed_phone.getText().toString());
                    //透過setResult將資料回傳
                    setResult(Activity.RESULT_OK, new Intent().putExtras(b));
                    //結束Main2Activity
                    finish();
                }
            }
        });
    }
}
