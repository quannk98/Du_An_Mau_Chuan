package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Loai_Sach;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.LoaiSachDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.LoaiSach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Quan_Ly_Loai_Sach_Fragment extends Fragment {
    RecyclerView recyclerViewls;
    ArrayList<LoaiSach> listloaisach;
    LoaiSachDAO loaiSachDAO;
    AdapterLoaiSach adapterLoaiSach;


    public Quan_Ly_Loai_Sach_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan__ly__loai__sach_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewls = view.findViewById(R.id.qlls_recycciew);
        FloatingActionButton floatingthemls = view.findViewById(R.id.qls_btnfloatingthemloaisach);
        realoandata();
        boolean checktvls = getArguments().getBoolean("tvls");
        if(checktvls == false){
            floatingthemls.setVisibility(View.INVISIBLE);
        }
        else {
            floatingthemls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Themls();
                }
            });
        }

    }

    public void realoandata() {
        boolean checktvls = getArguments().getBoolean("tvls");
        loaiSachDAO = new LoaiSachDAO(getContext());
        listloaisach = loaiSachDAO.GetDSLS();
        adapterLoaiSach = new AdapterLoaiSach(listloaisach, getContext(), loaiSachDAO,checktvls);
        recyclerViewls.setAdapter(adapterLoaiSach);
    }

    @Override
    public void onResume() {
        super.onResume();
        realoandata();
    }

    public void Themls() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loaisach, null, false);
        EditText themtenls = view.findViewById(R.id.dialogls_themtenloaisach);
        Button btthemls = view.findViewById(R.id.dialogls_btnthemloais);
        Button btthemthoatls = view.findViewById(R.id.dialogls_btnthoatthemloais);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btthemls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = themtenls.getText().toString();
                LoaiSach loaiSachthem = new LoaiSach(themten);
                if (themtenls.length() == 0) {
                    themtenls.requestFocus();
                    themtenls.setError("Không được để trống tên loại sách");
                } else {

                    if (loaiSachDAO.ThemLS(loaiSachthem) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        realoandata();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btthemthoatls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}