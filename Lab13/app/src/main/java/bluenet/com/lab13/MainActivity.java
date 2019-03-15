package bluenet.com.lab13;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //宣告元件
    private EditText ed_book, ed_price;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items = new ArrayList<>();

    private SQLiteDatabase dbrw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        ed_book = findViewById(R.id.ed_book);
        ed_price = findViewById(R.id.ed_price);
        listView = findViewById(R.id.listView);
        //宣告Adapter並連結ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        //取得資料庫實體
        dbrw = new MyDBHelper(this).getWritableDatabase();

        findViewById(R.id.btn_query).setOnClickListener(v -> {
            Cursor c;
            if(ed_book.length()<1)
                c = dbrw.rawQuery("SELECT * FROM myTable",null);
            else
                c = dbrw.rawQuery("SELECT * FROM myTable WHERE book LIKE '"+
                        ed_book.getText().toString() + "'",null);
            //從第一筆開始輸出
            c.moveToFirst();
            //清空舊資料
            items.clear();
            showToast("共有" + c.getCount() + "筆資料");
            for(int i = 0; i < c.getCount(); i++){
                items.add("書名:" + c.getString(0) + "\t\t\t\t價格:" + c.getString(1));
                //移動到下一筆
                c.moveToNext();
            }
            //更新列表資料
            adapter.notifyDataSetChanged();
            //關閉Cursor
            c.close();
        });

        findViewById(R.id.btn_insert).setOnClickListener(v -> {
            //判斷是否沒有填入書名或價格
            if(ed_book.length()<1 || ed_price.length()<1)
                showToast("欄位請勿留空");
            else{
                try{
                    //新增一筆book與price資料進入myTable資料表
                    dbrw.execSQL("INSERT INTO myTable(book, price) VALUES(?,?)",
                            new Object[]{ed_book.getText().toString(),
                                    ed_price.getText().toString()});
                    showToast("新增書名"+ ed_book.getText().toString() +
                            "    價格"+ ed_price.getText().toString());
                    cleanEditText();
                }catch (Exception e){
                    showToast("新增失敗:"+e.toString());
                }
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(v -> {
            //判斷是否沒有填入書名或價格
            if(ed_book.length()<1 || ed_price.length()<1)
                showToast("欄位請勿留空");
            else{
                try{
                    //更新book欄位為輸入字串（ed_book）的資料的price欄位數值
                    dbrw.execSQL("UPDATE myTable SET price = " + ed_price.getText().toString() +
                            " WHERE book LIKE '" + ed_book.getText().toString() + "'");
                    showToast("更新書名"+ ed_book.getText().toString() +
                            "    價格"+ ed_price.getText().toString());
                    cleanEditText();
                }catch (Exception e){
                    showToast("更新失敗:"+e.toString());
                }
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            //判斷是否沒有填入書名
            if(ed_book.length()<1)
                showToast("書名請勿留空");
            else{
                try{
                    //從myTable資料表刪除book欄位為輸入字串（ed_book）的資料
                    dbrw.execSQL("DELETE FROM myTable WHERE book LIKE '" +
                            ed_book.getText().toString() + "'");
                    showToast("刪除書名"+ ed_book.getText().toString());
                    cleanEditText();
                }catch (Exception e){
                    showToast("刪除失敗:"+e.toString());
                }
            }
        });
    }

    private void showToast(String text){
        Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();
    }
    //清空輸入欄
    private void cleanEditText(){
        ed_book.setText("");
        ed_price.setText("");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //關閉資料庫
        dbrw.close();
    }
}
