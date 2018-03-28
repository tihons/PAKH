package vn.com.vhc.pakh;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.http.RequestQueue;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.ContentShortInfo;
import info.DictionnaryCauseInfo;
import info.LinkAPI;
import info.SystemTypeInfo;
import info.UserInfo;
import info.YeuCauInfo;
import okhttp3.Request;
import okhttp3.Response;

public class XuLy extends AppCompatActivity {
    public static final String TAG = XuLy.class.getSimpleName();

    UserInfo userInfo = new UserInfo();
    ContentShortInfo  contentShortInfo = new ContentShortInfo();

    URL urlUpdateDetail = null;

    private int id, ordering;
    private String causeCode, causeName, isEnable,createdBy, isParent,depCode, isStatus, systemCode,idHas;

    Button btnOver, btnChuyenTiep;
    EditText content, contentPrivate;

    TextView muc1, muc2, noidung1, noidung2, goneRequestDetail;

    String ticketid, ID;

    DictionnaryCauseInfo dictionnaryCauseInfo;

    JSONArray arrayCase1, arrayCase2;
    ArrayAdapter<String> adapterCause1, adapterCause2;
    ArrayList<DictionnaryCauseInfo> listCause1 = new ArrayList<DictionnaryCauseInfo>();
    ArrayList<DictionnaryCauseInfo> listCause2 = new ArrayList<DictionnaryCauseInfo>();

    Spinner spnCause1, spnCause2;

    String dic_code_id_private, dic_code_id;

    LinearLayout mainLayout;
    LinkAPI linkapi = new LinkAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xu_ly);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ticketid = getIntent().getExtras().getString("TicketID");
        ID = getIntent().getExtras().getString("ID");
        final LinearLayout layoutForward = (LinearLayout) findViewById(R.id.layoutForward);

        new ReadJSONForward().execute(linkapi.linkForward+ticketid);
        TextView textViewShow = (TextView) findViewById(R.id.shortContent);
        final TextView textViewGoneDetail = (TextView)  findViewById(R.id.textViewGoneDetail);
        muc1 = (TextView) findViewById(R.id.truoc1);
        muc1 = (TextView) findViewById(R.id.truoc2);
        noidung1 = (TextView) findViewById(R.id.contentForward1);
        noidung2 = (TextView) findViewById(R.id.contentForward2);
        btnOver = (Button) findViewById(R.id.btnOver);
        btnChuyenTiep = (Button) findViewById(R.id.btnChuyenTiep);

        content =  (EditText) findViewById(R.id.noidungXL);
        contentPrivate =  (EditText) findViewById(R.id.noidungXL2);

        spnCause1 = (Spinner) findViewById(R.id.spNNNYCC1);
        spnCause2 = (Spinner) findViewById(R.id.spNNNYCC2);

        textViewShow.setPaintFlags(textViewShow.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);



        adapterCause1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapterCause1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCause1.setAdapter(adapterCause1);
        new ReadJSONObjectCause1().execute(linkapi.linkCause);

        adapterCause2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapterCause2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCause2.setAdapter(adapterCause2);

        new ReadJSONObjectCause1().execute(linkapi.linkCause+"level=1");
        spnCause1.setSelection(0);
        spnCause1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                adapterCause2.clear();
                dic_code_id = listCause1.get(i).getCauseCode();
                int id = listCause1.get(i).getId();
                new ReadJSONObjectCause2().execute(linkapi.linkCause+"level=2&id_parent="+id);

            }
        });

        spnCause2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                dic_code_id_private = listCause2.get(i).getCauseCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnOver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//                    urlUpdateDetail = new URL("http://14.160.91.174:9080/request/updateRequestDetail/2?");
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                DefaultHttpClient client = new DefaultHttpClient();
//                HttpPut put= new HttpPut(String.valueOf(urlUpdateDetail));
//
//                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//                pairs.add(new BasicNameValuePair("receiving_date", "06-03-2018"));
//                pairs.add(new BasicNameValuePair("receiving_dep_code", "xxx"));
//                pairs.add(new BasicNameValuePair("receiving_user", "thuongnv"));
//                pairs.add(new BasicNameValuePair("actualy_finish", "07-03-2018"));
//                pairs.add(new BasicNameValuePair("return_content", "Hom nay toi chuyen nha"));
//                pairs.add(new BasicNameValuePair("return_content_private", "Dia diem la toi nay"));
//                pairs.add(new BasicNameValuePair("dic_cause_id", ""));
//                pairs.add(new BasicNameValuePair("dic_cause_id_private", ""));
//                pairs.add(new BasicNameValuePair("file_id", ""));

