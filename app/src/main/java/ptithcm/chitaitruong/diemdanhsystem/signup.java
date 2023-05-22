package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class signup extends AppCompatActivity {
    TextView tv;
    EditText et_ngaysinh;
    LinearLayout guilaiotp;
    RelativeLayout bt_dangky, username, password, hoten, email, sodienthoai, diachi, ngaysinh, bt_xacthucotp, otp;
    RadioGroup radiogroup;
    final Calendar c = Calendar.getInstance();
    CountDownTimer cTimer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
                dangky();
                guiotp();
            }
        });
        bt_xacthucotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xacthucotp();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guiotp();
            }
        });
    }

    private void dangky() {
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
    }


    private void setControl() {
        et_ngaysinh = findViewById(R.id.et_ngaysinh);
        bt_dangky = findViewById(R.id.sign_up_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        sodienthoai = findViewById(R.id.sodienthoai);
        diachi = findViewById(R.id.diachi);
        hoten = findViewById(R.id.hoten);
        ngaysinh = findViewById(R.id.ngaysinh);
        radiogroup = findViewById(R.id.radioGroup);
        tv = findViewById(R.id.timer);
        guilaiotp = findViewById(R.id.guilaiotp);
        bt_xacthucotp = findViewById(R.id.bt_xacthucotp);
        otp = findViewById(R.id.otp);
    }
    private void guiotp() {
        startTimer();
    }

    private void xacthucotp() {
        boolean isValid = false;
        if (isValid) {
            Intent intent = new Intent(signup.this, signin.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Mã xác thực không chính xác!", Toast.LENGTH_SHORT).show();
        }
    }
    void startTimer() {
        tv.setClickable(false);
        cTimer = new CountDownTimer(30000, 1000) {
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