package bluenet.com.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
            //新增聯絡人
            Contact contact = new Contact(data.getExtras().getString("name"),
                    data.getExtras().getString("phone"));
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
        //建立LinearLayoutManager物件
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //建立GridLayoutManager物件
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        //設定垂直顯示
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(contacts);
        //連結Adapter
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_add).setOnClickListener(v -> {
            //透過startActivityForResult發出Intent，並紀錄請求對象
            startActivityForResult(new Intent(MainActivity.this,
                    Main2Activity.class),1);
        });
    }
}
//自定義聯絡人類別
class Contact{
    String name;    //姓名
    String phone;   //電話

    Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
}
