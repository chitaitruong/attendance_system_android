package ptithcm.chitaitruong.diemdanhsystem.helper;

import android.content.Context;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import ptithcm.chitaitruong.diemdanhsystem.interceptor.AuthInterceptor;
import ptithcm.chitaitruong.diemdanhsystem.interceptor.LoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientCreator {
    public static final String BASE_URL = "http://192.168.1.3:8080/api/";
    public static Retrofit getClientWithInterceptor(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new AuthInterceptor(context))
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL) //This is the onlt mandatory call on Builder object.
                .client(okHttpClient) //The Htttp client to be used for requests
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // Convertor library used to convert response into POJO
                .build();
    }
}
