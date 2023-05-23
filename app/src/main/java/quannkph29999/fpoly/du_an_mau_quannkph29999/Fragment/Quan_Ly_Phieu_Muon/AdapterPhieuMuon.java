package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Phieu_Muon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.PhieuMuonDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThanhVienDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.PhieuMuon;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;

public class AdapterPhieuMuon extends RecyclerView.Adapter<AdapterPhieuMuon.ViewHolder> {
    ArrayList<PhieuMuon> listpm;
    PhieuMuonDAO phieuMuonDAO;
    Context context;
    ArrayList<HashMap<String, Object>> listspinnertv;
    ArrayList<HashMap<String, Object>> listspinnertensach;

    public AdapterPhieuMuon(ArrayList<PhieuMuon> listpm, PhieuMuonDAO phieuMuonDAO, Context context,
                            ArrayList<HashMap<String, Object>> listspinnertv,
                            ArrayList<HashMap<String, Object>> listspinnertensach) {
        this.listpm = listpm;
        this.phieuMuonDAO = phieuMuonDAO;
        this.context = context;
        this.listspinnertv = listspinnertv;
        this.listspinnertensach = listspinnertensach;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText mapm, tentv, tensach, tienthue, ngaythue, trangthai;
        ImageButton suapm, xoapm;
        LinearLayout item_phieumuon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapm = itemView.findViewById(R.id.itemphieumuon_maphieumuon);
            tentv = itemView.findViewById(R.id.itemphieumuon_tenthanhvien);
            tensach = itemView.findViewById(R.id.itemphieumuon_tensach);
            tienthue = itemView.findViewById(R.id.itemphieumuon_tienthue);
            ngaythue = itemView.findViewById(R.id.itemphieumuon_ngaythue);
            trangthai = itemView.findViewById(R.id.itemphieumuon_trangthai);
            suapm = itemView.findViewById(R.id.itemphieumuon_sua);
            xoapm = itemView.findViewById(R.id.itemphieumuon_xoa);
            item_phieumuon = itemView.findViewById(R.id.itemphieumuon);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mapm.setText(String.valueOf(listpm.get(position).getMapm()));
        holder.tentv.setText(listpm.get(position).getTentv());
        holder.tensach.setText(listpm.get(position).getTens());
        holder.tienthue.setText(String.valueOf(listpm.get(position).getGiathue()));
        holder.trangthai.setText(listpm.get(position).getTrangthai());
        holder.ngaythue.setText(listpm.get(position).getNgaythue());
        holder.item_phieumuon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Showdata(context, listpm.get(position));
                return false;
            }
        });
        holder.suapm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suapm(context, listpm.get(position));
            }
        });
        holder.xoapm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xoamapm = listpm.get(position).getMapm();
                if (phieuMuonDAO.Xoapm(xoamapm) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    listpm.clear();
                    phieuMuonDAO = new PhieuMuonDAO(context);
                    listpm = phieuMuonDAO.GetDSPM();
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return listpm.size();
    }

    public void Showdata(Context context, PhieuMuon phieuMuon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Phiếu Mượn");
        builder.setMessage("Tên Thành Viên:" + phieuMuon.getTentv() + "\nTên Sách:" + phieuMuon.getTens()
                + "\nGiá Sách:" + phieuMuon.getGiathue() + "\nNgày Thuê:" + phieuMuon.getNgaythue() + "\nTrang Thái:" + phieuMuon.getTrangthai());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }

    public void Suapm(Context context, PhieuMuon phieuMuon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogpm_suaphieumuon, null);
        Spinner suatentv = view.findViewById(R.id.dialogpm_suatentv);
        Spinner suatensach = view.findViewById(R.id.dialogpm_suatensach);
        EditText suagiathue = view.findViewById(R.id.dialogpm_suagiathue);
        EditText suangaythue = view.findViewById(R.id.dialogpm_suangaythue);
        CheckBox suatrangthai = view.findViewById(R.id.dialogpm_suatrangthai);
        Button suaphieumuon = view.findViewById(R.id.dialogpm_btnsuapm);
        Button thoatphieumuon = view.findViewById(R.id.dialogpm_btnthoatsuapm);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        suangaythue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        suangaythue.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        SimpleAdapter adaptersuaten = new SimpleAdapter(context, listspinnertv, android.R.layout.simple_list_item_1,
                new String[]{"TenTV"}, new int[]{android.R.id.text1});
        suatentv.setAdapter(adaptersuaten);
        SimpleAdapter adaptersuasach = new SimpleAdapter(context, listspinnertensach, android.R.layout.simple_list_item_1,
                new String[]{"TenS"}, new int[]{android.R.id.text1});
        suatensach.setAdapter(adaptersuasach);


        suagiathue.setText(String.valueOf(phieuMuon.getGiathue()));
        suangaythue.setText(phieuMuon.getNgaythue());

        suaphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suangaythuesach = suangaythue.getText().toString();

                if (suangaythue.length() == 0) {
                    suangaythue.requestFocus();
                    suangaythue.setError("Không bỏ trống ngày thuê");
                } else {
                    HashMap<String, Object> chontentv = (HashMap<String, Object>) suatentv.getSelectedItem();
                    HashMap<String, Object> chontensach = (HashMap<String, Object>) suatensach.getSelectedItem();
                    String tentv = (String) chontentv.get("TenTV");
                    String tensach = (String) chontensach.get("TenS");
                    int maphieumuon = phieuMuon.getMapm();
                    String trangthaisua = "";
                    if (suatrangthai.isChecked() == true) {
                        trangthaisua = "Đã Trả Sách";
                    } else if (suatrangthai.isChecked() == false) {
                        trangthaisua = "Chưa Trả Sách";
                    }
                    PhieuMuon suaphieumuon = new PhieuMuon(maphieumuon, suangaythuesach, trangthaisua, tentv, tensach, 2000);
                    if (phieuMuonDAO.Suapm(suaphieumuon) > 0) {
                        Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        listpm.clear();
                        phieuMuonDAO = new PhieuMuonDAO(context);
                        listpm = phieuMuonDAO.GetDSPM();
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        thoatphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }


}
