package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class forgotpass extends AppCompatActivity {

    TextView tv;
    LinearLayout guilaiotp;
    RelativeLayout bt_guiotp, bt_xacthucotp, email, otp, bt_capnhatmatkhau, matkhau1, matkhau2;
    EditText et_email, et_otp, et_matkhau1, et_matkhau2;
    CountDownTimer cTimer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        setControl();
        setEvent();
    }
    private void setEvent() {
        bt_guiotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_guiotp.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                otp.setVisibility(View.VISIBLE);
                bt_xacthucotp.setVisibility(View.VISIBLE);
                guilaiotp.setVisibility(View.VISIBLE);
                guiotp();
            }
        });
        bt_xacthucotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xacthucotp();
            }
        });
        ;
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guiotp();
            }
        });
        bt_capnhatmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgotpass.this, signin.class);
                startActivity(intent);
            }
        });
    }

    private void guiotp() {
        startTimer();
    }

    private void xacthucotp() {
        boolean isValid = false;
        if (isValid) {
            otp.setVisibility(View.GONE);
            bt_xacthucotp.setVisibility(View.GONE);
            matkhau1.setVisibility(View.VISIBLE);
            matkhau2.setVisibility(View.VISIBLE);
            bt_capnhatmatkhau.setVisibility(View.VISIBLE);
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