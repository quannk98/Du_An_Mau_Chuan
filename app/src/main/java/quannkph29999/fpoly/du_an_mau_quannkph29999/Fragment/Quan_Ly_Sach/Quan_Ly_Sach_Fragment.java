package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Sach;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.LoaiSachDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.SachDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.LoaiSach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Quan_Ly_Sach_Fragment extends Fragment {
    RecyclerView recyclerViews;
    ArrayList<Sach> lists;
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    AdapterSach adapterSach;
    ArrayList<HashMap<String, Object>> listspinnerls;


    public Quan_Ly_Sach_Fragment() {

    }
    public static Quan_Ly_Sach_Fragment newInstance(){
           Quan_Ly_Sach_Fragment fragment = new Quan_Ly_Sach_Fragment();
           return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan__ly__sach_, container, false);
        recyclerViews = view.findViewById(R.id.qls_recycview);
        FloatingActionButton floatingthemsach = view.findViewById(R.id.qls_btnfloatingsach);
        sachDAO = new SachDAO(getContext());
        realoaddatasach();
        floatingthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemSach();
            }
        });
        return view;
    }

    public ArrayList<HashMap<String, Object>> GetdataSpinnerloaisach() {
        loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> listls = loaiSachDAO.GetDSLS();
        listspinnerls = new ArrayList<>();
        for (LoaiSach loaiSach : listls) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("TenLS", loaiSach.getTenls());
            listspinnerls.add(hashMap);
        }
        return listspinnerls;
    }

    public void realoaddatasach() {

        lists = sachDAO.GetDSS();
        adapterSach = new AdapterSach(lists, getContext(), sachDAO, GetdataSpinnerloaisach());
        recyclerViews.setAdapter(adapterSach);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void ThemSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_them_sach, null);
        EditText themtensach = view.findViewById(R.id.dialogs_tensach);
        EditText themgiasach = view.findViewById(R.id.dialogs_giathues);
        Spinner themloaisach = view.findViewById(R.id.dialogs_loaisach);
        Button btnthemsach = view.findViewById(R.id.dialogs_btnthems);
        Button btnthemthoatsach = view.findViewById(R.id.dialogs_btnthoats);
        builder.setView(view);
        SimpleAdapter adapter = new SimpleAdapter(getContext(), GetdataSpinnerloaisach(),
                android.R.layout.simple_list_item_1, new String[]{"TenLS"}, new int[]{android.R.id.text1});
        themloaisach.setAdapter(adapter);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        if(adapter.isEmpty()){
            Toast.makeText(getContext(), "Thêm Loại Sách Để Thêm Tên Sách", Toast.LENGTH_SHORT).show();
        }
        else {
            btnthemsach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String themten = themtensach.getText().toString();
                    int themgia = Integer.parseInt(themgiasach.getText().toString());
                    if (themtensach.length() == 0) {
                        themtensach.requestFocus();
                        themtensach.setError("Không bỏ trống tên sách");
                    } else if (themgiasach.length() == 0) {
                        themgiasach.requestFocus();
                        themgiasach.setError("Không bỏ trống giá sách");
                    } else {
                        HashMap<String, Object> loaisachspinner = (HashMap<String, Object>) themloaisach.getSelectedItem();
                        String tenls = (String) loaisachspinner.get("TenLS");
                        Sach themsach = new Sach(themten, themgia, tenls);
                        if (sachDAO.ThemSach(themsach) > 0) {
                            Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            realoaddatasach();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        }


        btnthemthoatsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 alertDialog.dismiss();
            }
        });


    }
}