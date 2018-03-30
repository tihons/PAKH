package info;

/**
 * Created by Administrator on 3/12/2018.
 */

public class SystemInfo {
    String sysCode;
    String sysName;

    public SystemInfo(String sysCode, String sysName) {
        this.sysCode = sysCode;
        this.sysName = sysName;
    }

    public SystemInfo(){

    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
