package ptithcm.chitaitruong.diemdanhsystem.model;

import java.io.Serializable;

public class LopTinChi implements Serializable {
    private Long id;
    private String mamonhoc;
    private String monhoc;
    private Long hocky;
    private String namhoc;

    public LopTinChi(Long id, String mamonhoc, String monhoc, Long hocky, String namhoc) {
        this.id = id;
        this.mamonhoc = mamonhoc;
        this.monhoc = monhoc;
        this.hocky = hocky;
        this.namhoc = namhoc;
    }

    public String getMamonhoc() {
        return mamonhoc;
    }

    public void setMamonhoc(String mamonhoc) {
        this.mamonhoc = mamonhoc;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMonhoc() {
        return monhoc;
    }
    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }
    public Long getHocky() {
        return hocky;
    }
    public void setHocky(Long hocky) {
        this.hocky = hocky;
    }
    public String getNamhoc() {
        return namhoc;
    }
    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }
    
}
