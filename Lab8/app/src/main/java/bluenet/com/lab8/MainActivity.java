package bluenet.com.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    //宣告元件
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null) return;
        //驗證發出對象與回傳狀態
        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            //建立Contact物件，讀取Bundle中的資料
            Contact contact = new Contact();
            contact.name = data.getExtras().getString("name");
            contact.phone = data.getExtras().getString("phone");
            contacts.add(contact);
            //更新列表資料
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        recyclerView = findViewById(R.id.recyclerView);
        ////建立LinearLayoutManager物件
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //設定垂直顯示
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MyAdapter(contacts);
        //連結Adapter
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透過startActivityForResult發出Intent，並紀錄請求對象
                startActivityForResult(new Intent(MainActivity.this,
                        Main2Activity.class),1);
            }
        });
    }
}
//自定義聯絡人類別
class Contact{
    String name;    //姓名
    String phone;   //電話
}
