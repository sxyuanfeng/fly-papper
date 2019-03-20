package com.example.administrator.helloworld;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/12/20.
 */
public class MyFragment3 extends Fragment {

    //声明SharedPreferences对象。
    SharedPreferences sharedPreferences;
    //声明Editor对象。
    SharedPreferences.Editor editor;

    private ImageView imageview;
    private ImageView imageview2;
    private ImageView imageview3;
    private ImageView imageview4;
    private ImageView imageview5;
    private ImageView imageview6;
    private ImageView imageview7;
    private TextView textview;
    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_yh, container, false);

        sharedPreferences=this.getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        final String yhid = sharedPreferences.getString("id", null);

        imageview = (ImageView) view.findViewById(R.id.imageView_dl);
        imageview2 = (ImageView) view.findViewById(R.id.imageView_xx);
        imageview3 = (ImageView) view.findViewById(R.id.imageView_guanzhu);
        imageview4 = (ImageView) view.findViewById(R.id.imageView_shoucang);
        imageview5 = (ImageView) view.findViewById(R.id.imageView_wodewenzhan);
        imageview6 = (ImageView) view.findViewById(R.id.imageView_dc);
        imageview7 = (ImageView) view.findViewById(R.id.imageView_tx);
        textview = (TextView) view.findViewById(R.id.textview_id);

        imageview.setOnClickListener(new LocationCheckedListener());
        imageview2.setOnClickListener(new LocationCheckedListener2());
        imageview3.setOnClickListener(new LocationCheckedListener3());
        imageview4.setOnClickListener(new LocationCheckedListener4());
        imageview5.setOnClickListener(new LocationCheckedListener5());
        imageview6.setOnClickListener(new LocationCheckedListener6());
        imageview7.setOnClickListener(new LocationCheckedListener8());
        textview.setText(yhid);
        textview.setOnClickListener(new LocationCheckedListener7());
        return view;
    }

    class LocationCheckedListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), dlActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }
    class LocationCheckedListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), xiaoxiActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }
    class LocationCheckedListener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), gzActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }
    class LocationCheckedListener4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), scActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }
    class LocationCheckedListener5 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), wdwzActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }
    class LocationCheckedListener6 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(sharedPreferences!=null) {
                sharedPreferences.edit().clear().commit();
                Toast toast = Toast.makeText(getActivity(),
                        "登出成功", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                textview.setText("");
            } else{
                Toast toast = Toast.makeText(getActivity(),
                        "未登录", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }
    class LocationCheckedListener7 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String yhid2 = sharedPreferences.getString("id", null);
            textview.setText(yhid2);
        }
    }
    class LocationCheckedListener8 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), xgxxActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }
}
