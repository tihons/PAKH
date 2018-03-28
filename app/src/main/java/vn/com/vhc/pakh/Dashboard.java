package vn.com.vhc.pakh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import info.UserInfo;

public class Dashboard extends AppCompatActivity {

    Button searchrq, addrq, prorq, logout;
    TextView hello;

    UserInfo userInfo = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        hello = (TextView) findViewById(R.id.dashboardText) ;
        searchrq = (Button) findViewById(R.id.searchRQ);
        addrq = (Button) findViewById(R.id.addRQ);
        prorq = (Button) findViewById(R.id.proRQ);
        logout = (Button) findViewById(R.id.logout);

        hello.setText("Xin Chào "+userInfo.getFullname());

        addrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AddRequest.class);
                Dashboard.this.startActivity(intent);
            }
        });

        searchrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Search.class);
                Dashboard.this.startActivity(intent);
            }
        });

        prorq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userInfo.getUsername();
                Intent intent = new Intent(Dashboard.this, RequestofUser.class);
                intent.putExtra("User", user);
                Dashboard.this.startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Login.class);
                Dashboard.this.startActivity(intent);
            }
        });
    }
}
