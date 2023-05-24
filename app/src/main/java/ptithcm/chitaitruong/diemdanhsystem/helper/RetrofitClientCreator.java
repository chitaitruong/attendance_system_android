package ptithcm.chitaitruong.diemdanhsystem.helper;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import ptithcm.chitaitruong.diemdanhsystem.interceptor.AuthInterceptor;
import ptithcm.chitaitruong.diemdanhsystem.interceptor.LoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientCreator {
    public static final String BASE_URL = "http://10.252.5.98:8080/api/";
    public static Retrofit getClientWithInterceptor(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new AuthInterceptor(context))
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(60, TimeUnit.SECONDS);
//        builder.readTimeout(60, TimeUnit.SECONDS);
//        builder.writeTimeout(60, TimeUnit.SECONDS);
//        okHttpClient = builder.build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL) //This is the onlt mandatory call on Builder object.
                .client(okHttpClient) //The Htttp client to be used for requests
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // Convertor library used to convert response into POJO
                .build();
    }
}
