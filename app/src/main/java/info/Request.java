package info;

/**
 * Created by Duong on 3/15/2018.
 */

public class Request {
    private String stt;
    private String ticketid;
    private String req_title;
    private String req_dep_code;
    private String req_user;
    private String req_date;
    private String pro_dep_code;
    private String pro_user;
    private String req_status;
    private String pro_plan;
    private String pro_actua;
    private String req_content;
    private String pro_content;
    private String req_system_code;

    public Request(String stt, String ticketid, String req_title, String req_dep_code, String req_user, String req_date, String pro_dep_code,
                   String pro_user, String req_status, String pro_plan, String pro_actua, String req_content, String pro_content, String req_system_code) {
        this.stt = stt;
        this.ticketid = ticketid;
        this.req_title = req_title;
        this.req_dep_code = req_dep_code;
        this.req_user = req_user;
        this.req_date = req_date;
        this.pro_dep_code = pro_dep_code;
        this.pro_user = pro_user;
        this.req_status = req_status;
        this.pro_plan = pro_plan;
        this.pro_actua = pro_actua;
        this.req_content = req_content;
        this.pro_content = pro_content;
        this.req_system_code = req_system_code;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getReq_title() {
        return req_title;
    }

    public void setReq_title(String req_title) {
        this.req_title = req_title;
    }

    public String getReq_dep_code() {
        return req_dep_code;
    }

    public void setReq_dep_code(String req_dep_code) {
        this.req_dep_code = req_dep_code;
    }

    public String getReq_user() {
        return req_user;
    }

    public void setReq_user(String req_user) {
        this.req_user = req_user;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getPro_dep_code() {
        return pro_dep_code;
    }

    public void setPro_dep_code(String pro_dep_code) {
        this.pro_dep_code = pro_dep_code;
    }

    public String getPro_user() {
        return pro_user;
    }

    public void setPro_user(String pro_user) {
        this.pro_user = pro_user;
    }

    public String getReq_status() {
        return req_status;
    }

    public void setReq_status(String req_status) {
        this.req_status = req_status;
    }

    public String getPro_plan() {
        return pro_plan;
    }

    public void setPro_plan(String pro_plan) {
        this.pro_plan = pro_plan;
    }

    public String getPro_actua() {
        return pro_actua;
    }

    public void setPro_actua(String pro_actua) {
        this.pro_actua = pro_actua;
    }

    public String getReq_content() {
        return req_content;
    }

    public void setReq_content(String req_content) {
        this.req_content = req_content;
    }

    public String getPro_content() {
        return pro_content;
    }

    public void setPro_content(String pro_content) {
        this.pro_content = pro_content;
    }

    public String getReq_system_code() {
        return req_system_code;
    }

    public void setReq_system_code(String req_system_code) {
        this.req_system_code = req_system_code;
    }
}
