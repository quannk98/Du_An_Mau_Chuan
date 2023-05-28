package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Sach_Muon_Nhieu_Nhat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import quannkph29999.fpoly.du_an_mau_quannkph29999.DAO.ThongKeDAO;
import quannkph29999.fpoly.du_an_mau_quannkph29999.Model.TopBook;
import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Sach_Muon_Nhieu_Nhat_Fragment extends Fragment {
        AdapterTopSach adapterTopSach;
        ArrayList<TopBook> listtop;
        RecyclerView recyclerView;
        ThongKeDAO thongKeDAO;

    public Sach_Muon_Nhieu_Nhat_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_sach__muon__nhieu__nhat_, container, false);
       recyclerView = view.findViewById(R.id.smnn_recycview);
       thongKeDAO = new ThongKeDAO(getContext());
       listtop = (ArrayList<TopBook>) thongKeDAO.getTop();
       adapterTopSach = new AdapterTopSach(getContext(),listtop);
       recyclerView.setAdapter(adapterTopSach);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}