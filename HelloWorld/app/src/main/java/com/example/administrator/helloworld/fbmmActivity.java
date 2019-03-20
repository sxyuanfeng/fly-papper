package com.example.administrator.helloworld;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/12/24.
 */
public class fbmmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbmm_view);
        ImageView imageview = (ImageView) findViewById(R.id.imageView_mmfh);
        ImageView imageview2 = (ImageView) findViewById(R.id.imageView_mmfb);
        final EditText content = (EditText) findViewById(R.id.EditText_mmfb);
        imageview.setOnClickListener(new LocationCheckedListener());

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://172.29.67.32:8080/CRUD/fbmm").newBuilder();
//addQueryParameter 添加查询参数
                String mm = content.getText().toString();
                urlBuilder.addQueryParameter("fbmm_content", mm);
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

    class LocationCheckedListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setResult(10);
            finish();
        }
    }
}
