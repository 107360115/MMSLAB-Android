package bluenet.com.lab7;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //宣告元件
    private Spinner spinner;
    private GridView gridview;
    private ListView listView;

    private ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        spinner = findViewById(R.id.spinner);
        gridview = findViewById(R.id.gridview);
        listView = findViewById(R.id.listView);
        //從R讀取圖片資源
        TypedArray imgArray = getResources().obtainTypedArray(R.array.imgArray);
        String[] nameArray = getResources().getStringArray(R.array.nameArray);
        for(int i=0;i<imgArray.length();i++) {
            //建立項目物件，放入圖片資源與名稱
            Item item  = new Item(imgArray.getResourceId(i,0), nameArray[i]);
            items.add(item);
        }
        //回收TypedArray
        imgArray.recycle();
        //連結Adapter，設定layout為adapter_horizontal
        spinner.setAdapter(new MyAdapter(R.layout.adapter_horizontal, items));
        //設定橫向顯示的項目筆數
        gridview.setNumColumns(3);
        //連結Adapter，設定layout為adapter_vertical
        gridview.setAdapter(new MyAdapter(R.layout.adapter_vertical, items));
        //gridview.setAdapter(new MyAdapter2(R.layout.adapter_vertical, items));
        //連結Adapter，設定layout為simple_list_item_1與字串陣列
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new String[]{"項目1","項目2","項目3","項目4","項目5","項目6","項目7","項目8","項目9"}));
    }
}
//自定義類別
class Item{
    int photo;      //圖片Resource
    String name;    //名稱

    Item(int photo, String name){
        this.photo = photo;
        this.name = name;
    }
}