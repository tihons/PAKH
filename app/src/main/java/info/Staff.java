package info;

/**
 * Created by Duong on 3/15/2018.
 */

public class Staff {
    int Id;
    String Username;
    String Password;
    String Fullname;
    String Position;
    String Phone;
    String Gender;
    String Email;
    String departmentCode;
    String isEnable;

    public Staff(int id, String username, String password, String fullname, String position, String phone, String gender, String email, String departmentCode, String isEnable) {
        Id = id;
        Username = username;
        Password = password;
        Fullname = fullname;
        Position = position;
        Phone = phone;
        Gender = gender;
        Email = email;
        this.departmentCode = departmentCode;
        this.isEnable = isEnable;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
}
