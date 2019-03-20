package com.example.administrator.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/12/24.
 */
public class xgmmActivity extends AppCompatActivity {
    //声明SharedPreferences对象。
    SharedPreferences sharedPreferences;
    //声明Editor对象。
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xgmm_view);
        Button button = (Button) findViewById(R.id.button_qrxgmm);
        final EditText zhanghao = (EditText)findViewById(R.id.editText_xgzh);
        final EditText oldpassWord = (EditText)findViewById(R.id.editText_xgjmm);
        final EditText newpassword = (EditText)findViewById(R.id.editText_xgxmm);
        sharedPreferences=getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/update").newBuilder();
//addQueryParameter 添加查询参数
                String zh = zhanghao.getText().toString();
                final String opw = oldpassWord.getText().toString();
                final String npw = newpassword.getText().toString();
                String id_spf = sharedPreferences.getString("id", null);
                String passWord_spf = sharedPreferences.getString("passWord", null);
                if (opw.equals(passWord_spf)) {
                    urlBuilder.addQueryParameter("id_1", zh);
                    urlBuilder.addQueryParameter("password_1", npw);
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
                                editor.putString("passWord",npw);
                                editor.commit();
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
                } else {
                    String showpassWord=sharedPreferences.getString("passWord",null);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "密码不匹配，旧密码是"+showpassWord, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
}
