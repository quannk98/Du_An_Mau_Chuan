package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Doi_Mat_Khau;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThanhVienDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThuThuDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThuThu;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Doi_Mat_Khau_Fragment extends Fragment {
    EditText mkcu, mkmoi, nlmkmoi;
    Button luumk, huy;
    ImageButton showmkcu, showmkm, shownlmk;
    boolean checkdshowmk = true;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    ThuThuDAO thuThuDAO;
    ThanhVienDAO thanhVienDAO;


    public Doi_Mat_Khau_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi__mat__khau_, container, false);
        showmkcu = view.findViewById(R.id.dmk_showmkcu);
        showmkm = view.findViewById(R.id.dmk_showmkmoi);
        shownlmk = view.findViewById(R.id.dmk_shownlmkm);
        mkcu = view.findViewById(R.id.dmk_matkhaucu);
        mkmoi = view.findViewById(R.id.dmk_mkmoi);
        nlmkmoi = view.findViewById(R.id.dmk_nlmkmoi);
        luumk = view.findViewById(R.id.dmk_btnluu);
        huy = view.findViewById(R.id.dmk_btnhuy);
        initPreferences();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showmkcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkdshowmk == true) {
                    showmkcu.setImageResource(R.drawable.hidepassword);
                    checkdshowmk = false;
                } else if (checkdshowmk == false) {
                    showmkcu.setImageResource(R.drawable.showpassword);
                    checkdshowmk = true;
                }
            }
        });
        showmkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkdshowmk == true) {
                    showmkm.setImageResource(R.drawable.hidepassword);
                    checkdshowmk = false;
                } else if (checkdshowmk == false) {
                    showmkm.setImageResource(R.drawable.showpassword);
                    checkdshowmk = true;
                }
            }
        });
        shownlmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkdshowmk == true) {
                    shownlmk.setImageResource(R.drawable.hidepassword);
                    checkdshowmk = false;
                } else if (checkdshowmk == false) {
                    shownlmk.setImageResource(R.drawable.showpassword);
                    checkdshowmk = true;
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mkcu.setText("");
                mkmoi.setText("");
                nlmkmoi.setText("");
            }
        });
        luumk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuThuDAO = new ThuThuDAO(getContext());
                thanhVienDAO = new ThanhVienDAO(getContext());
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("DATA", MODE_PRIVATE);
                String user = sharedPreferences.getString("DATATEN", "");
                String passold = sharedPreferences.getString("DATAMK", "");
                String passnew = mkmoi.getText().toString();
                String rePassnew = nlmkmoi.getText().toString();
                if (mkcu.length() == 0 || mkmoi.length() == 0 || nlmkmoi.length() == 0) {
                    Toast.makeText(getContext(), "Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                } else if (!passold.equals(mkcu.getText().toString())) {
                    Toast.makeText(getContext(), "Mật Khẩu Hoặc CCCD Cũ Không Đúng", Toast.LENGTH_SHORT).show();
                } else if (!passnew.equals(rePassnew)) {
                    Toast.makeText(getContext(), "Mật Khẩu Hoặc CCCD Mới Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                } else {
                    boolean taikhoandmk = getArguments().getBoolean("dmktt");
                    ThuThu suapasstt = new ThuThu(user, passnew);
                    if (taikhoandmk == true) {
                        if (thuThuDAO.SuaPassTT(suapasstt) > 0) {
                            Toast.makeText(getContext(), "Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                            mkcu.setText("");
                            mkmoi.setText("");
                            nlmkmoi.setText("");
                        } else {
                            Toast.makeText(getContext(), "Đổi Mật Khẩu(CCCD) Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ThanhVien suapsstv = new ThanhVien(user, passnew);
                        if (thanhVienDAO.SuaTv(suapsstv) > 0) {
                            Toast.makeText(getContext(), "Đổi CCCD Thành Công", Toast.LENGTH_SHORT).show();
                            mkcu.setText("");
                            mkmoi.setText("");
                            nlmkmoi.setText("");
                        } else {
                            Toast.makeText(getContext(), "Đổi Mật Khẩu(CCCD) Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            }


        });
    }

    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
    }
}