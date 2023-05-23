package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Thanh_Vien;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThanhVienDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.LoaiSach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Quan_Ly_Thanh_Vien_Fragment extends Fragment {
      AdapterThanhVien adapterThanhVien;
      ThanhVienDAO thanhVienDAO;
      RecyclerView recyclerViewtv;
      ArrayList<ThanhVien> listtv ;




    public Quan_Ly_Thanh_Vien_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fragment_quan__ly__thanh__vien_, container, false);
           return view ;
    }


    @Override
    public void onResume() {
        super.onResume();
        realoandata();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewtv = view.findViewById(R.id.qltv_recycquanly);
        FloatingActionButton themtv = view.findViewById(R.id.qltv_btnfloatingtv);
        realoandata();
        themtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Themtv();
            }
        });


    }
    public void realoandata(){
        thanhVienDAO = new ThanhVienDAO(getContext());
        listtv = thanhVienDAO.GetDSTV();
        adapterThanhVien = new AdapterThanhVien(listtv,thanhVienDAO,getContext());
        recyclerViewtv.setAdapter(adapterThanhVien);
    }
    public void Themtv(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogtv_themtv, null, false);
        EditText themtentv = view.findViewById(R.id.dialogtv_themtentv);
        EditText themcccdtv = view.findViewById(R.id.dialogtv_themcccdtv);
        Button btthemtv = view.findViewById(R.id.dialogtv_btnthemtv);
        Button btthemthoattv = view.findViewById(R.id.dialogtv_btnthoatthemtv);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btthemtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = themtentv.getText().toString();
                String themcccd = themcccdtv.getText().toString();
                if (themtentv.length() == 0) {
                    themtentv.requestFocus();
                    themtentv.setError("Không được để trống tên thành viên");
                }
                else if(themcccdtv.length() == 0){
                    themcccdtv.requestFocus();
                    themcccdtv.setError("Không được để trống căn cước công dân");
                }

                else {
                   ThanhVien themthanhvien = new ThanhVien(themten,themcccd);
                    if (thanhVienDAO.ThemTV(themthanhvien) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        realoandata();

                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btthemthoattv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

}