package ptithcm.chitaitruong.diemdanhsystem.model;

import java.io.Serializable;

public class DiemDanh implements Serializable {
    private Long sinhvienId;
    private String maSv;
    private String hoTen;
    private Long trangThai;
    private String ghiChu;
    private String ngayCapNhat;
    private String ngayDiemDiemDanh;

    @Override
    public String toString() {
        return "DiemDanh{" +
                "sinhvienId=" + sinhvienId +
                ", maSv='" + maSv + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", trangThai=" + trangThai +
                ", ghiChu='" + ghiChu + '\'' +
                ", ngayCapNhat='" + ngayCapNhat + '\'' +
                ", ngayDiemDiemDanh='" + ngayDiemDiemDanh + '\'' +
                '}';
    }

    public DiemDanh(Long sinhvienId, String maSv, String hoTen, Long trangThai, String ghiChu, String ngayCapNhat, String ngayDiemDiemDanh) {
        this.sinhvienId = sinhvienId;
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.ngayCapNhat = ngayCapNhat;
        this.ngayDiemDiemDanh = ngayDiemDiemDanh;
    }

    public Long getSinhvienId() {
        return sinhvienId;
    }

    public void setSinhvienId(Long sinhvienId) {
        this.sinhvienId = sinhvienId;
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

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getNgayDiemDiemDanh() {
        return ngayDiemDiemDanh;
    }

    public void setNgayDiemDiemDanh(String ngayDiemDiemDanh) {
        this.ngayDiemDiemDanh = ngayDiemDiemDanh;
    }
}
