package vn.com.vhc.pakh;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import info.LinkAPI;
import info.SystemInfo;
import info.UserInfo;
import info.YeuCauInfo;

public class AddRequest extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = AddRequest.class.getSimpleName();

    String checkMail = "";
    String checkSMS = "";
    String sysCode = "";
    String pro_dep_code = "";

    int id;
    String requestCode, requestName, departmentCode, isEnable, isStatus;
    JSONArray arrayYeuCau, arrayYCC2;

    YeuCauInfo yeuCauInfo;
    UserInfo userInfo;
    SystemInfo systemInfo;

    EditText title, content;

    ArrayAdapter<String> adapterSysType, adapterYeuCau, adaperYC2;
    ArrayList<SystemInfo> listSystemType = new ArrayList<SystemInfo>();
    ArrayList<YeuCauInfo> listCap1 = new ArrayList<YeuCauInfo>();
    ArrayList<YeuCauInfo> listCap2 = new ArrayList<YeuCauInfo>();
    Spinner spnSysType, spnYCCap1,spnYCCap2;

    private Button sendRequest, btnRequestOne, btnRight;
    private TextView doViXL;

    CheckBox receiveMail, receiveSMS;
    LinkAPI linkapi = new LinkAPI();
    String linkC1;
    String userName, userDepart, userAPI_info;

    String donvixuli = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_request);

        userName = userInfo.getUsername();
        userDepart = userInfo.getDepartmentCode();
        userAPI_info = "username="+userName+"&departmentCode="+userDepart;

        addView();
        addAdapter();

        new ReadSystemType().execute(linkapi.linkHT);

        ClickEvents();
    }

    private void addView() {
        title = (EditText) findViewById(R.id.tieuDe);
        content =(EditText) findViewById(R.id.noiDung);

        receiveMail = (CheckBox) findViewById(R.id.nhanMail);
        receiveSMS =  (CheckBox) findViewById(R.id.nhanSMS);
        doViXL = (TextView) findViewById(R.id.donViXuLy);

        sendRequest = (Button) findViewById(R.id.sendRequest);
        btnRequestOne = (Button) findViewById(R.id.btnBanNhap);
        btnRight = (Button) findViewById(R.id.boqua);

        spnSysType = (Spinner) findViewById(R.id.spnSysType);
        spnYCCap1 = (Spinner) findViewById(R.id.spnYC1);
        spnYCCap2 = (Spinner) findViewById(R.id.spnYC2);
    }

    private void addAdapter() {
        adapterSysType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapterSysType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSysType.setAdapter(adapterSysType);

        adapterYeuCau = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapterYeuCau.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adaperYC2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adaperYC2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnYCCap2.setAdapter(adaperYC2);
        spnYCCap1.setAdapter(adapterYeuCau);
    }

    private void ClickEvents() {
        receiveSMS.setOnClickListener(this);
        receiveMail.setOnClickListener(this);
        receiveSMS.setChecked(true);
        receiveMail.setChecked(true);
        checkSMS = "Y";
        checkMail = "Y";

        receiveMail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton btnViewMail, boolean b) {

                if (btnViewMail.isChecked()){
                    checkMail = "Y";
                }else {
                    checkMail = "N";
                }

            }
        });

        receiveSMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onClick(View view) {

            }

            @Override
            public void onCheckedChanged(CompoundButton btnViewSMS, boolean b) {
                if (btnViewSMS.isChecked()){
                    checkSMS = "Y";
                }else {
                    checkSMS = "N";
                }
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        btnRequestOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.length() == 0 || content.length() == 0){
                    showAlertDialogNullContent();
                } else {
                    new SendPostRequest().execute(linkapi.linkSendRQ);
                    Toast.makeText(getApplicationContext(), "Gửi yêu cầu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddRequest.this, Dashboard.class);
                    startActivity(intent);
                }
            }
        });

        spnSysType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                adapterYeuCau.clear();
                adaperYC2.clear();

                String syscode = listSystemType.get(pos).getSysCode();
                linkC1 = linkapi.linkYeuCau+userAPI_info+"&systemCode="+syscode;
                new ReadJSONObjectYCCap1().execute(linkC1);
