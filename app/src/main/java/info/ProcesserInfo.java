package info;

public class ProcesserInfo {

    protected static String departmentCode;
    protected static String proUser;

    public ProcesserInfo(String departmentCode, String proUser) {
        this.departmentCode = departmentCode;
        this.proUser = proUser;
    }
    public ProcesserInfo(){

    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getProUser() {
        return proUser;
    }

    public void setProUser(String proUser) {
        this.proUser = proUser;
    }
}
