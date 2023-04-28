package ptithcm.chitaitruong.diemdanhsystem;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.LoginRequest;
import ptithcm.chitaitruong.diemdanhsystem.service.AuthService;
import ptithcm.chitaitruong.diemdanhsystem.service.TestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signin extends AppCompatActivity {

    private Retrofit retrofit;
    RelativeLayout login_button;
    EditText et_username, et_password;
    TextView tv_signup;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sharedPreferences = getSharedPreferences("USER_DETAIL_SHAREDPREFERANCE",MODE_PRIVATE);
        //retrofit = RetrofitClientCreator.getClientWithInterceptor(this);
        retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.2.22:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setControl();
        setEvent();
    }
    private void setEvent() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    validateLogin(et_username.getText().toString(),et_password.getText().toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin.this, signup.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        login_button = findViewById(R.id.log_in_button);
        tv_signup = findViewById(R.id.tv_sign_up);
    }

    void validateLogin(String username, String password) throws IOException, JSONException {
        AuthService authService = retrofit.create(AuthService.class);
        Call<ResponseBody> call = authService.login(new LoginRequest(username,password));
        final int[] code = new int[1];
        Response<ResponseBody> response = call.execute();
        code[0] = response.code();
        if (code[0]==200) {
            JSONObject jsonObject = new JSONObject(response.body().string());
            sharedPreferences.edit().putString("PREFS_KEY_TOKEN",jsonObject.getString("accessToken")).apply();
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
            JSONArray jArray = jsonObject.getJSONArray("roles");
            boolean isAdmin = false;
            if (jArray != null) {
                for (int i=0;i<jArray.length();i++){
                    if (jArray.getString(i).equals("ROLE_ADMIN")) {
                        isAdmin = true;
                        break;
                    }
                }
            }
            if (isAdmin) {
                Intent intent = new Intent(signin.this, quanlyloptinchi.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(signin.this, loptinchi.class);
                startActivity(intent);
                finish();
            }
        } else if (code[0] == 401){
            Toast.makeText(signin.this, "Username or password is invalid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(signin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        //for test
//        boolean isAdmin = true;
//        if (isAdmin) {
//            Intent intent = new Intent(signin.this, quanlyloptinchi.class);
//            startActivity(intent);
//            finish();
//        } else {
//            Intent intent = new Intent(signin.this, loptinchi.class);
//            startActivity(intent);
//            finish();
//        }
    }
    void testAll() {
        TestService testService = retrofit.create(TestService.class);
        Call<ResponseBody> call = testService.getAll();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                try {
                    Toast.makeText(signin.this, responseBody.string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}