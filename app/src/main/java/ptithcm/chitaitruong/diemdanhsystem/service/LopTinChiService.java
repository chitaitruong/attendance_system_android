package ptithcm.chitaitruong.diemdanhsystem.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LopTinChiService {
    @GET("test/giangvien/loptinchi")
    Call<ResponseBody> getAll();
}
