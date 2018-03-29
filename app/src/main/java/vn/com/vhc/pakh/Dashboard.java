package vn.com.vhc.pakh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import info.UserInfo;

public class Dashboard extends AppCompatActivity {

    Button searchsrq, addrq, searchprq, logout;
    TextView hello;

    UserSessionManager session;
    UserInfo userInfo = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        session = new UserSessionManager(getApplicationContext());

        searchsrq = (Button) findViewById(R.id.searchSRQ);
        addrq = (Button) findViewById(R.id.addRQ);
        searchprq = (Button) findViewById(R.id.searchPRQ);
        logout = (Button) findViewById(R.id.logout);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(UserSessionManager.KEY_NAME);
        String email = user.get(UserSessionManager.KEY_EMAIL);

        addrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AddRequest.class);
                Dashboard.this.startActivity(intent);
            }
        });

        searchsrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, SearchUserSRQ.class);
                Dashboard.this.startActivity(intent);
            }
        });

        searchprq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userInfo.getUsername();
                Intent intent = new Intent(Dashboard.this, SearchUserPRQ.class);
                intent.putExtra("User", user);
                Dashboard.this.startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });
    }
}
