package bluenet.com.lab7;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
//Implement ViewHolder
public class MyAdapter2 extends BaseAdapter {
    private ArrayList<Item> data;
    private int layout;
    //ViewHolder類別，用來緩存畫面中的元件
    private class ViewHolder{
        private ImageView img_photo;
        private TextView tv_name;
    }

    MyAdapter2(int layout, ArrayList<Item> data){
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
    //回傳某筆項目Id
    @Override
    public long getItemId(int position) {
        return 0;
    }
    //取得畫面
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;  //宣告ViewHolder

        if(convertView==null){
            //建立畫面
            convertView = View.inflate(parent.getContext(), layout, null);
            //建立ViewHolder
            holder = new ViewHolder();
            //將ViewHolder作為View的Tag
            convertView.setTag(holder);
            //連結畫面元件
            holder.img_photo = convertView.findViewById(R.id.img_photo);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
        }else   //從Tag取得ViewHolder
            holder = (ViewHolder)convertView.getTag();
        //根據position顯示圖片與名稱
        holder.img_photo.setImageResource(data.get(position).photo);
        holder.tv_name.setText(data.get(position).name);

        return convertView;
    }
}
