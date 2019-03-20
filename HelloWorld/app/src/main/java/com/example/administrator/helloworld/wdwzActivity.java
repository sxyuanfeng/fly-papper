package com.example.administrator.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2019/1/2.
 */
public class wdwzActivity extends AppCompatActivity {

    //声明SharedPreferences对象。
    SharedPreferences sharedPreferences;
    //声明Editor对象。
    SharedPreferences.Editor editor;

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wodewenzhan_view);
        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        final String id = sharedPreferences.getString("id", null);

        mContext = wdwzActivity.this;
        list_animal = (ListView) findViewById(R.id.list_wodewenzhan);

        final SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) findViewById(R.id.wdwz_swipe_ly);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //do all refreshing tasks
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListArticleByid").newBuilder();
                urlBuilder.addQueryParameter("author_id", id);
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

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListArticleByid").newBuilder();
        urlBuilder.addQueryParameter("author_id", id);
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
                    String data = response.body().string();

                    final Article[] article = new Gson().fromJson(data, Article[].class);
                    list_animal.post(new Runnable() {
                        @Override
                        public void run() {
                            mData = new LinkedList<Animal>();
                            for (int i = 0; i < article.length; i++){
                                Article article1 = article[i];
                                final String content1 = article1.getContent().toString();
                                mData.add(new Animal("标题", content1, R.mipmap.moren));
                            }
                            mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
                            list_animal.setAdapter(mAdapter);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
