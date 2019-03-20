package com.example.administrator.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.administrator.helloworld.UserBean;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/21.
 */
public class dlActivity extends AppCompatActivity {

    //声明SharedPreferences对象。
    SharedPreferences sharedPreferences;
    //声明Editor对象。
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_view);
        Button button = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button4);
        Button button3 = (Button) findViewById(R.id.button_qrdl);
        final EditText zhanghao = (EditText)findViewById(R.id.editText_dlzh);
        final EditText passWord = (EditText)findViewById(R.id.editText_dlmm);
        sharedPreferences=getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示方式声明Intent，直接启动SecondActivity
                Intent intent = new Intent(dlActivity.this, zcActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //显示方式声明Intent，直接启动SecondActivity
                Intent intent = new Intent(dlActivity.this, xgmmActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/ListUserByname").newBuilder();
//addQueryParameter 添加查询参数
                String zh = zhanghao.getText().toString();
                final String pwd = passWord.getText().toString();
                urlBuilder.addQueryParameter("name", zh);
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

                            final UserBean[] userBean = new Gson().fromJson(data, UserBean[].class);
                            UserBean userbean1 = userBean[0];
                            final String mima = userbean1.getPassword().toString();
                            if (response.isSuccessful()) {
                                if(mima.equals(pwd)){
                                    editor.putString("id",zhanghao.getText().toString());
                                    editor.putString("passWord",passWord.getText().toString());
                                    //提交数据。
                                    editor.commit();
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "登录成功", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                    Looper.loop();
                                }else{
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "登录失败", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                    Looper.loop();
                                }
                            } else {
                                Log.e("asd", "okHttp is request error");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