//                try {
//                    put.setEntity(new UrlEncodedFormEntity(pairs));
//                    HttpResponse response = client.execute(put);
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                RequestQueue queue;
//                queue = Volley.newRequestQueue(this);
//
//                String url = "http://14.160.91.174:9080/request/updateRequestDetail/2?";
//
//                StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
//                        new Response.Listener<String>()
//                        {
//                            @Override
//                            public void onResponse(String response) {
//                                // response
//                                Log.d("Response", response);
//                            }
//                        },
//                        new Response.ErrorListener()
//                        {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Log.d("Error.Response", response);
//                            }
//                        }
//                ) {
//
//                    @Override
//                    protected Map<String, String> getParams()
//                    {
//                        Map<String, String>  params = new HashMap<String, String> ();
//                        params.put("parameter1", "value1");
//                        params.put("parameter2", "value2");
//
//                        return params;
//                    }
//
//                };
////
//                queue.add(putRequest);

                new PutRequest().execute(linkapi.linkPut);
            }
        });

        layoutForward.setVisibility(View.GONE);
        textViewGoneDetail.setVisibility(View.GONE);
        textViewShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                layoutForward.setVisibility(View.VISIBLE);
                textViewGoneDetail.setVisibility(View.VISIBLE);


            }
        });

        textViewGoneDetail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutForward.setVisibility(View.GONE);
                textViewGoneDetail.setVisibility(View.GONE);
            }
        });

        btnChuyenTiep.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new ResponseRequest().execute(linkapi.linkResponse);
                if (content.length() == 0 || contentPrivate.length() == 0){
//                    new AddRequest().showAlertDialogNullContent();
                    showAlertDialogNullContent();
                }else{
                    Intent intent = new Intent(XuLy.this, ShowRequest.class );
                    XuLy.this.startActivity(intent);
                }

            }
        });

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

    private class ReadJSONObjectCause1 extends AsyncTask<String, Void, String> {

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
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            try {
                arrayCase1 = new JSONArray(s);

                for (int i = 0; i < arrayCase1.length(); i ++){
                    JSONObject object = arrayCase1.getJSONObject(i);

                    dictionnaryCauseInfo = new DictionnaryCauseInfo();

                    causeCode = object.getString("causeCode");
                    causeName = object.getString("causeName");
                    isEnable = object.getString("isEnable");
                    ordering = object.getInt("ordering");
                    createdBy = object.getString("createdBy");
                    isParent = object.getString("isParent");
                    idHas = object.getString("idHas");
                    depCode = object.getString("depCode");
                    isStatus = object.getString("isStatus");
                    systemCode = object.getString("systemCode");
                    id = object.getInt("id");

                    dictionnaryCauseInfo.setCauseCode(causeCode);
                    dictionnaryCauseInfo.setCauseName(causeName);
                    dictionnaryCauseInfo.setIsEnable(isEnable);
                    dictionnaryCauseInfo.setOrdering(ordering);
                    dictionnaryCauseInfo.setCreateBy(createdBy);
                    dictionnaryCauseInfo.setIsParent(isParent);
                    dictionnaryCauseInfo.setIdHas(idHas);
                    dictionnaryCauseInfo.setDepCode(depCode);
                    dictionnaryCauseInfo.setIsStatus(isStatus);
                    dictionnaryCauseInfo.setSystemCode(systemCode);
                    dictionnaryCauseInfo.setId(id);

                    listCause1.add(dictionnaryCauseInfo);
                    adapterCause1.add(""+listCause1.get(i).getCauseName());
//                    Log.e(TAG, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                    Log.e(TAG, ""+listCause1.get(i).getCauseName());
//                    Toast.makeText(getApplicationContext(), listCause1.get(i).getId()+"-"+listCause1.get(i).getCauseName(), Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class ReadJSONObjectCause2 extends AsyncTask<String, Void, String> {

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
                arrayCase2 = new JSONArray(s);
                listCause2 = new ArrayList<DictionnaryCauseInfo>();

                for (int i = 0; i < arrayCase2.length(); i ++){
                    JSONObject object = arrayCase2.getJSONObject(i);

                    dictionnaryCauseInfo = new DictionnaryCauseInfo();

                    causeCode = object.getString("causeCode");
                    causeName = object.getString("causeName");
                    isEnable = object.getString("isEnable");
                    ordering = object.getInt("ordering");
                    createdBy = object.getString("createdBy");
                    isParent = object.getString("isParent");
                    idHas = object.getString("idHas");
                    depCode = object.getString("depCode");
                    isStatus = object.getString("isStatus");
                    systemCode = object.getString("systemCode");
                    id = object.getInt("id");

                    dictionnaryCauseInfo.setCauseCode(causeCode);
                    dictionnaryCauseInfo.setCauseName(causeName);
                    dictionnaryCauseInfo.setIsEnable(isEnable);
                    dictionnaryCauseInfo.setOrdering(ordering);
                    dictionnaryCauseInfo.setCreateBy(createdBy);
                    dictionnaryCauseInfo.setIsParent(isParent);
                    dictionnaryCauseInfo.setIdHas(idHas);
                    dictionnaryCauseInfo.setDepCode(depCode);
                    dictionnaryCauseInfo.setIsStatus(isStatus);
                    dictionnaryCauseInfo.setSystemCode(systemCode);
                    dictionnaryCauseInfo.setId(id);

                    listCause2.add(dictionnaryCauseInfo);
                    adapterCause2.add(""+listCause2.get(i).getCauseName());
                    Log.e(TAG, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    Log.e(TAG, ""+listCause2.get(i).getCauseName());
//                    Toast.makeText(getApplicationContext(), listCause1.get(i).getId()+"-"+listCause1.get(i).getCauseName(), Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    class ReadJSONForward extends AsyncTask<String, Void, String> {

        int id, ticketid;
        String fw_dep_code, fw_user, fw_content, receiving_dep_code,
                receiving_user, return_content, return_content_private,
                dic_cause_id, dic_cause_id_private, file_id;
        String fw_date;
        String receiving_date;
        String dateline;
        String actualy_finish;
        String created_date;

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL (strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
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

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject object = new JSONObject(s);

                id = object.getInt("id");
                ticketid = object.getInt("ticketid");
                fw_dep_code = object.getString("fw_dep_code");
                fw_user = object.getString("fw_user");
                fw_date = object.getString("fw_date");
                fw_content = object.getString("fw_content");
                receiving_date = object.getString("receiving_date");
                receiving_dep_code = object.getString("receiving_dep_code");
                receiving_user = object.getString("receiving_user");
                dateline  = object.getString("dateline");
                actualy_finish  = object.getString("actualy_finish");
                return_content  = object.getString("return_content");
                return_content_private  = object.getString("return_content_private");
                created_date  = object.getString("created_date");
                dic_cause_id  = object.getString("dic_cause_id");
                dic_cause_id_private  = object.getString("dic_cause_id_private");
                file_id  = object.getString("file_id");

                contentShortInfo.setId(id);
                contentShortInfo.setTicketid(ticketid);
                contentShortInfo.setFw_dep_code(fw_dep_code);
                contentShortInfo.setFw_user(fw_user);
                contentShortInfo.setFw_date(fw_date);
                contentShortInfo.setFw_content(fw_content);
                contentShortInfo.setReceiving_date(receiving_date);
                contentShortInfo.setReceiving_dep_code(receiving_dep_code);
                contentShortInfo.setReceiving_user(receiving_user);
                contentShortInfo.setDateline(dateline);
                contentShortInfo.setActualy_finish(actualy_finish);
                contentShortInfo.setReturn_content(return_content);
                contentShortInfo.setReturn_content_private(return_content_private);
                contentShortInfo.setCreated_date(created_date);
                contentShortInfo.setDic_cause_id(dic_cause_id);
                contentShortInfo.setDic_cause_id_private(dic_cause_id_private);
                contentShortInfo.setFile_id(file_id);

//                Toast.makeText(getApplicationContext(), contentShortInfo.getId(), Toast.LENGTH_LONG).show();

                Log.e(TAG, "ooooooooooooooooooo: "+contentShortInfo.toString());

                muc1.setText(""+contentShortInfo.getDic_cause_id());
//                muc2.setText(""+contentShortInfo.getDic_cause_id_private());
                noidung1.setText(""+contentShortInfo.getReturn_content());
                noidung2.setText(""+contentShortInfo.getReturn_content_private());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public class ResponseRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            postData();
            return null;
        }
        private void postData() {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(linkapi.linkResponse);

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("ticketid", ticketid));
                nameValuePairs.add(new BasicNameValuePair("fw_dep_code", contentShortInfo.getFw_dep_code()));// phong ban forward
                nameValuePairs.add(new BasicNameValuePair("fw_user",contentShortInfo.getFw_user()));// nguoi forward
                nameValuePairs.add(new BasicNameValuePair("fw_content", contentShortInfo.getFw_content()));
//                nameValuePairs.add(new BasicNameValuePair("receiving_date", ""+userInfo.getDepartmentCode()));//ngay nhan request
                nameValuePairs.add(new BasicNameValuePair("receiving_dep_code", userInfo.getDepartmentCode()));// phong ban nhan request
                nameValuePairs.add(new BasicNameValuePair("receiving_user","Thuc Ok" ));// nguoi nhan request
                nameValuePairs.add(new BasicNameValuePair("return_content", content.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("return_content_private", contentPrivate.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("dic_cause_id", ""+dic_code_id));
                nameValuePairs.add(new BasicNameValuePair("dic_cause_id_private", ""+dic_code_id_private));
                nameValuePairs.add(new BasicNameValuePair("file_id", ""));
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

    public class PutRequest extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            postData();
            return null;
        }
        private void postData() {



        }
    }
}

