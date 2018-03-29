package vn.com.vhc.pakh;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import info.LinkAPI;
import info.UserInfo;

public class Login extends AppCompatActivity {

    ConnectionDetector cd;

    UserInfo userInfo;
    EditText edtUsername, edtPassword;
    Button login;

    CountDownTimer countDownTimer;
    String userpass, userInput, passInput;

    LinkAPI linkapi = new LinkAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        getWindow().setBackgroundDrawableResource(R.drawable.background);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        cd = new ConnectionDetector(this);

        edtUsername = (EditText) findViewById(R.id.edt_User);
        edtPassword = (EditText) findViewById(R.id.edt_Password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput = edtUsername.getText().toString();
                passInput = edtPassword.getText().toString();
                if (userInput.trim().length() == 0 && passInput.trim().length() == 0) {
                    Toast.makeText(Login.this, "Bạn phải nhập Username và Password.", Toast.LENGTH_SHORT).show();
                } else  {
                    if (cd.isConnected()) {
                        startCheckPass();
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void startCheckPass() {
        countDownTimer = new CountDownTimer(200, 100) {

            @Override
            public void onTick(long millisUntilFinished) {
                ParseInputData();
            }

            @Override
            public void onFinish() {
                CheckPass();
            }
        }.start();
        //UI process
        Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();
    }

    private void ParseInputData() {
        new Read_UserInfo().execute(linkapi.linkLogin+"username="+userInput+"&password="+passInput);
    }

    private void CheckPass() {
        String md5pass = encryptMD5(passInput);
        if (md5pass.equals(userpass)){
            countDownTimer.cancel();
            Intent intent = new Intent(Login.this, Dashboard.class);
            Login.this.startActivity(intent);
        } else {
            countDownTimer.cancel();
            Toast.makeText(Login.this, "Username hoặc Password sai.", Toast.LENGTH_SHORT).show();
        }

    }

    private class Read_UserInfo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    content = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        content.append(line);
                    }
                    br.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return content.toString();
        }

        protected  void onPostExecute(String s){
            super.onPostExecute(s);
            getUserInfo(s);
        }
    }

    private String getUserInfo(String s) {
        try {
            JSONObject object = new JSONObject(s);
            int id = object.getInt("id");
            String username = object.getString("username");
            String password = object.getString("password");
            String fullname = object.getString("fullname");
            String position = object.getString("position");
            String phone = object.getString("phone");
            String gender = object.getString("gender");
            String email = object.getString("email");
            String departmentCode = object.getString("departmentCode");
            String isEnable  = object.getString("isEnable");

            userInfo = new UserInfo(id, username, password, fullname, position, phone, gender, email, departmentCode, isEnable);
            userpass = userInfo.getPassword();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    private String encryptMD5 (String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(data.getBytes());
            BigInteger num = new BigInteger(1, messageDigest);
            String hashtext = num.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}



