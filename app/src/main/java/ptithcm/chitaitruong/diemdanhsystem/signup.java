package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class signup extends AppCompatActivity {
    EditText et_ngaysinh;
    final Calendar c = Calendar.getInstance();
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
    }

    private void setControl() {
        et_ngaysinh = findViewById(R.id.et_ngaysinh);
    }

}