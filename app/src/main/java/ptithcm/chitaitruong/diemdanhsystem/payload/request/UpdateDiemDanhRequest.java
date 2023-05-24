package ptithcm.chitaitruong.diemdanhsystem.payload.request;

public class UpdateDiemDanhRequest {
    private Long sinhvien_id;
    private Long ngay_id;
    private Long loptinchi_id;
    private String ghi_chu;
    private Long trang_thai;

    public UpdateDiemDanhRequest(Long sinhvien_id, Long ngay_id, Long loptinchi_id, String ghi_chu, Long trang_thai) {
        this.sinhvien_id = sinhvien_id;
        this.ngay_id = ngay_id;
        this.loptinchi_id = loptinchi_id;
        this.ghi_chu = ghi_chu;
        this.trang_thai = trang_thai;
    }

    public Long getSinhvien_id() {
        return sinhvien_id;
    }

    public void setSinhvien_id(Long sinhvien_id) {
        this.sinhvien_id = sinhvien_id;
    }

    public Long getNgay_id() {
        return ngay_id;
    }

    public void setNgay_id(Long ngay_id) {
        this.ngay_id = ngay_id;
    }

    public Long getLoptinchi_id() {
        return loptinchi_id;
    }

    public void setLoptinchi_id(Long loptinchi_id) {
        this.loptinchi_id = loptinchi_id;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public Long getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(Long trang_thai) {
        this.trang_thai = trang_thai;
    }
}
