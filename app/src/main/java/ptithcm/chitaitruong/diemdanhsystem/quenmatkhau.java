package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.LoginRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.SendOTPRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.UpdatePassRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.VerifyOTPRequest;
import ptithcm.chitaitruong.diemdanhsystem.service.AuthService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class quenmatkhau extends AppCompatActivity {

    TextView tv;
    LinearLayout guilaiotp;
    RelativeLayout bt_guiotp, bt_xacthucotp, email, otp, bt_capnhatmatkhau, matkhau1, matkhau2;
    EditText et_email, et_otp, et_matkhau1, et_matkhau2;
    CountDownTimer cTimer = null;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.1.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setControl();
        setEvent();
    }
    private void setEvent() {
        bt_guiotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
                if ((!TextUtils.isEmpty(et_email.getText().toString()))) {
                    bt_guiotp.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    otp.setVisibility(View.VISIBLE);
                    bt_xacthucotp.setVisibility(View.VISIBLE);
                    guilaiotp.setVisibility(View.VISIBLE);
                    try {
                        guiotp();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(quenmatkhau.this, "Mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_xacthucotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    xacthucotp();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ;
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    guiotp();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        bt_capnhatmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
                if (et_matkhau1.getText().toString().matches(pattern)) {
                    if (et_matkhau1.getText().toString().equals(et_matkhau2.getText().toString())) {
                        AuthService authService = retrofit.create(AuthService.class);
                        Call<ResponseBody> call = authService.capnhatmatkhau(new UpdatePassRequest(et_email.getText().toString(), et_matkhau1.getText().toString()));
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(quenmatkhau.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(quenmatkhau.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent intent = new Intent(quenmatkhau.this, signin.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(quenmatkhau.this, "Password must contain at least one digit [0-9].\n" +
                            "Password must contain at least one lowercase Latin character [a-z].\n" +
                            "Password must contain at least one uppercase Latin character [A-Z].\n" +
                            "Password must contain at least one special character like ! @ # & ( ).\n" +
                            "Password must contain a length of at least 8 characters and a maximum of 20 characters.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void guiotp() throws IOException {
        AuthService authService = retrofit.create(AuthService.class);
        Call<ResponseBody> call = authService.sendOTP(new SendOTPRequest(et_email.getText().toString(), "Quên mật khẩu"));
        final int[] code = new int[1];
//        Response<ResponseBody> response = call.execute();
//        code[0] = response.code();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        startTimer();
    }

    private void xacthucotp() throws IOException {
        boolean isValid = false;
        AuthService authService = retrofit.create(AuthService.class);
        Call<ResponseBody> call = authService.verifyOTP(new VerifyOTPRequest(et_email.getText().toString(), et_otp.getText().toString()));
        final int[] code = new int[1];
        Response<ResponseBody> response = call.execute();
        code[0] = response.code();
        if (code[0] == 200) {
            isValid = true;
        }
        if (isValid) {
            otp.setVisibility(View.GONE);
            bt_xacthucotp.setVisibility(View.GONE);
            matkhau1.setVisibility(View.VISIBLE);
            matkhau2.setVisibility(View.VISIBLE);
            bt_capnhatmatkhau.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Mã xác thực không chính xác!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setControl() {
        bt_guiotp = findViewById(R.id.guiotp);
        bt_xacthucotp = findViewById(R.id.bt_xacthucotp);
        email = findViewById(R.id.email);
        otp = findViewById(R.id.otp);
        et_email = findViewById(R.id.et_email);
        et_otp = findViewById(R.id.et_otp);
        et_matkhau1 = findViewById(R.id.et_matkhau1);
        et_matkhau2 = findViewById(R.id.et_matkhau2);
        matkhau1 = findViewById(R.id.matkhau1);
        matkhau2 = findViewById(R.id.matkhau2);
        bt_capnhatmatkhau = findViewById(R.id.bt_capnhatmatkhau);
        tv = findViewById(R.id.timer);
        guilaiotp = findViewById(R.id.guilaiotp);
    }
    void startTimer() {
        tv.setClickable(false);
        cTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv.setText("Gửi lại OTP sau " +String.valueOf(millisUntilFinished/1000));
            }
            public void onFinish() {
                tv.setText("Gửi lại OTP");
                tv.setClickable(true);
            }
        };
        cTimer.start();
    }
}