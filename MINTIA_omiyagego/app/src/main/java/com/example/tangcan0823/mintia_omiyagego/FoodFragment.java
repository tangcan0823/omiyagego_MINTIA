package com.example.tangcan0823.mintia_omiyagego;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tangcan0823 on 2016/08/25.
 */
public class FoodFragment extends Fragment {
    CardView okashi1;
    CardView okashi2;
    CardView okashi3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, null);
        okashi1 = (CardView)view.findViewById(R.id.okashi1);
        okashi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



}
