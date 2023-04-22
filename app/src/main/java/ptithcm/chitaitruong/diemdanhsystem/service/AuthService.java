package ptithcm.chitaitruong.diemdanhsystem.service;


import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/signin")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);
}