//                isHas = listCap1.get(0).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnYCCap1.setSelection(0);

        spnYCCap1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adaperYC2.clear();
                int isHas = listCap1.get(i).getId();
                donvixuli = listCap1.get(i).getDepartmentCode();
                doViXL.setText(donvixuli);
                new ReadJSONObjectYCCap2().execute(linkC1+"&isHas="+isHas);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnYCCap2.setSelection(0);
        spnYCCap2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hủy yêu cầu");
        builder.setMessage("Bạn có muốn hủy không ?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(AddRequest.this, Dashboard.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public void showAlertDialogNullContent(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lỗi");
        builder.setMessage("Yêu cầu nhập đây đủ thông tin ?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View view) {

    }

    private class ReadSystemType extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL (strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        protected  void onPostExecute(String s){
            super.onPostExecute(s);

            try {
                JSONArray arrSysType = new JSONArray(s);

                for (int i = 0; i < arrSysType.length(); i ++){
                    JSONObject object = arrSysType.getJSONObject(i);

                    systemInfo = new SystemInfo();
                    String sysType = object.getString("systemCode");

                    systemInfo.setSysCode(sysType);

                    listSystemType.add(systemInfo);
                    adapterSysType.add(""+listSystemType.get(i).getSysCode());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReadJSONObjectYCCap1 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL (strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        protected  void onPostExecute(String s){
            super.onPostExecute(s);

            try {
                listCap1 = new ArrayList<YeuCauInfo>();
                arrayYeuCau = new JSONArray(s);

                for (int i = 0; i < arrayYeuCau.length(); i ++){
                    JSONObject object = arrayYeuCau.getJSONObject(i);

                    yeuCauInfo = new YeuCauInfo();
                    id = object.getInt("id");
                    requestCode = object.getString("requestCode");
                    requestName = object.getString("requestName");
                    departmentCode = object.getString("departmentCode");
                    isEnable = object.getString("isEnable");
                    isStatus = object.getString("isStatus");

                    yeuCauInfo.setId(id);
                    yeuCauInfo.setRequestCode(requestCode);
                    yeuCauInfo.setRequestName(requestName);
                    yeuCauInfo.setDepartmentCode(departmentCode);
                    yeuCauInfo.setIsEnable(isEnable);
                    yeuCauInfo.setIsStatus(isStatus);

                    listCap1.add(yeuCauInfo);
                    adapterYeuCau.add(""+listCap1.get(i).getRequestName());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReadJSONObjectYCCap2 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL (strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        protected  void onPostExecute(String s){
            super.onPostExecute(s);

            try {
                listCap2 = new ArrayList<YeuCauInfo>();
                arrayYCC2= new JSONArray(s);

                for (int i = 0; i < arrayYCC2.length(); i ++){
                    JSONObject object = arrayYCC2.getJSONObject(i);

                    yeuCauInfo = new YeuCauInfo();
                    id = object.getInt("id");
                    requestCode = object.getString("requestCode");
                    requestName = object.getString("requestName");
                    departmentCode = object.getString("departmentCode");
                    isEnable = object.getString("isEnable");
                    isStatus = object.getString("isStatus");

                    yeuCauInfo.setId(id);
                    yeuCauInfo.setRequestCode(requestCode);
                    yeuCauInfo.setRequestName(requestName);
                    yeuCauInfo.setDepartmentCode(departmentCode);
                    yeuCauInfo.setIsEnable(isEnable);
                    yeuCauInfo.setIsStatus(isStatus);

                    listCap2.add(yeuCauInfo);
                    adaperYC2.add(""+listCap2.get(i).getRequestName());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class SendPostRequest extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            postData();
            return null;
        }
        private void postData() {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(linkapi.linkSendRQ);

            try {
                // Add your data

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("req_dep_code", userInfo.getDepartmentCode()));
                nameValuePairs.add(new BasicNameValuePair("req_user", userInfo.getUsername()));
                nameValuePairs.add(new BasicNameValuePair("req_system_code",sysCode));
                nameValuePairs.add(new BasicNameValuePair("req_title", title.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("pro_dep_code", ""+userInfo.getDepartmentCode()));
                nameValuePairs.add(new BasicNameValuePair("req_content", content.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("receiving_sms", checkSMS));
                nameValuePairs.add(new BasicNameValuePair("receiving_email", checkMail));
                nameValuePairs.add(new BasicNameValuePair("file_dir", ""));
                nameValuePairs.add(new BasicNameValuePair("req_status", "PHAN_CONG_XU_LY"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    Log.v(TAG, "Response: " +  responseStr);

                    // you can add an if statement here and do other actions based on the response
                }

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }
    }
}

