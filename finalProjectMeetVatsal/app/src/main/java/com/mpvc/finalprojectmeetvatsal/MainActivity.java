package com.mpvc.finalprojectmeetvatsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mpvc.finalprojectmeetvatsal.db.AppDatabase;
import com.mpvc.finalprojectmeetvatsal.db.dao.UserDao;
import com.mpvc.finalprojectmeetvatsal.db.entity.User;
import com.mpvc.finalprojectmeetvatsal.helpers.HelperMethods;
import com.mpvc.finalprojectmeetvatsal.recycler.HomePage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView btnSignUp;
    private EditText txtUserName,txtPassword;

    private AppDatabase appDatabase;
    private UserDao userDao;

    private final String SHARED_PREFERENCE_FILE="mv-commerce";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp = findViewById(R.id.main_btnSignUp);
        btnSignUp.setOnClickListener(this);

        btnLogin = findViewById(R.id.main_btnLogin);
        btnLogin.setOnClickListener(this);

        txtUserName=findViewById(R.id.main_txtUserName);
        txtPassword=findViewById(R.id.main_txtPassword);

        appDatabase=AppDatabase.getDatabaseInstance(this);
        userDao = appDatabase.userDao();

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE,Context.MODE_PRIVATE);
//        if(!TextUtils.isEmpty(sharedPreferences.getString("UserName",""))){
//            Intent i=new Intent(this, HomePage.class);
//            startActivity(i);
//        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE,Context.MODE_PRIVATE);
//        if(!TextUtils.isEmpty(sharedPreferences.getString("UserName",""))){
//            Intent i=new Intent(this, HomePage.class);
//            startActivity(i);
//        }
    }

    private void redirectIfUserExists(User user){
            if(user == null){
                HelperMethods.showToastMessage(getApplicationContext(),"Username or password is incorrect", Toast.LENGTH_SHORT);
            }
            else{

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UserName", user.getUserName().toString());
                editor.putInt("UserID", user.getUserID());
                editor.apply();

                Intent i=new Intent(this, HomePage.class);
                startActivity(i);
                finish();
            }
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent();
        switch (v.getId()) {
            case R.id.main_btnLogin:
                String userName=txtUserName.getText().toString();
                String password=txtPassword.getText().toString();

                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
                    HelperMethods.showToastMessage(this,"Fields cannot be empty!", Toast.LENGTH_SHORT);
                    break;
                }

                userDao.getUserByNameAndPassword(userName,password).observe(this, this::redirectIfUserExists);

                break;
            case R.id.main_btnSignUp:
                i=new Intent(this, RegisterPage.class);
                startActivity(i);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        int userId=sharedPreferences.getInt("UserID",0);
        if(userId==0){
            Intent i= new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}