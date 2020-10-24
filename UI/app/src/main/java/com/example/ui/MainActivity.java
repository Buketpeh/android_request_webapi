package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button scndlyr;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scndlyr= findViewById(R.id.scndlyr);
         //Nesne İle Button İlişkilendirme


        //Butonumuza tıklama özelliği kazandırıyoruz.
        scndlyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent sınıfından nesnemizi oluşturuyoruz.
                //context ya da MainActivity.this diyerek bu activity de çalışacağını belirtiyoruz
                //Intent ıntent = new Intent(MainActivity.this, SecondActivity.class);
                Intent ıntent = new Intent(context, SecondActivty.class);
                //Activity başlatıyoruz bizden intent türünde nesne istiyor kendi oluşturduğumuz nesneyi kullanıyoruz.
                startActivity(ıntent);
            }
        });



        Button b= (Button)findViewById(R.id.liste);
        tv= (TextView)findViewById(R.id.tv);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tv.setText("sunucu cevabı");
                new arkaPlan().execute("https://raw.githubusercontent.com/Buketpeh/my_json_users/master/users.json");

            }
        });

    }
    class arkaPlan extends AsyncTask<String,String,String> {
        protected String doInBackground(String... params) {
            //ilk elemanı sunucu adresi URL
            HttpURLConnection connection = null;
            BufferedReader br = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String satir;
                String dosya = "";
                while ((satir = br.readLine()) != null) {
                    Log.d("satir", satir);
                    dosya += satir;

                }
                return dosya;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "hata";
        }
protected  void onPostExecute(String s){
            Log.d("postExecutetangelen",s);
            tv.setText(s);
            System.out.println("get data: "+s);
}
    }



}