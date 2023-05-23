package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Quan_Ly_Sach;

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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.SachDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.Sach;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.ViewHolder> {
    ArrayList<Sach> listsach;
    Context context;
    SachDAO sachDAO;
    ArrayList<HashMap<String, Object>> listspinnerls;

    public AdapterSach(ArrayList<Sach> listsach, Context context, SachDAO sachDAO, ArrayList<HashMap<String, Object>> listspinnerls) {
        this.listsach = listsach;
        this.context = context;
        this.sachDAO = sachDAO;
        this.listspinnerls = listspinnerls;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView masach, tensach, giathue, loaisach;
        ImageButton suasach, xoasach;
        LinearLayout llsach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            masach = itemView.findViewById(R.id.itemsach_masach);
            tensach = itemView.findViewById(R.id.itemsach_tensach);
            giathue = itemView.findViewById(R.id.itemsach_giasach);
            loaisach = itemView.findViewById(R.id.itemsach_loaisach);
            suasach = itemView.findViewById(R.id.itemsach_suas);
            xoasach = itemView.findViewById(R.id.itemsach_xoas);
            llsach = itemView.findViewById(R.id.itemsach);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.masach.setText(String.valueOf(listsach.get(position).getMasach()));
        holder.tensach.setText(listsach.get(position).getTensach());
        holder.giathue.setText(String.valueOf(listsach.get(position).getGiasach()));
        holder.loaisach.setText(listsach.get(position).getTenls());
        holder.llsach.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ShowdataSach(context, listsach.get(position));
                return false;
            }
        });
        holder.suasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           SuaSach(context,listsach.get(position));
            }
        });
        holder.xoasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             int masach = listsach.get(position).getMasach();
              if(sachDAO.XoaSach(masach) > 0){
                  Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                  listsach.clear();
                  sachDAO = new SachDAO(context);
                  listsach = sachDAO.GetDSS();
                  notifyDataSetChanged();
              }
              else {
                  Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
              }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listsach.size();
    }

    public void ShowdataSach(Context context, Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Sách");
        builder.setMessage("Mã Sách:" + sach.getMasach() + "\nTên Sách:" + sach.getTensach() + "\nGiá Thuê:" + sach.getGiasach() + "\nLoại Sách:" + sach.getTenls());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }

    public void SuaSach(Context context, Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_sach, null);
        EditText suatensach = view.findViewById(R.id.dialogs_suatensach);
        EditText suagiasach = view.findViewById(R.id.dialogs_suagiathues);
        Spinner sualoaisach = view.findViewById(R.id.dialogs_sualoaisach);
        Button btnsuasach = view.findViewById(R.id.dialogs_btnsuas);
        Button btnthoatsach = view.findViewById(R.id.dialogs_btnsuathoats);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        SimpleAdapter adapter = new SimpleAdapter(context, listspinnerls, android.R.layout.simple_list_item_1,
                new String[]{"TenLS"}, new int[]{android.R.id.text1});
        sualoaisach.setAdapter(adapter);
        int index = 0;
        int vitri = -1;
        for (HashMap<String, Object> item : listspinnerls) {
            if ( item.get("TenLS") == sach.getTenls()) {
                vitri = index;
            }
            index++;
        }
        suatensach.setText(sach.getTensach());
        suagiasach.setText(String.valueOf(sach.getGiasach()));
        sualoaisach.setSelection(vitri);
        btnsuasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suaten = suatensach.getText().toString();
                int suagia = Integer.parseInt(suagiasach.getText().toString());
                int masach = sach.getMasach();
                if (suatensach.length() == 0) {
                    suatensach.requestFocus();
                    suatensach.setError("Không bỏ trống tên sách");
                } else if (suagiasach.length() == 0) {
                    suagiasach.requestFocus();
                    suagiasach.setError("Không bỏ trống giá sách");
                } else {
                    HashMap<String, Object> loaisachspinner = (HashMap<String, Object>) sualoaisach.getSelectedItem();
                    String tenls = (String) loaisachspinner.get("TenLS");
                    Sach sach1 = new Sach(masach,suaten,suagia,tenls);
                    if(sachDAO.SuaSach(sach1) > 0){
                        Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                          listsach.clear();
                          sachDAO = new SachDAO(context);
                          listsach = sachDAO.GetDSS();
                          notifyDataSetChanged();
                          alertDialog.dismiss();
                    }
                    else {
                        Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnthoatsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            alertDialog.dismiss();
            }
        });

    }



}
