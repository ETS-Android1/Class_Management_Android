package com.example.class_management_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    private EditText edt_user_name;
    private EditText edt_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_user_name = (EditText)findViewById(R.id.edtUserName);
        edt_password = (EditText)findViewById(R.id.edtPasswords);
        btn_login = (Button)findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkLogin(edt_user_name.getText().toString(), edt_password.getText().toString());
            }
        });
    }

    private void checkLogin(String _user_name, String _password)
    {
        if (_user_name.isEmpty() || _password.isEmpty())
        {
            Toast.makeText(LoginActivity.this, "Username or password is empty!", Toast.LENGTH_LONG).show();
            return;
        }
        if ((_user_name.equals("admin")) && (_password.equals("admin")))
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Incorrect account or password!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}