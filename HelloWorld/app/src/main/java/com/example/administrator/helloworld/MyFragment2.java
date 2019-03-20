package com.example.administrator.helloworld;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/12/20.
 */
public class MyFragment2 extends Fragment {

    private ImageView imageview;
    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fg_sd, container, false);

        imageview = (ImageView) view.findViewById(R.id.imageView_sd);
        imageview.setOnClickListener(new LocationCheckedListener());

        final SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.sd_swipe_ly);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //do all refreshing tasks
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListMimi").newBuilder();
                String url = urlBuilder.build().toString();
                final Request request = new Request.Builder()
                        .url(url)
                        .build();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            //newCall方法会得到一个Call对象，表示一个新的网络请求
                            //execute方法是同步方法，会阻塞当前线程，并返回Response对象
                            okhttp3.Response response = client.newCall(request).execute();
                            String data=response.body().string();

                            final String[] string = new Gson().fromJson(data, String[].class);
                            final ListView list_mimi = (ListView) view.findViewById(R.id.list_mimi);
                            list_mimi.post(new Runnable() {
                                @Override
                                public void run() {
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (getActivity(),android.R.layout.simple_expandable_list_item_1,string);
                                    list_mimi.setAdapter(adapter);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                swipeContainer.setRefreshing(false);
            }
        });
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListMimi").newBuilder();
        String url = urlBuilder.build().toString();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //newCall方法会得到一个Call对象，表示一个新的网络请求
                    //execute方法是同步方法，会阻塞当前线程，并返回Response对象
                    okhttp3.Response response = client.newCall(request).execute();
                    String data=response.body().string();

                    final String[] string = new Gson().fromJson(data, String[].class);
                    final ListView list_mimi = (ListView) view.findViewById(R.id.list_mimi);
                    list_mimi.post(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (getActivity(),android.R.layout.simple_expandable_list_item_1,string);
                            list_mimi.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }
    class LocationCheckedListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), fbmmActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 10){
            MainActivity mainActivity = (MainActivity) getActivity();
            FragmentManager fragmentManager = mainActivity.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ly_content, new MyFragment2());
            fragmentTransaction.commit();
        }
    }
}
