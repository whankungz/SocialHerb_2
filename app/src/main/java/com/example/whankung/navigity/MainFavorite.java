package com.example.whankung.navigity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.example.whankung.navigity.adapter.AppState;
import com.example.whankung.navigity.adapter.CustomAdapterH;


/**
 * Created by Whankung on 16/1/2560.
 */

public class MainFavorite extends android.support.v4.app.Fragment {
    private View rootView;
    private Typeface font;
    private TextView fav;
    private String title,titleid;
//    ArrayAdapter<String> adapter;
    private CustomAdapterH adapter;
    ListView lv;
//    private HRequest h;
//    public MainFavorite(HRequest h) {
//        this.h= h;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStat) {
        rootView = inflater.inflate(R.layout.stucture_favorite, container, false);
        setView();
//        Call<List<HRequest>> call = Http.getInstance().getHerb().loadJson();
//        fav.setText(h.getHerbName());
        return rootView;
    }

    private void setView() {
        fav = (TextView) rootView.findViewById(R.id.textF);
//
//        font = Typeface.createFromAsset(getContext().getAssets(), "tmedium.ttf");
//        t_H.setTypeface(font);
//        t_H2.setTypeface(font);
////        tb.setTypeface(font);
////        t_H3.setTypeface(font);
////        t_H4.setTypeface(font);

        lv = (ListView) rootView.findViewById(R.id.list_view);
        //   lv.setAdapter(AppState.getSingleInstance().getDataHerb());

//        final String[] herbs = new String[]{"ทับทิม", "มะนาว", "มะเขือ",
//                "ชมพู่", "กา", "ว่านหาง", "กระชายดำ", "ขิง",
//                "ตะไคร้หอม", "พริก"};

        int[] resId = { R.drawable.img_ruby
                , R.drawable.img_herb, R.drawable.img_lemon
                , R.drawable.img_ruby2, R.drawable.img_lemon5};
//        final String[] herbs = new String[]{"ทับทิม", "ตะไคร้หอม", "มะนาว",
//                "ฟ้าทะลายโจร", "พญายอ"};
        final String[] idH = new String[]{"1", "2", "3",
                "4", "5"};
        final String[] herbs = new String[]{AppState.getSingleInstance().getDataHerb()};
        if (AppState.getSingleInstance().isDataHerb2(true)) {
          //  fav.setText(AppState.getSingleInstance().getNameH());
//            fav.setText(AppState.getSingleInstance().getNamePhama());

// adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item, R.id.product_name, herbs);
            adapter = new CustomAdapterH(getActivity().getApplicationContext(), herbs, resId);

            lv.setAdapter(adapter);
          //  lv.getAdapter().getCount();
            lv.setTextFilterEnabled(true);
//            fav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentManager m = getFragmentManager();
//                    FragmentTransaction t = m.beginTransaction();
//                    t.replace(R.id.container, new SearchHerb());
//                    t.commit();
//
//                }
//            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    FragmentManager m = getFragmentManager();
                    FragmentTransaction t = m.beginTransaction();
//                    title = herbs[position];
// titleid = idH[position];
                    t.replace(R.id.container, new SearchHerb(title, titleid));
                    t.commit();

                }
            });
        }
    }
}
