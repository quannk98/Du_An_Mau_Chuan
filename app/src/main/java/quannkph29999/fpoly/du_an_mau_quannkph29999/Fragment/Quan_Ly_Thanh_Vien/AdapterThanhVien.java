package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Thanh_Vien;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThanhVienDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.ThanhVien;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;

public class AdapterThanhVien extends RecyclerView.Adapter<AdapterThanhVien.ViewHolder> {
    ArrayList<ThanhVien> listtv;
    ThanhVienDAO thanhVienDAO;
    Context context;

    public AdapterThanhVien(ArrayList<ThanhVien> listtv, ThanhVienDAO thanhVienDAO, Context context) {
        this.listtv = listtv;
        this.thanhVienDAO = thanhVienDAO;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matv, tentv, cccd;
        ImageButton suatv, xoatv;
        RelativeLayout lltv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matv = itemView.findViewById(R.id.itemtv_matv);
            tentv = itemView.findViewById(R.id.itemtv_tentv);
            cccd = itemView.findViewById(R.id.itemtv_cccdtv);
            suatv = itemView.findViewById(R.id.itemtv_suatv);
            xoatv = itemView.findViewById(R.id.itemtv_xoatv);
            lltv = itemView.findViewById(R.id.itemtv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thanhvien, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.matv.setText(String.valueOf(listtv.get(position).getMatv()));
        holder.tentv.setText(listtv.get(position).getTentv());
        holder.cccd.setText(listtv.get(position).getCccd());
        holder.lltv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Showdatathanhvien(context, listtv.get(position));
                return false;
            }
        });
        holder.suatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaTV(context, listtv.get(position));
            }
        });
        holder.xoatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int matv = listtv.get(position).getMatv();
                if (thanhVienDAO.XoaTv(matv) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    listtv.clear();
                    thanhVienDAO = new ThanhVienDAO(context);
                    listtv = thanhVienDAO.GetDSTV();
                    notifyDataSetChanged();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listtv.size();
    }

    public void Showdatathanhvien(Context context, ThanhVien thanhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Thành Viên");
        builder.setMessage("Mã Thành Viên:" + thanhVien.getMatv() + "\nTên Thành Viên:" + thanhVien.getTentv() + "\nCCCD:" + thanhVien.getCccd());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }

    public void SuaTV(Context context, ThanhVien thanhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogtv_suatv, null, false);
        EditText suatentv = view.findViewById(R.id.dialogtv_suatentv);
        EditText suacccdtv = view.findViewById(R.id.dialogtv_suacccdtv);
        Button btnsuatv = view.findViewById(R.id.dialogtv_btnsuatv);
        Button btnthoattv = view.findViewById(R.id.dialogtv_btnthoatsuatv);
        builder.setView(view);
        suatentv.setText(thanhVien.getTentv());
        suacccdtv.setText(thanhVien.getCccd());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnsuatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suaten = suatentv.getText().toString();
                String suacccd = suacccdtv.getText().toString();
                int matv = thanhVien.getMatv();
                if (suatentv.length() == 0) {
                    suatentv.requestFocus();
                    suatentv.setError("Không được để trống tên thành viên");
                } else if (suacccdtv.length() == 0) {
                    suacccdtv.requestFocus();
                    suacccdtv.setError("Không được để trống CCCD");
                } else {
                    ThanhVien thanhViensua = new ThanhVien(matv,suaten, suacccd);
                    if (thanhVienDAO.SuaTv(thanhViensua) > 0) {
                        Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        listtv.clear();
                        thanhVienDAO = new ThanhVienDAO(context);
                        listtv = thanhVienDAO.GetDSTV();
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnthoattv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


}
