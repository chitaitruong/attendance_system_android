package ptithcm.chitaitruong.diemdanhsystem.payload.request;

import java.util.ArrayList;

public class SignUpRequest {
    private Boolean isGiangVien;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String ngaysinh;
    private String address;
    private String hoten;
    private ArrayList<String> role;

    public Boolean getGiangVien() {
        return isGiangVien;
    }

    public void setGiangVien(Boolean giangVien) {
        isGiangVien = giangVien;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRoles(ArrayList<String> roles) {
        this.role = roles;
    }

    public SignUpRequest(Boolean isGiangVien, String username, String password, String email, String phone, String ngaysinh, String address, String hoten, ArrayList<String> roles) {
        this.isGiangVien = isGiangVien;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.ngaysinh = ngaysinh;
        this.address = address;
        this.hoten = hoten;
        this.role = roles;
    }
}
