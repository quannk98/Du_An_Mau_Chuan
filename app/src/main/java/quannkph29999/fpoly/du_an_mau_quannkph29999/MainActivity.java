package quannkph29999.fpoly.du_an_mau_quannkph29999;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DataBase.DBHelper;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Doanh_Thu.Doanh_Thu_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Doi_Mat_Khau.Doi_Mat_Khau_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Loai_Sach.Quan_Ly_Loai_Sach_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Phieu_Muon.Quan_Ly_Phieu_Muon_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Sach.Quan_Ly_Sach_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Thanh_Vien.Quan_Ly_Thanh_Vien_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Sach_Muon_Nhieu_Nhat.Sach_Muon_Nhieu_Nhat_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Them_Thanh_Vien.Them_Thanh_Vien_Fragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       replaceFragment(new Quan_Ly_Phieu_Muon_Fragment());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navi);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        DBHelper db = new DBHelper(getApplicationContext());
        db.getWritableDatabase();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.manage_note:
                setTitle("Quản Lý Phiếu Mượn");
                replaceFragment(new Quan_Ly_Phieu_Muon_Fragment());
                drawerLayout.close();
                break;
            case R.id.type_book:
                setTitle("Quản Lý Loại Sách");
                replaceFragment(new Quan_Ly_Loai_Sach_Fragment());
                drawerLayout.close();
                break;
            case R.id.manage_book:
                setTitle("Quản Lý Sách");
                replaceFragment(new Quan_Ly_Sach_Fragment());
                drawerLayout.close();
                break;
            case R.id.manage_user:
                setTitle("Quản Lý Thành Viên");
                replaceFragment(new Quan_Ly_Thanh_Vien_Fragment());
                drawerLayout.close();
                break;
            case R.id.bestbook:
                setTitle("Sách Được Mượn Nhiều Nhất");
                replaceFragment(new Sach_Muon_Nhieu_Nhat_Fragment());
                drawerLayout.close();
                break;
            case R.id.total:
                setTitle("Doanh Thu");
                replaceFragment(new Doanh_Thu_Fragment());
                drawerLayout.close();
                break;
            case R.id.add_user:
                setTitle("Thêm Thành Viên");
                replaceFragment(new Them_Thanh_Vien_Fragment());
                drawerLayout.close();
                break;
            case R.id.change_password:
                setTitle("Đổi Mật Khẩu");
                replaceFragment(new Doi_Mat_Khau_Fragment());
                drawerLayout.close();
                break;
            case R.id.log_out:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Bạn có muốn đăng xuất hay không");
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        MainActivity.super.onBackPressed();
                    }
                });
                alertDialog.show();

                break;


        }

        return true;
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()) {
            drawerLayout.close();
        } else {
            super.onBackPressed();
        }
    }
}