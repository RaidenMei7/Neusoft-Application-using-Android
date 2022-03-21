package com.example.zyfypt_no7_406ml.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.iface.RegListener;
import com.example.zyfypt_no7_406ml.model.RegModel;

public class RegActivity extends AppCompatActivity {
    private EditText ed1,ed2,ed3,ed4,ed5;
    private Button bt1;

    private String username,password,tel,roleid,email;


    public void Init()
    {
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        ed3=findViewById(R.id.ed3);
        ed4=findViewById(R.id.ed4);
        ed5=findViewById(R.id.ed5);

        bt1=findViewById(R.id.bt1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=ed1.getText().toString();
                password=ed2.getText().toString();
                tel=ed3.getText().toString();
                roleid=ed4.getText().toString();
                email=ed5.getText().toString();

                RegModel regModel = new RegModel();
                regModel.getRegResult(username,password,tel,roleid,email,regListener);
            }
        });


    }
    private RegListener regListener = new RegListener() {
        @Override
        public void onResponse(String success) {
            if(username!=null && password!=null && tel!=null && roleid!=null && email!=null)
            {
                Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Init();


    }


}