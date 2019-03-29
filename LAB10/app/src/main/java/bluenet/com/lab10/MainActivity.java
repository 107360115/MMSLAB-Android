package bluenet.com.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            //啟動Service並結束MainActivity
            startService(new Intent(MainActivity.this, MyService.class));
            Toast.makeText(MainActivity.this, "啟動Service", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
