package ptithcm.chitaitruong.attendance_system.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ptithcm.chitaitruong.attendance_system.R;

public class LoginActivity extends AppCompatActivity {

    EditText edt_username, edt_password;
    RelativeLayout login_button;
    TextView tv_signup;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();
    }
    private void setControl() {
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        tv_signup = findViewById(R.id.tv_signup);
        login_button = findViewById(R.id.login_button);
    }
    private void setEvent() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}