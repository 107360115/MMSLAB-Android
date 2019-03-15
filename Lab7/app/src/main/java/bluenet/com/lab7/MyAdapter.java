package bluenet.com.lab7;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Item> data;
    private int layout;

    MyAdapter(int layout, ArrayList<Item> data){
        this.layout = layout;
        this.data = data;
    }
    //回傳項目筆數
    @Override
    public int getCount() {
        return data.size();
    }
    //回傳某筆項目
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    //取得畫面
    @Override
    public long getItemId(int position) {
        return 0;
    }
    //取得畫面
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), layout, null);
        //連結畫面元件
        ImageView img_photo = convertView.findViewById(R.id.img_photo);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        //根據position顯示圖片與名稱
        img_photo.setImageResource(data.get(position).photo);
        tv_name.setText(data.get(position).name);

        return convertView;
    }
}
