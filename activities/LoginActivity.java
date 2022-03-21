package com.example.zyfypt_no7_406ml.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.bean.LoginBean;
import com.example.zyfypt_no7_406ml.iface.LoginListener;
import com.example.zyfypt_no7_406ml.model.LoginModel;

public class LoginActivity extends AppCompatActivity {

    private EditText etname;
    private EditText etpass;
    private Button btlogin;
    private Switch swh;
    private SharedPreferences sp;
    private String username = "", password = "", sessionID = "";

    private Button btreg;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        readSP();


    }

    private void initViews() {
        etname = findViewById(R.id.etuser);
        etpass = findViewById(R.id.etpwd);
        btlogin = findViewById(R.id.btlogin);
        swh = findViewById(R.id.swh);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        btreg=findViewById(R.id.btregister);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录的代码
                username = etname.getText().toString();
                password = etpass.getText().toString();
                LoginModel loginModel = new LoginModel();
                loginModel.getLoginResult(username, password,loginListener);

            }
        });

        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private LoginListener loginListener = new LoginListener() {
        @Override
        public void onResponse(LoginBean loginBean) {
            sessionID = loginBean.getSessionid();
            Log.i("LoginActivity", "----sessionID=" + sessionID);
            if (sessionID != null) {
                Toast.makeText(LoginActivity.this, "登录成功" + sessionID, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                saveSP();
                finish();
            } else
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

        };

    private void saveSP(){
        // Boolean remember = swh.isChecked();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",username);
        editor.putString("pass",password);
        editor.putBoolean("remember",swh.isChecked());
        editor.putString("sessionID",sessionID);
        editor.commit();//提交
    }
    private void readSP(){
        String strname = sp.getString("name",null);
        String strpass = sp.getString("pass",null);
        Boolean remember = sp.getBoolean("remember",false);
        swh.setChecked(remember);
        if(remember){
            etname.setText(strname);
            etpass.setText(strpass);
        }
    }


    }