package com.example.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;

public class SecondActivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayer);

        EditText edittext= (EditText) findViewById(R.id.name1);

       String str = edittext.getText().toString();
        Button b= (Button)findViewById(R.id.save1);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                class AsyncT extends AsyncTask<Void,Void,Void> {

                    @Override
                    protected Void doInBackground(Void... params) {
                        EditText edittext= (EditText) findViewById(R.id.name1);
                        String str = edittext.getText().toString();

                        EditText edittext1= (EditText) findViewById(R.id.name2);
                        String str1 = edittext1.getText().toString();


                        try {
                            URL url = new URL("http://localhost:50497/api/user/save"); //Enter URL here
                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                            httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
                            httpURLConnection.connect();

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("UserFirstname", str);
                            jsonObject.put("UserLastname",str1);

                            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                            wr.writeBytes(jsonObject.toString());
                            wr.flush();
                            wr.close();

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }


                }

            }
        });

    }

    }


