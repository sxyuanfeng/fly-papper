package com.example.administrator.helloworld;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/12/20.
 */
public class MyFragment extends Fragment {

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;


    private ImageView imageview;
    public MyFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fg_ycgs, container, false);

        imageview = (ImageView) view.findViewById(R.id.imageView_tj);

        imageview.setOnClickListener(new LocationCheckedListener());
        final SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.ycgs_swipe_ly);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //do all refreshing tasks
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListArticle").newBuilder();
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

                            final Article[] article = new Gson().fromJson(data, Article[].class);
                            mData.clear();
                            mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
                            for (int i = 0; i < article.length; i++){
                                Article article1 = article[i];
                                final String content1 = article1.getContent().toString();
                                mData.add(new Animal("标题", content1, R.mipmap.moren));
                            }
                            mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                swipeContainer.setRefreshing(false);
            }
        });

        mContext = getActivity();
        list_animal = (ListView) view.findViewById(R.id.list_animal);

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListArticle").newBuilder();
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

                    final Article[] article = new Gson().fromJson(data, Article[].class);
                    mData = new LinkedList<Animal>();
                    for (int i = 0; i < article.length; i++){
                        Article article1 = article[i];
                        final String content1 = article1.getContent().toString();
                        mData.add(new Animal("标题", content1, R.mipmap.moren));
                    }
                    mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
                    mAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(),ydqwActivity.class);
                            startActivity(intent);
                        }
                    });
                    list_animal.setAdapter(mAdapter);
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
            intent.setClass(getActivity(), CzgsActivity.class); //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
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
            fragmentTransaction.replace(R.id.ly_content, new MyFragment());
            fragmentTransaction.commit();
        }
    }
}




