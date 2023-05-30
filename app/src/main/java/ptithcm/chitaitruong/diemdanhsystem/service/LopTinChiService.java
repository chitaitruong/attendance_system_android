package ptithcm.chitaitruong.diemdanhsystem.service;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.UpdateDiemDanhRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.XuatFileRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LopTinChiService {
    @GET("test/giangvien/loptinchi")
    Call<ResponseBody> getAll();
    @GET("test/giangvien/loptinchi/{id}/lich")
    Call<ResponseBody> getLich(@Path("id") Long id);
    @GET("test/giangvien/loptinchi/{id}/lich/{ngayid}/diemdanh")
    Call<ResponseBody> getDiemDanh(@Path("id") Long id, @Path("ngayid") Long ngayid);
    @PUT("test/giangvien/diemdanh")
    Call<ResponseBody> updateDiemDanh(@Body UpdateDiemDanhRequest updateDiemDanhRequest);

    @POST("xuatfile")
    Call<ResponseBody> xuatfile(@Body XuatFileRequest xuatFileRequest);
}
