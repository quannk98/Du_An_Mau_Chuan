package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Doanh_Thu;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThongKeDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Doanh_Thu_Fragment extends Fragment {
     Button btntuNgay,btndenNgay,btntongDoanhThu;
     TextView tvTungay,tvDenngay,tvTongdt;
     int mYear,mMonth,mDay;
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Doanh_Thu_Fragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh__thu_, container, false);
        btntuNgay = view.findViewById(R.id.dt_btntungay);
        btndenNgay = view.findViewById(R.id.dt_btndenngay);
        btntongDoanhThu = view.findViewById(R.id.dt_btndoanhthu);
        tvTungay = view.findViewById(R.id.dt_tungay);
        tvDenngay = view.findViewById(R.id.dt_denngay);
        tvTongdt = view.findViewById(R.id.dt_doanhthu);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        final int mYear = calendar.get(Calendar.YEAR);
        final int mMonth = calendar.get(Calendar.MONTH);
        final int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        btntuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tvTungay.setText(date);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btndenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tvDenngay.setText(date);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btntongDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dt_tuNgay = tvTungay.getText().toString();
                String dt_denNgay = tvDenngay.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                tvTongdt.setText(thongKeDAO.getDoanhThu(dt_tuNgay,dt_denNgay)+" VND");
            }
        });
    }
}