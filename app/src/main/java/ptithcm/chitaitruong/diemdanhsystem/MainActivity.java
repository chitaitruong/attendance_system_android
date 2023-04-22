package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.helper.RetrofitClientCreator;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.LoginRequest;
import ptithcm.chitaitruong.diemdanhsystem.service.AuthService;
import ptithcm.chitaitruong.diemdanhsystem.service.TestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static Retrofit retrofit;
    RelativeLayout login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }
    private void setEvent() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSignedIn()==true) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isSignedIn() {
                return true;
            }
        });
    }

    private void setControl() {
        login_button = findViewById(R.id.log_in_button);
    }

    void testLogin() throws IOException {
        AuthService authService = retrofit.create(AuthService.class);
        Call<ResponseBody> call = authService.login(new LoginRequest("admin","123456"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int responseBody = response.code();
                //JsonObject jsonObject = new JsonParser().parse(responseBody.string()).getAsJsonObject();
                Toast.makeText(MainActivity.this, String.valueOf(responseBody), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void testAll() {
        TestService testService = retrofit.create(TestService.class);
        Call<ResponseBody> call = testService.getAll();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                try {
                    Toast.makeText(MainActivity.this, responseBody.string(), Toast.LENGTH_SHORT).show();
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