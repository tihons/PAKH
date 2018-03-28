package info;

import java.util.Date;

/**
 * Created by Administrator on 3/16/2018.
 */

public class ContentShortInfo {

    private int id, ticketid;
    private String fw_date;
    private String receiving_date;
    private String dateline;
    private String actualy_finish;
    private String created_date;
    private String fw_dep_code, fw_user, fw_content, receiving_dep_code,
            receiving_user, return_content, return_content_private,
            dic_cause_id, dic_cause_id_private, file_id;

    public ContentShortInfo() {
        this.id = id;
        this.ticketid = ticketid;
        this.fw_dep_code = fw_dep_code;
        this.fw_user = fw_user;
        this.fw_date = fw_date;
        this.fw_content = fw_content;
        this.receiving_date = receiving_date;
        this.receiving_dep_code = receiving_dep_code;
        this.receiving_user = receiving_user;
        this.dateline = dateline;
        this.actualy_finish = actualy_finish;
        this.return_content = return_content;
        this.return_content_private = return_content_private;
        this.created_date = created_date;
        this.dic_cause_id = dic_cause_id;
        this.dic_cause_id_private = dic_cause_id_private;
        this.file_id = file_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getFw_date() {
        return fw_date;
    }

    public void setFw_date(String fw_date) {
        this.fw_date = fw_date;
    }

    public String getReceiving_date() {
        return receiving_date;
    }

    public void setReceiving_date(String receiving_date) {
        this.receiving_date = receiving_date;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getActualy_finish() {
        return actualy_finish;
    }

    public void setActualy_finish(String actualy_finish) {
        this.actualy_finish = actualy_finish;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getFw_dep_code() {
        return fw_dep_code;
    }

    public void setFw_dep_code(String fw_dep_code) {
        this.fw_dep_code = fw_dep_code;
    }

    public String getFw_user() {
        return fw_user;
    }

    public void setFw_user(String fw_user) {
        this.fw_user = fw_user;
    }

    public String getFw_content() {
        return fw_content;
    }

    public void setFw_content(String fw_content) {
        this.fw_content = fw_content;
    }

    public String getReceiving_dep_code() {
        return receiving_dep_code;
    }

    public void setReceiving_dep_code(String receiving_dep_code) {
        this.receiving_dep_code = receiving_dep_code;
    }

    public String getReceiving_user() {
        return receiving_user;
    }

    public void setReceiving_user(String receiving_user) {
        this.receiving_user = receiving_user;
    }

    public String getReturn_content() {
        return return_content;
    }

    public void setReturn_content(String return_content) {
        this.return_content = return_content;
    }

    public String getReturn_content_private() {
        return return_content_private;
    }

    public void setReturn_content_private(String return_content_private) {
        this.return_content_private = return_content_private;
    }

    public String getDic_cause_id() {
        return dic_cause_id;
    }

    public void setDic_cause_id(String dic_cause_id) {
        this.dic_cause_id = dic_cause_id;
    }

    public String getDic_cause_id_private() {
        return dic_cause_id_private;
    }

    public void setDic_cause_id_private(String dic_cause_id_private) {
        this.dic_cause_id_private = dic_cause_id_private;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    @Override
    public String toString() {
        return "ContentShortInfo{" +
                "id=" + id +
                ", ticketid=" + ticketid +
                ", fw_date='" + fw_date + '\n' +
                ", receiving_date='" + receiving_date + '\n' +
                ", dateline='" + dateline + '\n' +
                ", actualy_finish='" + actualy_finish + '\n' +
                ", created_date='" + created_date + '\n' +
                ", fw_dep_code='" + fw_dep_code + '\n' +
                ", fw_user='" + fw_user + '\n' +
                ", fw_content='" + fw_content + '\n' +
                ", receiving_dep_code='" + receiving_dep_code + '\n' +
                ", receiving_user='" + receiving_user + '\n' +
                ", return_content='" + return_content + '\n' +
                ", return_content_private='" + return_content_private + '\n' +
                ", dic_cause_id='" + dic_cause_id + '\n' +
                ", dic_cause_id_private='" + dic_cause_id_private + '\n' +
                ", file_id='" + file_id + '\n' +
                '}';
    }
}
