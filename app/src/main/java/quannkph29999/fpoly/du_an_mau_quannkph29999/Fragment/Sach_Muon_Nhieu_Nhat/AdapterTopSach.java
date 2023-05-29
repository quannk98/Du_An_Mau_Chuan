package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Sach_Muon_Nhieu_Nhat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.TopBook;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;

public class AdapterTopSach extends RecyclerView.Adapter<AdapterTopSach.ViewHolder> {
    Context context;
    ArrayList<TopBook> listtop;

    public AdapterTopSach(Context context, ArrayList<TopBook> listtop) {
        this.context = context;
        this.listtop = listtop;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensach,soluongsach;

        RelativeLayout topsach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tensach = itemView.findViewById(R.id.itemtop_tensach);
           soluongsach = itemView.findViewById(R.id.itemtop_soluong);
           topsach = itemView.findViewById(R.id.itemtop);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sach_muon_nhieu_nhat,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tensach.setText(listtop.get(position).getTensach());
        holder.soluongsach.setText(String.valueOf(listtop.get(position).getSoluong()));

    }

    @Override
    public int getItemCount() {
        return listtop.size();
    }


}
