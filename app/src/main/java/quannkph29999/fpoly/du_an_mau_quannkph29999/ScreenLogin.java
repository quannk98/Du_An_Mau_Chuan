package quannkph29999.fpoly.du_an_mau_quannkph29999;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThuThuDAO;

public class ScreenLogin extends AppCompatActivity {
    Button dangnhap, thoat;
    EditText tendangnhap, matkhau;
    ImageButton showpass;
    CheckBox luutaikhoan;
    ThuThuDAO thuThuDAO;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;


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
        initPreferences();
        String savedDataten = sharedPreferences.getString("DATATEN", "");
        String savedDatamk = sharedPreferences.getString("DATAMK", "");
        tendangnhap.setText(savedDataten);
        matkhau.setText(savedDatamk);

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
                String user = tendangnhap.getText().toString();
                String pass = matkhau.getText().toString();
                thuThuDAO = new ThuThuDAO(ScreenLogin.this);
                if(tendangnhap.length() == 0){
                    tendangnhap.requestFocus();
                    tendangnhap.setError("Không bỏ trống tên");
                }
                else if(matkhau.length() == 0){
                    matkhau.requestFocus();
                    matkhau.setError("Không bỏ trống mật khẩu");
                }
                else if (thuThuDAO.checkLogin(user, pass)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("MaTT", user);
                    editor.commit();
                    if(luutaikhoan.isChecked() == true){
                        String dataten = tendangnhap.getText().toString();
                        String datamk = matkhau.getText().toString();
                        editor.putString("DATATEN",dataten);
                        editor.putString("DATAMK",datamk);
                        editor.commit();
                    }
                    else {
                        tendangnhap.setText("");
                        matkhau.setText("");
                        editor.commit();
                    }
                    Intent intent = new Intent(ScreenLogin.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ScreenLogin.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ScreenLogin.this, "Tên Hoặc Mật Khẩu Không Đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }



}