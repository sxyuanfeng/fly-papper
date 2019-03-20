package com.example.administrator.helloworld;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/12/24.
 */
public class zcActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.zc_view);
            Button button = (Button) findViewById(R.id.button_qrzc);
            final EditText zhanghao = (EditText)findViewById(R.id.editText_zczh);
            final EditText passWord = (EditText)findViewById(R.id.editText_zcmm);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/insert").newBuilder();
//addQueryParameter 添加查询参数
                    String zh = zhanghao.getText().toString();
                    final String pwd = passWord.getText().toString();
                    urlBuilder.addQueryParameter("id_2", zh);
                    urlBuilder.addQueryParameter("password_2", pwd);
                    String url = urlBuilder.build().toString();
                    final Request request = new Request.Builder()
                            .url(url)
                            .build();
                    new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    OkHttpClient client = new OkHttpClient();
                                    okhttp3.Response response = client.newCall(request).execute();
                                    String data = response.body().string();
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            data, Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                    Looper.loop();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });
        }
}


