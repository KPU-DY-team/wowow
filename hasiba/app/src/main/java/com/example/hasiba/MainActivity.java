package org.logintest;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.List;



import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.NameVal‎uePair;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.utils.URLEncodedUtils;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameVal‎uePair;

import org.apache.http.params.HttpConnectionParams;



import android.app.Activity;

import android.content.Intent;

import android.os.AsyncTask;

import android.os.Bundle;

import android.os.Handler;

import android.os.StrictMode;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;

import android.widget.Toast;



public class LoginTestActivity extends Activity {

    /** Called when the activity is first created. */



    EditText et_id;

    EditText et_pw;

    TextView tv_msg;



    private static String id = "";

    private static String pw = "";

    private static String msgString = null;

    private static boolean isConnected = false;



    @Override

    public void onCreate(Bundle savedInstanceState) {

        StrictMode.enableDefaults();



        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);



        et_id = (EditText) findViewById(R.id.edit_id);

        et_pw = (EditText) findViewById(R.id.edit_pw);



        tv_msg = (TextView) findViewById(R.id.text_msg);



        Button btn_web = (Button) findViewById(R.id.btn_web);

        btn_web.set[안내]태그제한으로등록되지않습니다-xxOnClickListener(new View.[안내]태그제한으로등록되지않습니다-xxOnClickListener() {



            public void [안내]태그제한으로등록되지않습니다-xxonClick(View v) {

                Intent intent = new Intent(LoginTestActivity.this, WebViewTest.class);

                startActivity(intent);

            }

        });



        final Button btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.set[안내]태그제한으로등록되지않습니다-xxOnClickListener(new View.[안내]태그제한으로등록되지않습니다-xxOnClickListener() {



            public void [안내]태그제한으로등록되지않습니다-xxonClick(View v) {



//                                        String url = "http://192.168.0.9:8080/0525/login.jsp";

                id = et_id.getText().toString();

                pw = et_pw.getText().toString();



                new Handler().post(new Runnable() {



                    public void run() {

                        String url = "http://125.179.204.2:8080/AndroidServer/login.jsp";

//                                                              String url = "http://125.179.204.2:8080/AndroidServer/login.jsp?id=mygirl2&pwd=1111";





                        new LoginTestActivity().connect(url);



                        while(true) {

                            if(isConnected) {

                                Toast.makeText(LoginTestActivity.this, msgString, Toast.LENGTH_LONG).show();

                                break;

                            }

                        }

                    }

                });

            }

        });

    }



    private void setText(String s) {

        tv_msg.setText(s);

    }



    private void connect(String url){

        new WebConnection().execute(url);

    }





    private class WebConnection extends AsyncTask<String, Void, HttpResponse>{



        @Override

        protected HttpResponse doInBackground(String... urls) {

            HttpClient client = new DefaultHttpClient();

            HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);

            List<NameVal‎uePair> params = new ArrayList<NameVal‎uePair>();

            params.add(new BasicNameVal‎uePair("id", id));

            params.add(new BasicNameVal‎uePair("pwd", pw));



            Log.i("id", id);

            Log.i("pwd", pw);



            String url = urls[0] + "?" + URLEncodedUtils.format(params, "UTF-8");

//                              String url = "http://125.179.204.2:8080/AndroidServer/login.jsp?id=mygirl2&pwd=1111";



            HttpGet httpGet = new HttpGet(url);



            HttpResponse response = null;

            String s ="";

            StringBuffer sb = null;

            try {

                response = client.execute(httpGet);

                HttpEntity entity = response.getEntity();

                InputStream is;

                BufferedReader br = null;

                if(entity != null) {

                    is = entity.getContent();

                    br =  new BufferedReader(new InputStreamReader(is));

                    s = "";

                    sb = new StringBuffer();

                }

                while((s = br.readLine()) != null) {

                    sb.append(s);

                }

                Log.i("sb : ", sb.toString());

                msgString = sb.toString().trim().toString();

                Log.i("msgString : ", msgString);

                isConnected = true;

            } catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }



            return response;

        }

    }

}