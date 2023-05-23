package quannkph29999.fpoly.du_an_mau_quannkph29999;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ScreenLogin extends AppCompatActivity {
    Button dangnhap, thoat;
    EditText tendangnhap, matkhau;
    ImageButton showpass;
    CheckBox luutaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
        dangnhap = findViewById(R.id.slogin_btndangnhap);
        thoat = findViewById(R.id.slogin_btnthoat);
        tendangnhap = findViewById(R.id.slogin_tendangnhap);
        matkhau = findViewById(R.id.slogin_mkdangnhap);
        showpass = findViewById(R.id.slogin_showmk);
        luutaikhoan = findViewById(R.id.slogin_luudp);
        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matkhau.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    matkhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showpass.setImageResource(R.drawable.hidepassword);
                } else {
                    matkhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showpass.setImageResource(R.drawable.showpassword);
                }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ScreenLogin.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ScreenLogin.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();

            }
        });

    }




}