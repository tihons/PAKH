package vn.com.vhc.pakh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    TextView tvTitle, tvRqContent, tvRqSysCode, tvProDep, tvProUser, tvProActua, tvProPlan, tvProContent;

    String rqTitle, rqContent, rqSysCode, proDepCode, proUser, proActua, proPlan, proContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        rqTitle = getIntent().getExtras().getString("RqTitle");
        rqContent = getIntent().getExtras().getString("RqContent");
        rqSysCode = getIntent().getExtras().getString("RqSysCode");
        proDepCode = getIntent().getExtras().getString("ProDepCode");
        proUser = getIntent().getExtras().getString("ProUser");
        proActua = getIntent().getExtras().getString("ProActua");
        proPlan = getIntent().getExtras().getString("ProPlan");
        proContent = getIntent().getExtras().getString("ProContent");

        tvTitle = (TextView) findViewById(R.id.reqTitle);
        tvRqContent = (TextView) findViewById(R.id.reqContent);
        tvRqSysCode = (TextView) findViewById(R.id.reqSystemCode);
        tvProDep = (TextView) findViewById(R.id.proDepart);
        tvProUser = (TextView) findViewById(R.id.proUser);
        tvProActua = (TextView) findViewById(R.id.proActua);
        tvProPlan = (TextView) findViewById(R.id.proPlan);
        tvProContent = (TextView) findViewById(R.id.proContent);

        tvTitle.setText(rqTitle);
        tvRqContent.setText(rqContent);
        tvRqSysCode.setText(rqSysCode);
        tvProDep.setText(proDepCode);
        tvProUser.setText(proUser);
        tvProActua.setText(proActua);
        tvProPlan.setText(proPlan);
        tvProContent.setText(proContent);
    }
}
