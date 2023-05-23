package quannkph29999.fpoly.du_an_mau_quannkph29999;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class ScreenWattingLogin extends AppCompatActivity {
    ProgressBar pb;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_watting_login);
        pb = findViewById(R.id.progress);
        thanhcho();
    }
    public void thanhcho(){
        pb = findViewById(R.id.progress);
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if(counter == 40){
                    t.cancel();
                    startActivity(new Intent(ScreenWattingLogin.this,ScreenLogin.class));
                }
            }
        };
        t.schedule(tt,0,100);

    }
}