package bluenet.com.lab5;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化頁面
        Log.e(TAG,"onCreate");
        //連結畫面
        setContentView(R.layout.activity_main);
        //連結畫面元件
        ViewPager viewPager = findViewById(R.id.viewPager);
        //連接Adapter，讓畫面(Fragment)與ViewPager建立關聯
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        //返回頁面
        Log.e(TAG,"onRestart");
    }

    @Override
    public void onStart() {
        super.onStart();
        //頁面可見
        Log.e(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        //頁面與使用者開始互動
        Log.e(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        //離開頁面
        Log.e(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //頁面不可見
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //回收頁面
        Log.e(TAG,"onDestroy");
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    //回傳對應位置的Fragment，決定頁面的呈現順序
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new FirstFragment();    //第一頁要呈現的Fragment
            case 1 : return new SecondFragment();   //第二頁要呈現的Fragment
            default: return new ThirdFragment();    //第三頁要呈現的Fragment
        }
    }
    //回傳Fragment頁數
    @Override
    public int getCount() {
        return 3;
    }
}
