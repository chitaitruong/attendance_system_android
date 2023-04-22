package ptithcm.chitaitruong.diemdanhsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ptithcm.chitaitruong.diemdanhsystem.adapter.NgayListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;

public class MainActivity3 extends AppCompatActivity {
    ListView listView;
    ArrayList<Ngay> ds_ngay = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        loadData();
        NgayListAdapter adapter = new NgayListAdapter(MainActivity3.this,R.layout.ngay_adapter, ds_ngay);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity3.this, ds_ngay.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        ds_ngay.add(new Ngay(1L, "27/09/2023"));
        ds_ngay.add(new Ngay(2L, "27/010/2023"));
    }

    private void setControl() {
        listView = findViewById(R.id.lvDSngay);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}