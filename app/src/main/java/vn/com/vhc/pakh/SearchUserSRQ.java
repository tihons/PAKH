package vn.com.vhc.pakh;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import info.Department;
import info.LinkAPI;
import info.Staff;
import info.SystemInfo;
import info.UserInfo;

public class SearchUserSRQ extends AppCompatActivity {

    ArrayAdapter<String> hethongAdapter, donvixulyAdapter, nguoixulyAdapter, trangthaiAdapter;

    Spinner hethongSpinner, donvixulySpinner, nguoixulySpinner, trangthaiSpinner;
    ImageButton addRequest, infoSearch;
    Button search;
    EditText mayeucau, tieude;
    TextView tvFromTime, tvToTime, tvPCXL, tvDANGXL, tvDAXL, userTV, userDepartTV;

    ArrayList<String> arrayJsondata = new ArrayList<String>();
    ArrayList<SystemInfo> hethongList = new ArrayList<SystemInfo>();
    ArrayList<Department> donvixulyList = new ArrayList<Department>();
    ArrayList<Staff> nguoixulyList = new ArrayList<Staff>();
    String [] trangthaiList = {"Tất cả", "PHAN_CONG_XU_LY", "DANG_XU_LY", "DA_XU_LY"};

    UserInfo userInfo = new UserInfo();
    LinkAPI linkapi = new LinkAPI();
    String linkstaff, PCXL, DANGXL, DAXL;
    String departCodegui = "";
    String departCodexuly = "";
    String username, userDepartcode, systemCode;

    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user_srq);

        username = userInfo.getUsername();
        userDepartcode = userInfo.getDepartmentCode();

        addView();
        Date();
        addAdapter();

        new ReadJSONObject().execute();
        hethongAdapter.notifyDataSetChanged();
        ClickEvents();
    }

    private void addView() {
        tieude = (EditText) findViewById(R.id.tieudeS);
        hethongSpinner = (Spinner) findViewById(R.id.hethong);
        userTV = (TextView) findViewById(R.id.donvigui);
        userDepartTV = (TextView) findViewById(R.id.nguoigui);
        donvixulySpinner = (Spinner) findViewById(R.id.donvixuly);
        nguoixulySpinner = (Spinner) findViewById(R.id.nguoixuly);
        tvFromTime = (TextView) findViewById(R.id.fromtime);
        tvToTime = (TextView) findViewById(R.id.totime);
        mayeucau = (EditText) findViewById(R.id.mayeucau);
        trangthaiSpinner = (Spinner) findViewById(R.id.trangthai);
        addRequest = (ImageButton) findViewById(R.id.add_request);
        infoSearch = (ImageButton) findViewById(R.id.btn_Info);

        search = (Button) findViewById(R.id.search);

        userTV.setText(username);
        userDepartTV.setText(userDepartcode);
    }

    private void addAdapter() {
        hethongAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        hethongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hethongSpinner.setAdapter(hethongAdapter);
        hethongAdapter.add("Tất cả");

        donvixulyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        donvixulyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donvixulySpinner.setAdapter(donvixulyAdapter);
        donvixulyAdapter.add("Tất cả");

        nguoixulyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        nguoixulyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nguoixulySpinner.setAdapter(nguoixulyAdapter);
        nguoixulyAdapter.add("Tất cả");

        trangthaiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trangthaiList);
        trangthaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trangthaiSpinner.setAdapter(trangthaiAdapter);
    }

    private void Date() {
        Calendar currentDate;
        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);

        month = month + 1;
        tvFromTime.setText(day+"-"+month+"-"+year);
        tvToTime.setText(day+"-"+month+"-"+year);
    }

    private void ClickEvents() {
        tvFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchUserSRQ.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        tvFromTime.setText(dayOfMonth+"-"+month+"-"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        tvToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchUserSRQ.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        tvToTime.setText(dayOfMonth+"-"+month+"-"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        hethongSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    systemCode = hethongList.get(position).getSysCode();
                } else {
                    systemCode = hethongSpinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        donvixulySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nguoixulyAdapter.clear();
                nguoixulyAdapter.add("Tất cả");

                String dc = null;
                if (position!=0) {
                    dc = donvixulyList.get(position).getDepartmentCode();
                }
                linkstaff = linkapi.linkStaff + dc;
//                departCodexuly = dc;

                new Read_clickDVXuly().execute(linkstaff);

                nguoixulyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchUserSRQ.this, ShowUserSRQ.class);
                intent.putExtra("fromtime", tvFromTime.getText().toString());
                intent.putExtra("totime", tvToTime.getText().toString());
                intent.putExtra("tieude", tieude.getText().toString());
                intent.putExtra("hethong", systemCode);
                intent.putExtra("donvigui", userDepartcode);
                intent.putExtra("nguoigui", username);
                intent.putExtra("donvixuly", donvixulySpinner.getSelectedItem().toString());
                intent.putExtra("nguoixuly", nguoixulySpinner.getSelectedItem().toString());
                intent.putExtra("mayeucau", mayeucau.getText().toString());
                intent.putExtra("trangthai", trangthaiSpinner.getSelectedItem().toString());
                SearchUserSRQ.this.startActivity(intent);
            }
        });

        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUserSRQ.this, AddRequest.class);
                SearchUserSRQ.this.startActivity(intent);
            }
        });

        infoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlerDialog();
            }
        });
    }

    private class ReadJSONObject extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            String [] api = {linkapi.linkHT, linkapi.linkDepart, linkapi.linkStatusNumber+"PHAN_CONG_XU_LY",
                    linkapi.linkStatusNumber+"DANG_XU_LY", linkapi.linkStatusNumber+"DA_XU_LY"};

            for (int i = 0; i < api.length; i++) {
                arrayJsondata.add(docNoiDung_Tu_URL(api[i]));
            }

            return arrayJsondata;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            getlistHethong();
            getlistDonvixuly();
            PCXL = arrayJsondata.get(2);
            DANGXL = arrayJsondata.get(3);
            DAXL = arrayJsondata.get(4);
        }
    }

    private class Read_clickDVXuly extends AsyncTask<String, Void, String> {

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
            getlistNguoixuly(s);
        }
    }

    private void getlistHethong(){
        try {
            JSONArray jsonAr = new JSONArray(arrayJsondata.get(0));
            for (int i = 0; i < jsonAr.length(); i++){
                JSONObject obj = jsonAr.getJSONObject(i);
                String sysCode = obj.getString("systemCode");
                String sysName = obj.getString("systemName");
                SystemInfo systemInfo = new SystemInfo(sysCode, sysName);
                hethongList.add(systemInfo);
                hethongAdapter.add(hethongList.get(i).getSysName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getlistDonvixuly(){
        try {
            donvixulyList = new ArrayList<Department>();

            JSONArray jsonAr = new JSONArray(arrayJsondata.get(1));
            for (int i = 0; i < jsonAr.length(); i++){
                JSONObject obj = jsonAr.getJSONObject(i);
                Integer Id = obj.getInt("id");
                String departmentCode = obj.getString("departmentCode");
                String departmentName = obj.getString("departmentName");
                Department dpm = new Department(Id, departmentCode, departmentName);
                donvixulyList.add(dpm);
                donvixulyAdapter.add(donvixulyList.get(i).getDepartmentCode());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String getlistNguoixuly(String s){
        try {
            nguoixulyList.clear();
            JSONArray jsonAr = new JSONArray(s);
            for (int i = 0; i < jsonAr.length(); i++){
                JSONObject obj = jsonAr.getJSONObject(i);
                Integer Id = obj.getInt("id");
                String username = obj.getString("username");
                String password = obj.getString("password");
                String fullname = obj.getString("fullname");
                String position = obj.getString("position");
                String phone = obj.getString("phone");
                String gender = obj.getString("gender");
                String email = obj.getString("email");
                String departmentCode = obj.getString("departmentCode");
                String isenable = obj.getString("isEnable");
                Staff staff = new Staff(Id, username, password, fullname, position, phone, gender, email, departmentCode, isenable);
                nguoixulyList.add(staff);
                nguoixulyAdapter.add(nguoixulyList.get(i).getUsername());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayJsondata.get(0);
    }

    private void displayAlerDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.info_search, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        tvPCXL = (TextView) alertLayout.findViewById(R.id.pcXL);
        tvDANGXL = (TextView) alertLayout.findViewById(R.id.dangXL);
        tvDAXL = (TextView) alertLayout.findViewById(R.id.daXL);
        tvPCXL.setText(PCXL);
        tvDANGXL.setText(DANGXL);
        tvDAXL.setText(DAXL);
        alert.setNegativeButton("Ẩn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
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
}