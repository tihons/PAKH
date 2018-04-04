package vn.com.vhc.pakh;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import info.LinkAPI;
import info.Request;
import info.UserInfo;

public class ShowUserPRQ extends AppCompatActivity {

    ListView requestView;
    RequestAdapter rqAdapter;
    LayoutInflater inflater;

    ArrayList<Request> rqList;
    ArrayList<String> rqUserList = new ArrayList<String>();

    LinkAPI linkapi = new LinkAPI();
    String startReqDate, endReqDate, reqTitle, reqSystemCode, reqDepCode,
            reqUser, proDepCode, proUser, ticketId, reqStatus, linkToSearch, sdt;

    ImageButton infoRQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_request);

        dataSearch();

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestView = (ListView) findViewById(R.id.requestView);
        rqList = new ArrayList<Request>();

        new SearchRequest().execute(linkToSearch);

        View header = inflater.inflate(R.layout.show_request_header, null);
        requestView.addHeaderView(header);
        header.setBackgroundColor(Color.parseColor("#30336b"));

        infoRQ = (ImageButton) findViewById(R.id.infoRequest);
        infoRQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlerDialog();
            }
        });

        requestView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ticketid = rqList.get(position-1).getTicketid();
                String ID = rqList.get(position-1).getStt();
                String reqUser = rqList.get(position-1).getReq_user();
                String reqTitle = rqList.get(position-1).getReq_title();
                String reqDate = rqList.get(position-1).getReq_date();

                new Read_UserInfo().execute(linkapi.linkUser+reqUser);

                Intent intent = new Intent(ShowUserPRQ.this, XuLy.class);
                intent.putExtra("TicketID", ticketid);
                intent.putExtra("ID", ID);
                intent.putExtra("reqUser", reqUser);
                intent.putExtra("reqTitle", reqTitle);
                intent.putExtra("phone", sdt);
                intent.putExtra("ReqDate", reqDate);

                ShowUserPRQ.this.startActivity(intent);
            }
        });
    }

    private void dataSearch() {
        startReqDate = getIntent().getExtras().getString("fromtime");
        endReqDate = getIntent().getExtras().getString("totime");
        reqTitle = getIntent().getExtras().getString("tieude");
        reqSystemCode = getIntent().getExtras().getString("hethong");
        reqDepCode = getIntent().getExtras().getString("donvigui");
        reqUser = getIntent().getExtras().getString("nguoigui");
        proDepCode = getIntent().getExtras().getString("donvixuly");
        proUser = getIntent().getExtras().getString("nguoixuly");
        ticketId = getIntent().getExtras().getString("mayeucau");
        reqStatus = getIntent().getExtras().getString("trangthai");

        linkToSearch = linkapi.linkSearchRQ+"start_req_date="+startReqDate+"&end_req_date="+endReqDate;
        if(!reqTitle.equals("")&&!reqTitle.equals(null)) {
            linkToSearch = linkToSearch+"&req_title="+reqTitle;
        }
        if (!reqSystemCode.equals("")&&!reqSystemCode.equals(null)&&!reqSystemCode.equals("Tất cả")) {
            linkToSearch = linkToSearch+"&req_system_code="+reqSystemCode;
        }
        if (!reqDepCode.equals("")&&!reqDepCode.equals(null)&&!reqDepCode.equals("Tất cả")) {
            linkToSearch = linkToSearch+"&req_dep_code="+reqDepCode;
        }
        if (!reqUser.equals("")&&!reqUser.equals(null)&&!reqUser.equals("Tất cả")) {
            linkToSearch = linkToSearch+"&req_user="+reqUser;
        }
        if (!proDepCode.equals("")&&!proDepCode.equals(null)) {
            linkToSearch = linkToSearch+"&pro_dep_code="+proDepCode;
        }
        if (!proUser.equals("")&&!proUser.equals(null)&&!proUser.equals("Tất cả")) {
            linkToSearch = linkToSearch+"&pro_user="+proUser;
        }
        if (!ticketId.equals("")&&!ticketId.equals(null)) {
            linkToSearch = linkToSearch+"&ticket_id="+ticketId;
        }
        if (!reqStatus.equals("")&&!reqStatus.equals(null)&&!reqStatus.equals("Tất cả")) {
            linkToSearch = linkToSearch+"&req_status="+reqStatus;
        }
    }

    private class SearchRequest extends AsyncTask<String, Void, String> {

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
            getlistSearchRQ(s);
            if (rqList.size()==0) {
                Toast.makeText(getApplicationContext(), "Không có yêu cầu nào.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getlistSearchRQ(String s){
        try {
            JSONArray jsonAr = new JSONArray(s);
            for (int i = 0; i < jsonAr.length(); i++){
                JSONObject obj = jsonAr.getJSONObject(i);
                String stt = obj.getString("id");
                String ticketid = obj.getString("ticket_id");
                String reqTitle = obj.getString("req_title");
                String reqDepCode = obj.getString("req_dep_code");
                String reqUser = obj.getString("req_user");
                String reqDate = obj.getString("req_date");
                String proDepCode = obj.getString("pro_dep_code");
                String proUser = obj.getString("pro_user");
                String reqStatus = obj.getString("req_status");
                String proPlan = obj.getString("pro_plan");
                String proActua = obj.getString("pro_actua");
                String reqContent = obj.getString("req_content");
                String proContent = obj.getString("pro_content");
                String reqSysCode = obj.getString("req_system_code");

                Request request = new Request(stt, ticketid, reqTitle, reqDepCode, reqUser, reqDate,
                        proDepCode, proUser, reqStatus, proPlan, proActua, reqContent, proContent, reqSysCode);
                rqList.add(request);
                rqAdapter = new RequestAdapter(getApplicationContext(), R.layout.show_rqchild, rqList);
                requestView.setAdapter(rqAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
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

            UserInfo userInfo = new UserInfo(id, username, password, fullname, position, phone, gender, email, departmentCode, isEnable);
            sdt = userInfo.getPhone();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    private void displayAlerDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.note_request, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setNegativeButton("Ẩn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
