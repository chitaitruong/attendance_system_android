package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Objects;

import ptithcm.chitaitruong.diemdanhsystem.adapter.DiemDanhListApater;
import ptithcm.chitaitruong.diemdanhsystem.model.DiemDanh;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;

public class diemdanh extends AppCompatActivity implements DiemDanhListApater.RecyclerViewActionListener {
    RecyclerView recyclerView;
    TextView hocky_detail, total_student_detail, ngay_detail;
    ImageView imageView;
    Spinner spinner;
    ArrayList<DiemDanh> ds_diemdanh = new ArrayList<>();
    ArrayList<DiemDanh> tam = new ArrayList<>();
    String[] items = new String[]{"All", "Present", "Late", "Absent"};
    DiemDanhListApater diemDanhListApater;
    LopTinChi lopTinChi;
    Ngay ngay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diemdanh);
        getWindow().setExitTransition(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar_class_detail);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_class_menu, menu);
        return true;
    }
    private void setEvent() {

        lopTinChi = (LopTinChi) getIntent().getSerializableExtra("loptinchi");
        ngay = (Ngay) getIntent().getSerializableExtra("ngay");
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_disease_detail);
        collapsingToolbarLayout.setTitle(lopTinChi.getMamonhoc() + " - " + lopTinChi.getMonhoc());
        switch (lopTinChi.getId().intValue()%6) {
            case 0:
                imageView.setImageResource(R.drawable.asset_bg_paleblue);
                break;
            case 1:
                imageView.setImageResource(R.drawable.asset_bg_green);
                break;
            case 2:
                imageView.setImageResource(R.drawable.asset_bg_yellow);
                break;
            case 3:
                imageView.setImageResource(R.drawable.asset_bg_palegreen);
                break;
            case 4:
                imageView.setImageResource(R.drawable.asset_bg_paleorange);
                break;
            case 5:
                imageView.setImageResource(R.drawable.asset_bg_white);
                break;
        }
        hocky_detail.setText("Học kỳ " + lopTinChi.getHocky() + " Năm học " + lopTinChi.getNamhoc());
        loadData();
        tam.addAll(ds_diemdanh);
        total_student_detail.setText("Total students: " + ds_diemdanh.size());

        ngay_detail.setText("Ngày: " + ngay.getNgay());
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        diemDanhListApater = new DiemDanhListApater(ds_diemdanh,this);
        recyclerView.setAdapter(diemDanhListApater);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ds_diemdanh.clear();
                if (items[i].equals("Present")) {
                    for (DiemDanh diemDanh : tam) {
                        if (diemDanh.getTrangThai() == 1L) {
                            ds_diemdanh.add(diemDanh);
                        }
                        diemDanhListApater.notifyDataSetChanged();
                    }

                } else  if (items[i].equals("Late")) {
                    for (DiemDanh diemDanh : tam) {
                        if (diemDanh.getTrangThai() == 2L) {
                            ds_diemdanh.add(diemDanh);
                        }
                        diemDanhListApater.notifyDataSetChanged();
                    }
                } else  if (items[i].equals("Absent")) {
                    for (DiemDanh diemDanh : tam) {
                        if (diemDanh.getTrangThai() == 0L) {
                            ds_diemdanh.add(diemDanh);
                        }
                        diemDanhListApater.notifyDataSetChanged();
                    }
                } else {
                    ds_diemdanh.addAll(tam);
                    diemDanhListApater.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) {
        switch (clickedViewId){
            case R.id.layout_click1:
                ds_diemdanh.get(clickedItemPosition).setTrangThai(1L);
                diemDanhListApater.notifyDataSetChanged();
                break;
            case R.id.radio_present:
                if (ds_diemdanh.get(clickedItemPosition).getTrangThai() != 1L) {
                    ds_diemdanh.get(clickedItemPosition).setTrangThai(1L);
                    diemDanhListApater.notifyDataSetChanged();
                }
                break;
            case R.id.radio_absent:
                if (ds_diemdanh.get(clickedItemPosition).getTrangThai() != 0L) {
                    ds_diemdanh.get(clickedItemPosition).setTrangThai(0L);
                    diemDanhListApater.notifyDataSetChanged();
                }
                break;
            case R.id.radio_late:
                if (ds_diemdanh.get(clickedItemPosition).getTrangThai() != 2L) {
                    ds_diemdanh.get(clickedItemPosition).setTrangThai(2L);
                    diemDanhListApater.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onViewLongClicked(int clickedViewId, int clickedItemPosition) {
        switch (clickedViewId){
            case R.id.layout_click1:
                Toast.makeText(this, "Long click" + ds_diemdanh.get(clickedItemPosition).toString(), Toast.LENGTH_SHORT).show();
                break;
        }

    }
    private void loadData() {
        ds_diemdanh.add(new DiemDanh(1L, "N19DCAT067","Truong Chi Tai",1L,"","",""));
        ds_diemdanh.add(new DiemDanh(2L, "N19DCAT068","Truong Chi ABC",0L,"","",""));
        ds_diemdanh.add(new DiemDanh(3L, "N19DCAT069","Truong Chi XYZ",2L,"","",""));
//        ds_diemdanh.add(new DiemDanh(1L, "N19DCAT067","Truong Chi Tai",1L,"","",""));
//        ds_diemdanh.add(new DiemDanh(2L, "N19DCAT068","Truong Chi ABC",0L,"","",""));
//        ds_diemdanh.add(new DiemDanh(3L, "N19DCAT069","Truong Chi XYZ",2L,"","",""));
//        ds_diemdanh.add(new DiemDanh(1L, "N19DCAT067","Truong Chi Tai",1L,"","",""));
//        ds_diemdanh.add(new DiemDanh(2L, "N19DCAT068","Truong Chi ABC",0L,"","",""));
//        ds_diemdanh.add(new DiemDanh(3L, "N19DCAT069","Truong Chi XYZ",2L,"","",""));
//        ds_diemdanh.add(new DiemDanh(1L, "N19DCAT067","Truong Chi Tai",1L,"","",""));
//        ds_diemdanh.add(new DiemDanh(2L, "N19DCAT068","Truong Chi ABC",0L,"","",""));
//        ds_diemdanh.add(new DiemDanh(3L, "N19DCAT069","Truong Chi XYZ",2L,"","",""));
//        ds_diemdanh.add(new DiemDanh(1L, "N19DCAT067","Truong Chi Tai",1L,"","",""));
//        ds_diemdanh.add(new DiemDanh(2L, "N19DCAT068","Truong Chi ABC",0L,"","",""));
//        ds_diemdanh.add(new DiemDanh(3L, "N19DCAT069","Truong Chi XYZ",2L,"","",""));
//
    }

    private void setControl() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_detail);
        total_student_detail = (TextView) findViewById(R.id.total_students_detail);
        hocky_detail = (TextView) findViewById(R.id.hocky_detail);
        ngay_detail = (TextView) findViewById(R.id.ngay_detail);
        imageView = (ImageView) findViewById(R.id.image_disease_detail);
        spinner = (Spinner) findViewById(R.id.spinner1);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
//        startActivity(intent);
//        return super.onOptionsItemSelected(item);
//    }
}