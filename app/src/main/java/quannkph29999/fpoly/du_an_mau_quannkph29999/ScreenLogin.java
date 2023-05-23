package quannkph29999.fpoly.du_an_mau_quannkph29999;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ScreenLogin extends AppCompatActivity {
         Button dangnhap,thoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
         dangnhap = findViewById(R.id.slogin_btndangnhap);
         thoat = findViewById(R.id.slogin_btnthoat);
         dangnhap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ScreenLogin.this,MainActivity.class);
                 startActivity(intent);
             }
         });
    }
}