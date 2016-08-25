package com.example.tangcan0823.mintia_omiyagego;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tangcan0823 on 2016/08/25.
 */
public class FoodFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, null);
        Intent intent = new Intent(getActivity(),FoodViewActivity.class);
        startActivity(intent);
        return view;

    }
}
