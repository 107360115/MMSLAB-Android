package bluenet.com.lab8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;
    //ViewHolder類別，用來緩存畫面中的元件
    class ViewHolder extends RecyclerView.ViewHolder{
        //宣告元件
        private TextView tv_name, tv_phone;
        private ImageView img_delete;
        //連結畫面元件
        ViewHolder(View v){
            super(v);
            tv_name = v.findViewById(R.id.tv_name);
            tv_phone = v.findViewById(R.id.tv_phone);
            img_delete = v.findViewById(R.id.img_delete);
        }
    }

    MyAdapter(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }
    //建立ViewHolder並連結畫面
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_row, viewGroup, false);
        return new ViewHolder(v);
    }
    //連結項目資料與元件
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_name.setText(contacts.get(position).name);
        holder.tv_phone.setText(contacts.get(position).phone);

        holder.img_delete.setOnClickListener(v -> {
            //移除聯絡人
            contacts.remove(position);
            //更新列表資料
            notifyDataSetChanged();
        });
    }
    //回傳項目筆數
    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
