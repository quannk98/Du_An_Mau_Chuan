package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Loai_Sach;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.LoaiSachDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.LoaiSach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;

public class AdapterLoaiSach extends RecyclerView.Adapter<AdapterLoaiSach.ViewHolder> {

    ArrayList<LoaiSach> listls;
    Context context;
    LoaiSachDAO loaiSachDAO;

    public AdapterLoaiSach(ArrayList<LoaiSach> listls, Context context, LoaiSachDAO loaiSachDAO) {
        this.listls = listls;
        this.context = context;
        this.loaiSachDAO = loaiSachDAO;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maloaisach, tenloaisach;
        ImageButton sualoaisach, xoaloaisach;
        LinearLayout llloaisach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maloaisach = itemView.findViewById(R.id.itemls_maloaisach);
            tenloaisach = itemView.findViewById(R.id.itemls_tenloaisach);
            sualoaisach = itemView.findViewById(R.id.itemls_suals);
            xoaloaisach = itemView.findViewById(R.id.itemls_xoals);
            llloaisach = itemView.findViewById(R.id.itemls);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.maloaisach.setText(String.valueOf(listls.get(position).getMals()));
        holder.tenloaisach.setText(listls.get(position).getTenls());
        holder.llloaisach.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Showdataloaisach(context, listls.get(position));
                return false;
            }
        });
        holder.sualoaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              SuaLoaiSach(context,listls.get(position));
            }
        });
        holder.xoaloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maloaisach = listls.get(position).getMals();
                if (loaiSachDAO.XoaLS(maloaisach) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    listls.clear();
                    loaiSachDAO = new LoaiSachDAO(context);
                    listls = loaiSachDAO.GetDSLS();
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listls.size();
    }

    public void Showdataloaisach(Context context, LoaiSach loaiSach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Loại Sách");
        builder.setMessage("Mã Loại Sách:" + loaiSach.getMals() + "\nTên Loại Sách:" + loaiSach.getTenls());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }

    public void SuaLoaiSach(Context context, LoaiSach loaiSach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_loaisach, null, false);
        EditText suatenls = view.findViewById(R.id.dialogls_suatenloaisach);
        Button btsuals = view.findViewById(R.id.dialogls_btnsualoais);
        Button btthoatls = view.findViewById(R.id.dialogls_btnthoatsualoais);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        suatenls.setText(loaiSach.getTenls());
        btsuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suaten = suatenls.getText().toString();
                int mals = loaiSach.getMals();
                if(suatenls.length() == 0){
                    suatenls.requestFocus();
                    suatenls.setError("Không được để trống tên loại sách");
                }
                else{
                   LoaiSach loaiSachsua = new LoaiSach(mals,suaten);
                   if(loaiSachDAO.SuaLS(loaiSachsua) > 0){
                       Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                       listls.clear();
                       loaiSachDAO = new LoaiSachDAO(context);
                       listls = loaiSachDAO.GetDSLS();
                       notifyDataSetChanged();
                       alertDialog.dismiss();
                   }
                   else {
                       Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
        btthoatls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


    }


}
