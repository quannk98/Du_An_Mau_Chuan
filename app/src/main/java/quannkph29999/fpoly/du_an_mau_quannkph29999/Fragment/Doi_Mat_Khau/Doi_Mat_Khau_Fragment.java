package quannkph29999.fpoly.du_an_mau_quannkph29999.Fragment.Doi_Mat_Khau;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import quannkph29999.fpoly.du_an_mau_quannkph29999.R;


public class Doi_Mat_Khau_Fragment extends Fragment {
      ImageButton showmkcu,showmkm,shownlmk;
      boolean checkdmk = true;


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
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showmkcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdmk == true){
                    showmkcu.setImageResource(R.drawable.hidepassword);
                    checkdmk = false;
                }
                else if(checkdmk == false ){
                    showmkcu.setImageResource(R.drawable.showpassword);
                    checkdmk = true;
                }
            }
        });
        showmkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdmk == true){
                    showmkm.setImageResource(R.drawable.hidepassword);
                    checkdmk = false;
                }
                else if(checkdmk == false ){
                    showmkm.setImageResource(R.drawable.showpassword);
                    checkdmk = true;
                }
            }
        });
        shownlmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkdmk == true){
                    shownlmk.setImageResource(R.drawable.hidepassword);
                    checkdmk = false;
                }
                else if(checkdmk == false ){
                    shownlmk.setImageResource(R.drawable.showpassword);
                    checkdmk = true;
                }
            }
        });
    }
}