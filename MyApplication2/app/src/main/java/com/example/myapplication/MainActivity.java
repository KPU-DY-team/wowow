package com.example.go.hello;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class main extends AppCompatActivity implements View.OnClickListener{
    Button join_btn;                 // 회원가입 버튼
    Button login_btn;                // 로그인 버튼

    EditText id_edit;                // id 에디트
    EditText pw_edit;                // pw 에디트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        join_btn = (Button)findViewById(R.id.join_btn);    // 회원가입 버튼을 찾고
        login_btn = (Button)findViewById(R.id.login_btn);  // 로그인 버튼을 찾고

        join_btn.setOnClickListener(this);                 // 리스너를 달아줌.
        login_btn.setOnClickListener(this);                // 리스너를 달아줌.

        id_edit = (EditText)findViewById(R.id.id_edit);    // id 에디트를 찾음.
        pw_edit = (EditText)findViewById(R.id.pw_edit);    // pw 에디트를 찾음.
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join_btn:     // 회원가입 버튼을 눌렀을 때
                Intent intent = new Intent(main.this, join.class);
                startActivity(intent);  // 새 액티비티를 열어준다.
                finish();               // 현재의 액티비티는 끝내준다.
                break;
            case R.id.login_btn:    // 로그인 버튼을 눌렀을 때
                Intent intent2 = new Intent(main.this, login.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}