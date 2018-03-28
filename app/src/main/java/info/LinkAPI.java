package info;

/**
 * Created by Duong on 3/14/2018.
 */

public class LinkAPI {
    private String api = "http://14.160.91.174:9080";
    public String linkLogin = api+"/user/";
    public String linkHT = api+"/sys/KT";
    public String linkDepart = api+"/depart";
    public String linkStaff = api+"/staff/";
    public String linkPCXL = api+"/request/num/PHAN_CONG_XU_LY";
    public String linkDANGXL = api+"/request/num/DANG_XU_LY";
    public String linkDAXL = api+"/request/num/DA_XU_LY";
    public String linkSearchRQ = api+"/request/get?";
    public String linkForward = api+"/request/recent/";
    public String linkResponse = api+"/request/response?";
    public String linkSendRQ = api+"/request/post?";
    public String linkYeuCau = api+"/request/type?";
    public String linkCause = api+"/cause?";
    public String linkPut = api+"/request/updateRequestDetail/2?";

//    http://103.199.78.64:80 Mobifone API
//    http://14.160.91.174:9080
}
