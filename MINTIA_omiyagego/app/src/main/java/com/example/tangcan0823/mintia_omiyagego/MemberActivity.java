package com.example.tangcan0823.mintia_omiyagego;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangcan0823 on 2016/08/30.
 */
public class MemberActivity extends EuclidActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberActivity.this, "Oh Hello!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        int[] avatars = {
                R.drawable.tangcan,
                R.drawable.zhongyuran,
                R.drawable.fon,
                R.drawable.tangcan,
                R.drawable.puyue,
                R.drawable.tangcan,


                };
        String[] names = getResources().getStringArray(R.array.array_names);
        String[] des_short = getResources().getStringArray(R.array.lorem_ipsum_short);
        String[] des_long = getResources().getStringArray(R.array.lorem_ipsum_long);

        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, des_short[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, des_long[i]);
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }
}
