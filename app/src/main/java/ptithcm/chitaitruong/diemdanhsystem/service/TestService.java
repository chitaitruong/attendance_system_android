package ptithcm.chitaitruong.diemdanhsystem.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET("test/all")
    Call<ResponseBody> getAll();
}
