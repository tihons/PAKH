package info;

/**
 * Created by Duong on 3/14/2018.
 */

public class LinkAPI {
    private String api = "http://103.199.78.64:80";
    public String linkLogin = api+"/user?";
    public String linkUser = api+"/user/";
    public String linkHT = api+"/sys/KT";
    public String linkDepart = api+"/depart";
    public String linkStaff = api+"/staff/";
    public String linkStatusNumber = api+"/request/num/";
    public String linkSearchRQ = api+"/request/get?";
    public String linkForward = api+"/request/recent/";
    public String linkResponse = api+"/request/response?";
    public String linkSendRQ = api+"/request/post?";
    public String linkYeuCau = api+"/request/type?";
    public String linkCause = api+"/cause?";
    public String linkPutRequestDetail = api+"/request/updateRequestDetail/";
    public String linkPutRequest = api+"/request/updateRequest/";
    public String linkProcesser = api+"/process/";

//    http://103.199.78.64:80 Mobifone API
//    http://14.160.91.174:9080
}
