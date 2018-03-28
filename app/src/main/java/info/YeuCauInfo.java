package info;

/**
 * Created by Administrator on 3/6/2018.
 */

public class YeuCauInfo {
    protected int id;
    protected String requestCode, requestName, departmentCode, isEnable, isStatus;

    public YeuCauInfo(int id, String requestCode, String requestName, String departmentCode, String isEnable, String isStatus) {
        this.id = id;
        this.requestCode = requestCode;
        this.requestName = requestName;
        this.departmentCode = departmentCode;
        this.isEnable = isEnable;
        this.isStatus = isStatus;
    }
    public YeuCauInfo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }
}
