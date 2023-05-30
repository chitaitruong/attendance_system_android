package ptithcm.chitaitruong.diemdanhsystem.service;


import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.LoginRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.SendOTPRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.SignUpRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.UpdatePassRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.VerifyOTPRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/signin")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);
    @POST("auth/sendOTP")
    Call<ResponseBody> sendOTP(@Body SendOTPRequest sendOTPRequest);
    @POST("auth/verifyOTP")
    Call<ResponseBody> verifyOTP(@Body VerifyOTPRequest verifyOTPRequest);
    @POST("auth/capnhatmatkhau")
    Call<ResponseBody> capnhatmatkhau(@Body UpdatePassRequest updatePassRequest);
    @POST("auth/signup")
    Call<ResponseBody> signup(@Body SignUpRequest signUpRequest);
    @POST("auth/kichhoat")
    Call<ResponseBody> kichhoat(@Body VerifyOTPRequest verifyOTPRequest);
}
