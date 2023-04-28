package ptithcm.chitaitruong.diemdanhsystem.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private Long id;
    private String maSv;
    private String hoTen;

    public SinhVien(Long id, String maSv, String hoTen) {
        this.id = id;
        this.maSv = maSv;
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id=" + id +
                ", maSv='" + maSv + '\'' +
                ", hoTen='" + hoTen + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
