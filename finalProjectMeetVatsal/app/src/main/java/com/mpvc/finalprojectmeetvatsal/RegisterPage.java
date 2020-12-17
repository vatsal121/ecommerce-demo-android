package com.mpvc.finalprojectmeetvatsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mpvc.finalprojectmeetvatsal.db.AppDatabase;
import com.mpvc.finalprojectmeetvatsal.db.dao.UserDao;
import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.db.entity.User;
import com.mpvc.finalprojectmeetvatsal.helpers.HelperMethods;
import com.mpvc.finalprojectmeetvatsal.recycler.HomePage;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister;
    private EditText txtUserName,txtPassword,txtConfirmPassword;
    private TextView btnLogin;

    AppDatabase appDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        btnLogin = findViewById(R.id.registerPage_btnLogin);
        btnLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.registerPage_btnRegister);
        btnRegister.setOnClickListener(this);


        txtUserName=findViewById(R.id.registerPage_txtUserName);
        txtPassword=findViewById(R.id.registerPage_txtPassword);
        txtConfirmPassword=findViewById(R.id.registerPage_txtConfirmPassword);

        appDatabase=AppDatabase.getDatabaseInstance(this);
        userDao = appDatabase.userDao();
    }


    public void onRegister(User user,String userName,String password){
        if(user != null){
            HelperMethods.showToastMessage(RegisterPage.this,"Username already taken!. Please choose a different username", Toast.LENGTH_LONG);
        }
        else {
            User newUser = new User(userName,password);
            AppDatabase.databaseWriteExecutor.execute(()->{
                userDao.insert(newUser);
            });
            HelperMethods.showToastMessage(RegisterPage.this,"Successfully Registered",Toast.LENGTH_LONG);
            txtUserName.setText("");
            txtPassword.setText("");
            txtConfirmPassword.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent();
        switch (v.getId()) {
            case R.id.registerPage_btnLogin:
                i=new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.registerPage_btnRegister:
                String userName=txtUserName.getText().toString();
                String password=txtPassword.getText().toString();
                String cPass=txtConfirmPassword.getText().toString();
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cPass)){
                    HelperMethods.showToastMessage(this,"Fields cannot be empty!", Toast.LENGTH_SHORT);
                    break;
                }
                if(!TextUtils.equals(password,cPass)){
                    HelperMethods.showToastMessage(RegisterPage.this,"Passwords and Confirm Password does not match!", Toast.LENGTH_LONG);
                    break;
                }

                LiveData<User> userLiveData =userDao.getUserByName(userName);
                userLiveData.observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        onRegister(user,userName,password);
                        userLiveData.removeObservers(RegisterPage.this);
                    }
                });

                break;
            default:
                break;
        }
    }
}