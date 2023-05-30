package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.SendOTPRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.SignUpRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.VerifyOTPRequest;
import ptithcm.chitaitruong.diemdanhsystem.service.AuthService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signup extends AppCompatActivity {
    TextView tv;
    EditText et_ngaysinh, et_email, et_username, et_password, et_hoten, et_sodienthoai, et_diachi, et_otp;
    LinearLayout guilaiotp;
    RelativeLayout bt_dangky, username, password, hoten, email, sodienthoai, diachi, ngaysinh, bt_xacthucotp, otp;
    RadioGroup radiogroup;
    RadioButton radioButtonSV;
    final Calendar c = Calendar.getInstance();
    CountDownTimer cTimer = null;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.1.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setControl();
        setEvent();
        setControl();
        setEvent();

    }

    private void setEvent() {
        et_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        et_ngaysinh.setText(i2 + "/" + (i1+1)  + "/" + i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        bt_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dangky();
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
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
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guiotp();
            }
        });
    }

    private void dangky() throws IOException, JSONException {
        if (TextUtils.isEmpty(et_username.getText().toString()) || TextUtils.isEmpty(et_password.getText().toString()) || TextUtils.isEmpty(et_hoten.getText().toString())
                || TextUtils.isEmpty(et_email.getText().toString()) || TextUtils.isEmpty(et_sodienthoai.getText().toString()) || TextUtils.isEmpty(et_diachi.getText().toString()) || TextUtils.isEmpty(et_ngaysinh.getText().toString())) {
            Toast.makeText(this, "Missing information", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<String> roles = new ArrayList<>();
            if (radioButtonSV.isChecked()) {
                roles.add("user");
            } else {
                roles.add("teacher");
            }
            AuthService authService = retrofit.create(AuthService.class);
            Call<ResponseBody> call = authService.signup(new SignUpRequest(!radioButtonSV.isChecked(), et_username.getText().toString(), et_password.getText().toString(),et_email.getText().toString(),et_sodienthoai.getText().toString(),et_ngaysinh.getText().toString(),et_diachi.getText().toString(),et_hoten.getText().toString(), roles));
            final int[] code = new int[1];
            Response<ResponseBody> response = call.execute();
            if (response == null){
                Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
            }
            code[0] = response.code();
            if (code[0] == 200) {
                guiotp();
                bt_dangky.setVisibility(View.GONE);
                username.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                hoten.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                ngaysinh.setVisibility(View.GONE);
                radiogroup.setVisibility(View.GONE);
                diachi.setVisibility(View.GONE);
                sodienthoai.setVisibility(View.GONE);
                //
                guilaiotp.setVisibility(View.VISIBLE);
                bt_xacthucotp.setVisibility(View.VISIBLE);
                otp.setVisibility(View.VISIBLE);
            } else if (code[0] == 400) {
                System.out.println(response.body().string());
                JSONObject jsonObject = new JSONObject(response.body().string());
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void setControl() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_hoten = findViewById(R.id.et_hoten);
        et_email = findViewById(R.id.et_email);
        et_sodienthoai = findViewById(R.id.et_sodienthoai);
        et_diachi = findViewById(R.id.et_diachi);
        et_username = findViewById(R.id.et_username);
        et_ngaysinh = findViewById(R.id.et_ngaysinh);
        et_otp = findViewById(R.id.et_otp);
        bt_dangky = findViewById(R.id.sign_up_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        sodienthoai = findViewById(R.id.sodienthoai);
        diachi = findViewById(R.id.diachi);
        hoten = findViewById(R.id.hoten);
        ngaysinh = findViewById(R.id.ngaysinh);
        radiogroup = findViewById(R.id.radioGroup);
        radioButtonSV = findViewById(R.id.radio_sinhvien);
        tv = findViewById(R.id.timer);
        guilaiotp = findViewById(R.id.guilaiotp);
        bt_xacthucotp = findViewById(R.id.bt_xacthucotp);
        otp = findViewById(R.id.otp);
    }
    private void guiotp() {
        //sendOTP
        AuthService authService = retrofit.create(AuthService.class);
        Call<ResponseBody> call = authService.sendOTP(new SendOTPRequest(et_email.getText().toString(), "Active account"));
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
        Call<ResponseBody> call = authService.kichhoat(new VerifyOTPRequest(et_email.getText().toString(), et_otp.getText().toString()));
        final int[] code = new int[1];
        Response<ResponseBody> response = call.execute();
        code[0] = response.code();
        if (code[0] == 200) {
            isValid = true;
        }
        if (isValid) {
            Toast.makeText(this, "Account is actived", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(signup.this, signin.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Mã xác thực không chính xác!", Toast.LENGTH_SHORT).show();
        }
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