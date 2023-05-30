package ptithcm.chitaitruong.diemdanhsystem.payload.request;

public class SendOTPRequest {
    private String email;
    private String info;

    public SendOTPRequest(String email, String info) {
        this.email = email;
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
