package bluenet.com.lab12;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final static int REQUEST_PERMISSIONS = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                for(int result:grantResults){
                    //若使用者拒絕給予權限則關閉APP
                    if(result != PackageManager.PERMISSION_GRANTED) {
                        finish();
                    } else {
                        //連接MapFragment物件
                        SupportMapFragment map = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
                        map.getMapAsync(this);
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: 2019/3/15   將AndroidManifest的YOUR_API_KEY換成自行申請的金鑰
        //檢查使用者是否已授權定位權限，向使用者要求權限
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSIONS);
        else{
            //連接MapFragment物件
            SupportMapFragment map = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
            map.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //檢查使用者是否已授權定位權限
        if  (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        //顯示目前位置與定位的按鈕
        map.setMyLocationEnabled(true);
        //建立MarkerOptions物件
        MarkerOptions m1 = new MarkerOptions();
        m1.position(new LatLng(25.033611, 121.565000));
        m1.title("台北101");
        m1.draggable(true);
        map.addMarker(m1);
        //建立MarkerOptions物件
        MarkerOptions m2 = new MarkerOptions();
        //設定Marker座標
        m2.position(new LatLng(25.047924, 121.517081));
        //設定Marker標題
        m2.title("台北車站");
        m2.draggable(true);
        //將Marker加入Map並顯示
        map.addMarker(m2);
        //建立PolylineOptions物件
        PolylineOptions polylineOpt = new PolylineOptions();
        //加入座標到PolylineOptions
        polylineOpt.add(new LatLng(25.033611, 121.565000));
        polylineOpt.add(new LatLng(25.032728, 121.565137));
        polylineOpt.add(new LatLng(25.047924, 121.517081));
        //設定PolylineOptions顏色
        polylineOpt.color(Color.BLUE);
        //將PolylineOptions加入Map並顯示
        Polyline polyline = map.addPolyline(polylineOpt);
        //設定Polyline寬度
        polyline.setWidth(10);
        //移動鏡頭（畫面）到指定座標與深度
        map.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(25.034, 121.545), 13));
    }
}
