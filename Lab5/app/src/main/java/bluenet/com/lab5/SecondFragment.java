package bluenet.com.lab5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    private static final String TAG = "SecondFragment";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化頁面
        Log.e(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //連結畫面
        Log.e(TAG,"onCreateView");
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //連結Fragment與Activity
        Log.e(TAG,"onActivityCreated");
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
    public void onDestroyView() {
        super.onDestroyView();
        //移除畫面
        Log.e(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //回收頁面
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //移除Fragment
        Log.e(TAG,"onDetach");
    }
}
