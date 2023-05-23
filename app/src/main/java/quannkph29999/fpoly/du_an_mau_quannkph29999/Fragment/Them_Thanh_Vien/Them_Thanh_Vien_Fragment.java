package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Them_Thanh_Vien;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.time.Instant;
import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThanhVienDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Thanh_Vien.AdapterThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Thanh_Vien.Quan_Ly_Thanh_Vien_Fragment;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Them_Thanh_Vien_Fragment extends Fragment {
  ImageButton showmk,shownlmk;
  boolean checkmk = true;
  EditText tendn,tentv,cccd,mk,nlmk;
  Button btndangky,btnshowds;

    public Them_Thanh_Vien_Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view =  inflater.inflate(R.layout.fragment_them__thanh__vien_, container, false);
             showmk = view.findViewById(R.id.showmkdk);
             shownlmk = view.findViewById(R.id.shownhaplaimkdk);
             tendn = view.findViewById(R.id.themtv_tendangnhapdk);
             tentv = view.findViewById(R.id.themtv_hotendk);
             cccd = view.findViewById(R.id.themtv_cccddk);
             mk = view.findViewById(R.id.themtv_matkhaudk);
             nlmk = view.findViewById(R.id.themtv_nhaplaimkdk);
             btndangky = view.findViewById(R.id.themtv_btndangky);
             btnshowds = view.findViewById(R.id.themtv_btnshowds);
             return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkmk == true){
                    showmk.setImageResource(R.drawable.hidepassword);
                    checkmk = false;
                }
                else if(checkmk == false ){
                    showmk.setImageResource(R.drawable.showpassword);
                    checkmk = true;
                }

            }
        });
        shownlmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkmk == true){
                    shownlmk.setImageResource(R.drawable.hidepassword);
                    checkmk = false;
                }
                else if(checkmk == false){
                    shownlmk.setImageResource(R.drawable.showpassword);
                    checkmk = true;
                }
            }
        });

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnshowds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

//    public void Themtv(){
//        if(tendn.length() == 0){
//            tendn.requestFocus();
//            tendn.setError("Không được để trống phần tên đăng nhập");
//        }
//        else if(tentv.length() == 0){
//            tentv.requestFocus();
//            tentv.setError("Không được để trống tên thành viên");
//        }
//        else if(cccd.length() == 0){
//            cccd.requestFocus();
//            cccd.setError("Không để trống căn cước công dân");
//        }
//        else if(mk.length() == 0){
//            mk.requestFocus();
//            mk.setError("Không để trống mật khẩu");
//        }
//        else if(nlmk.length() == 0){
//            nlmk.requestFocus();
//            nlmk.setError("Không được để trống");
//        }
//        else if(!mk.getText().toString().equals(nlmk.getText().toString())){
//            nlmk.requestFocus();
//            nlmk.setError("Mật khẩu và nhập lại mật khẩu không trùng nhau");
//        }
//        else {
//            thanhVienDAO = new ThanhVienDAO(getContext());
//            String tenthanhvien = tentv.getText().toString();
//            String cccdthanhvien = cccd.getText().toString();
//            String tendangnhap = tendn.getText().toString();
//            String matkhauthanhvien = mk.getText().toString();
//            ThanhVien themthanhvien = new ThanhVien(tenthanhvien,cccdthanhvien,tendangnhap,matkhauthanhvien);
//            if(thanhVienDAO.ThemTV(themthanhvien) > 0){
//                Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                listtv = thanhVienDAO.GetDSTV();
//            }
//            else {
//                Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}