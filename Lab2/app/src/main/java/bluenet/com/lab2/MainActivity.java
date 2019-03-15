package bluenet.com.lab2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //宣告元件
    private EditText ed_name;
    private TextView tv_text, tv_name, tv_winner, tv_mmora, tv_cmora;
    private RadioButton btn_scissor, btn_stone, btn_paper;
    private Button btn_mora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連結畫面元件
        ed_name = findViewById(R.id.ed_name);
        tv_text = findViewById(R.id.tv_text);
        tv_name = findViewById(R.id.tv_name);
        tv_winner = findViewById(R.id.tv_winner);
        tv_mmora = findViewById(R.id.tv_mmora);
        tv_cmora = findViewById(R.id.tv_cmora);
        btn_scissor = findViewById(R.id.btn_scissor);
        btn_stone = findViewById(R.id.btn_stone);
        btn_paper = findViewById(R.id.btn_paper);
        btn_mora = findViewById(R.id.btn_mora);
        //『猜拳』按鈕點擊監聽
        btn_mora.setOnClickListener(v -> {
            //判斷使用者是否輸入姓名
            if(ed_name.length()<1)
                tv_text.setText("請輸入玩家姓名");
            else{
                //顯示使用者姓名、出拳
                tv_name.setText(String.format("名字\n%s", ed_name.getText().toString()));
                if(btn_scissor.isChecked())
                    tv_mmora.setText("我方出拳\n剪刀");
                else if(btn_stone.isChecked())
                    tv_mmora.setText("我方出拳\n石頭");
                else
                    tv_mmora.setText("我方出拳\n布");
                //使用亂數產生電腦出拳
                int computer = (int)(Math.random()*3);
                if(computer==0)
                    tv_cmora.setText("電腦出拳\n剪刀");
                else if(computer==1)
                    tv_cmora.setText("電腦出拳\n石頭");
                else
                    tv_cmora.setText("電腦出拳\n布");
                //判斷勝負
                if((btn_scissor.isChecked() && computer==2) || (btn_stone.isChecked() && computer==0) ||
                        (btn_paper.isChecked() && computer==1)){
                    tv_winner.setText("勝利者\n" + ed_name.getText().toString());
                    tv_text.setText("恭喜你獲勝了！！！");
                }else if((btn_scissor.isChecked() && computer==1) || (btn_stone.isChecked() && computer==2) ||
                        (btn_paper.isChecked() && computer==0)){
                    tv_winner.setText("勝利者\n電腦");
                    tv_text.setText("可惜，電腦獲勝了！");
                }else{
                    tv_winner.setText("勝利者\n平手");
                    tv_text.setText("平局，請再試一次！");
                }
            }
        });
    }
}
