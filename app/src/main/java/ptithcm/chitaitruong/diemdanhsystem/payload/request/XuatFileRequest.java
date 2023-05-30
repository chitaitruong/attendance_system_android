package ptithcm.chitaitruong.diemdanhsystem.payload.request;

public class XuatFileRequest {
    private Long ltc_id;
    private String file_name;

    public XuatFileRequest(Long ltc_id, String file_name) {
        this.ltc_id = ltc_id;
        this.file_name = file_name;
    }
}
