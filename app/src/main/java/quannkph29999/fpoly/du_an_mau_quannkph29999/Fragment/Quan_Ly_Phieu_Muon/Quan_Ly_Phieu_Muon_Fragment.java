package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Phieu_Muon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.PhieuMuonDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.SachDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThanhVienDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.MainActivity;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.PhieuMuon;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Quan_Ly_Phieu_Muon_Fragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<PhieuMuon> listpm;
    PhieuMuonDAO phieuMuonDAO;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    AdapterPhieuMuon adapterPhieuMuon;
    ArrayList<HashMap<String, Object>> listspinnertv;
    ArrayList<HashMap<String, Object>> listspinnertensach;


    public Quan_Ly_Phieu_Muon_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan__ly__phieu__muon_, container, false);
        recyclerView = view.findViewById(R.id.qlpm_recycview);
        realoandata();
        FloatingActionButton addphieumuon = view.findViewById(R.id.qlpm_btnfloatingthemphieumuon);
        addphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   ThemPhieuMuon();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        realoandata();
        super.onResume();
    }

    public ArrayList<HashMap<String,Object>> Spinnertentv(){
        thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> listthanhvien = thanhVienDAO.GetDSTV();
        listspinnertv = new ArrayList<>();
        for(ThanhVien thanhVien: listthanhvien){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("TenTV",thanhVien.getTentv());
            listspinnertv.add(hashMap);
        }
        return listspinnertv;
    }

    public ArrayList<HashMap<String,Object>> Spinnertensach(){
        sachDAO = new SachDAO(getContext());
        ArrayList<Sach> listsach = sachDAO.GetDSS();
        listspinnertensach = new ArrayList<>();
        for(Sach sach: listsach){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("TenS",sach.getTensach());
            listspinnertensach.add(hashMap);
        }
        return listspinnertensach;
    }
    public void realoandata(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        listpm = phieuMuonDAO.GetDSPM();
        adapterPhieuMuon = new AdapterPhieuMuon(listpm,phieuMuonDAO,getContext(),Spinnertentv(),Spinnertensach());
        recyclerView.setAdapter(adapterPhieuMuon);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void ThemPhieuMuon(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogpm_themphieumuon, null);
        Spinner spThanhvien = view.findViewById(R.id.dialogpm_themtentv);
        Spinner spSach = view.findViewById(R.id.dialogpm_themtensach);
        EditText edtGiasach = view.findViewById(R.id.dialogpm_themgiathue);
        EditText edtNgaythue = view.findViewById(R.id.dialogpm_themngaythue);
        CheckBox chkTrangthai = view.findViewById(R.id.dialogpm_themtrangthai);
        Button btnThemphieuMuon = view.findViewById(R.id.dialogpm_btnthempm);
        Button btnThoat = view.findViewById(R.id.dialogpm_btnthoatthempm);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        edtNgaythue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        edtNgaythue.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        SimpleAdapter adapterthemten = new SimpleAdapter(getContext(),listspinnertv,android.R.layout.simple_list_item_1,
                new String[]{"TenTV"},new int[]{android.R.id.text1});
        spThanhvien.setAdapter(adapterthemten);
        SimpleAdapter adapterthemsach = new SimpleAdapter(getContext(),listspinnertensach, android.R.layout.simple_list_item_1,
                new String[]{"TenS"},new int[]{android.R.id.text1});
        spSach.setAdapter(adapterthemsach);


        ArrayList<Sach> listsach = sachDAO.GetDSS();
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Sach sach = listsach.get(i);
                edtGiasach.setText(sach.getGiasach() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnThemphieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trangthai = "" ;
                if(chkTrangthai.isChecked() == true) {
                    trangthai = "Đã Trả Sách";
                }
                else if(chkTrangthai.isChecked() == false) {
                    trangthai = "Chưa Trả Sách";
                }
                String themgaythuesach = edtNgaythue.getText().toString();
                String giasach = edtGiasach.getText().toString();
                if(edtNgaythue.length() == 0){
                    edtNgaythue.requestFocus();
                    edtNgaythue.setError("Không bỏ trống ngày thuê");
                }

                else{
                    HashMap<String,Object> chontentv = (HashMap<String, Object>) spThanhvien.getSelectedItem();
                    HashMap<String,Object> chontensach = (HashMap<String, Object>) spSach.getSelectedItem();
                    String tentv = (String) chontentv.get("TenTV");
                    String tensach = (String) chontensach.get("TenS");
                    PhieuMuon themphieumuon = new PhieuMuon(themgaythuesach, trangthai,tentv,tensach,Integer.parseInt(giasach));
                    if(phieuMuonDAO.Thempm(themphieumuon) > 0){
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        realoandata();
                        alertDialog.dismiss();
                    }
                    else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }


}