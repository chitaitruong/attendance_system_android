package ptithcm.chitaitruong.diemdanhsystem.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String hoten;
    private String email;
    private String phone;
    private String diachi;

    public User(String username, String hoten, String email, String phone, String diachi) {
        this.username = username;
        this.hoten = hoten;
        this.email = email;
        this.phone = phone;
        this.diachi = diachi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
