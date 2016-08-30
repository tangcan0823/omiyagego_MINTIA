package com.example.tangcan0823.mintia_omiyagego;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tangcan0823 on 2016/08/26.
 */
public class DetailRoadFragment extends Fragment {

    String search;

    /*User Location(Debug Data)*/
    private final double myLat = 36.108650, myLng = 140.099812;

    public static DetailRoadFragment newInstance(String info, String search) {
        Bundle args = new Bundle();
        DetailRoadFragment fragment = new DetailRoadFragment();
        args.putString("info", info);
        Log.d("debug", "1:" + search);
        args.putString("search", search);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_road, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        tvInfo.setText(getArguments().getString("info"));
        this.search = getArguments().getString("search");

        android.support.v7.widget.CardView mapCard = (android.support.v7.widget.CardView) view.findViewById(R.id.map_card);
        mapCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Click Event
                openGoogleMap(myLat, myLng);
                Log.d("debug", "10:" + search);
            }
        });

        return view;
    }

    public void openGoogleMap(double lat,double lng){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
        intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr="+search+"&dirflg="+"w"));
        startActivity(intent);
    }

}
