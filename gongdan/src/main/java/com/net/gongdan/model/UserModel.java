package com.net.gongdan.model;

public class UserModel {
    private String staff_id;
    private String staff_name;
    private String sex;
    private String enable;
    private String login_id;
    private String telephone;
    private String org_id;
    private String org_name;
    private String staff_role;
    private String gd_check_limit;

    public String getStaff_id() {
        return  staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getSex() {
        return sex;
    }

    public String getEnable() {
        return enable;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getOrg_id() {
        return org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public String getStaff_role() {
        return staff_role;
    }

    public String getGd_check_limit() {
        return gd_check_limit;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public void setStaff_role(String staff_role) {
        this.staff_role = staff_role;
    }

    public void setGd_check_limit(String gd_check_limit) {
        this.gd_check_limit = gd_check_limit;
    }
}
