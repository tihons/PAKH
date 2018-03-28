package vn.com.vhc.pakh;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.Request;

/**
 * Created by Duong on 3/15/2018.
 */

public class RequestAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Request> requestList;

    public RequestAdapter(Context context, int layout, ArrayList<Request> requestList) {
        this.context = context;
        this.layout = layout;
        this.requestList = requestList;
    }

    @Override
    public int getCount() {
        return requestList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        TextView stt = (TextView) convertView.findViewById(R.id.stt);
        TextView codeRQ = (TextView) convertView.findViewById(R.id.codeRQ);
        TextView headerRQ = (TextView) convertView.findViewById(R.id.headerRQ);
        TextView donviyc = (TextView) convertView.findViewById(R.id.donviyc);
        TextView nguoiyc = (TextView) convertView.findViewById(R.id.nguoiyc);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView donvixl = (TextView) convertView.findViewById(R.id.donvixl);
        TextView nguoixl = (TextView) convertView.findViewById(R.id.nguoixl);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        TextView deadline = (TextView) convertView.findViewById(R.id.deadline);

        Request request = requestList.get(position);

        stt.setText(request.getStt());
        codeRQ.setText(request.getTicketid());
        headerRQ.setText(request.getReq_title());
        donviyc.setText(request.getReq_dep_code());
        nguoiyc.setText(request.getReq_user());
        time.setText(request.getReq_date());
        donvixl.setText(request.getPro_dep_code());
        nguoixl.setText(request.getPro_user());
        status.setText(request.getReq_status());
        deadline.setText(request.getPro_plan());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#ffab58"));
        }

        return convertView;
    }
}
