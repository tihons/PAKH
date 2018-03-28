package info;

/**
 * Created by Duong on 3/15/2018.
 */

public class Department {
    int Id;
    String departmentCode;
    String departmentName;

    public Department(int id, String departmentCode, String departmentName) {
        Id = id;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
