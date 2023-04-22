package ptithcm.chitaitruong.diemdanhsystem.model;

import java.io.Serializable;
import java.util.Date;

public class Ngay implements Serializable{
    private Long id;
    private String ngay;
    
    public Ngay(Long id, String ngay) {
        this.id = id;
        this.ngay = ngay;
    }
    public Long getId() {
        return id;  
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNgay() {
        return ngay;
    }
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "Ngay{" +
                "id=" + id +
                ", ngay='" + ngay + '\'' +
                '}';
    }
}
