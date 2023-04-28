package ptithcm.chitaitruong.diemdanhsystem.interceptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
public class AuthInterceptor implements Interceptor {

    private static String token = null;
    Context context;
    SharedPreferences mPrefs;
    SharedPreferences.Editor mPrefsEdit;
    private final String PREFERENCE_NAME = "USER_DETAIL_SHAREDPREFERANCE";
    private final String PREFS_KEY_TOKEN = "PREFS_KEY_TOKEN";
    public AuthInterceptor(Context ctx) {
        this.context = ctx;
        this.mPrefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mPrefsEdit=mPrefs.edit();
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*
         * chain.request() returns original request that you can work with(modify,
         * rewrite)
         */
        Request originalRequest = chain.request();
        // Here we can rewrite the request
        // We add an Authorization header if the request is not an authorize request and already had a token
        Request authRequest = originalRequest;
        if (!originalRequest.url().toString().contains("/auth") && getToken() != null) {
            authRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + getToken())
                    .build();
        }

        /*
         * chain.proceed(request) is the call which will initiate the HTTP work. This
         * call invokes the request and returns the response as per the request.
         */
        Response response = chain.proceed(authRequest);

        // Here we can rewrite/modify the response

        return response;
    }
    private String getToken(){
        return mPrefs.getString(PREFS_KEY_TOKEN, null);
    }
}