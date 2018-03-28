package info;

import java.io.Serializable;

/**
 * Created by Administrator on 2/7/2018.
 */

public class UserInfo implements Serializable {

    protected static int id;
    protected static String username;
    protected static String password;
    protected static String fullname;
    protected static String position;
    protected static String phone;
    protected static String gender;
    protected static String email;
    protected static String departmentCode;
    protected static String isEnable;

    public UserInfo(int id, String username, String password, String fullname, String position, String phone, String gender, String email, String departmentCode, String isEnable   ) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.position = position;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.departmentCode = departmentCode;
        this.isEnable = isEnable;

    }

    public UserInfo() {

    }

    public static void setId(int id) {
        UserInfo.id = id;
    }

    public static void setUsername(String username) {
        UserInfo.username = username;
    }

    public static void setPassword(String password) {
        UserInfo.password = password;
    }

    public static void setFullname(String fullname) {
        UserInfo.fullname = fullname;
    }

    public static void setPosition(String position) {
        UserInfo.position = position;
    }

    public static void setPhone(String phone) {
        UserInfo.phone = phone;
    }

    public static void setGender(String gender) {
        UserInfo.gender = gender;
    }

    public static void setEmail(String email) {
        UserInfo.email = email;
    }

    public static String getDepartmentCode() {
        return departmentCode;
    }

    public static void setDepartmentCode(String departmentCode) {
        UserInfo.departmentCode = departmentCode;
    }

    public static String getIsEnable() {
        return isEnable;
    }

    public static void setIsEnable(String isEnable) {
        UserInfo.isEnable = isEnable;
    }

    public static int getId() {
        return id;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getFullname() {
        return fullname;
    }

    public static String getPosition() {
        return position;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getGender() {
        return gender;
    }

    public static String getEmail() {
        return email;
    }
}

