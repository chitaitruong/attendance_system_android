package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Objects;

import ptithcm.chitaitruong.diemdanhsystem.adapter.DiemDanhListApater;
import ptithcm.chitaitruong.diemdanhsystem.adapter.NgayListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.SinhVienListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;
import ptithcm.chitaitruong.diemdanhsystem.model.SinhVien;

public class DetailLopTinChi extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView hocky_detail, total_sinhvien, giangvien_detail;
    ImageView imageView;
    ArrayList<SinhVien> ds_sinhvien = new ArrayList<>();
    LopTinChi lopTinChi;
    SinhVienListAdapter sinhVienListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lop_tin_chi);
        Toolbar toolbar = findViewById(R.id.toolbar_class_detail3);
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
        giangvien_detail.setText("GV: " + lopTinChi.getGiangvien());
        loadData();
        total_sinhvien.setText("Total students: " + ds_sinhvien.size());
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        sinhVienListAdapter = new SinhVienListAdapter(ds_sinhvien);
        recyclerView.setAdapter(sinhVienListAdapter);
    }

    private void loadData() {
        ds_sinhvien.add(new SinhVien(1L,"N19DCAT067","Truong Chi Tai"));
        ds_sinhvien.add(new SinhVien(2L,"N19DCAT068","Truong Chi Thong"));
        ds_sinhvien.add(new SinhVien(3L,"N19DCAT069","Truong Quynh Hoa"));
        ds_sinhvien.add(new SinhVien(4L,"N19DCAT070","Truong Van Minh"));
        ds_sinhvien.add(new SinhVien(5L,"N19DCAT071","Le Thi Phuong"));
    }

    private void setControl() {
        total_sinhvien = findViewById(R.id.total_students_detail);
        hocky_detail = findViewById(R.id.hocky_detail);
        giangvien_detail = findViewById(R.id.giangvien_detail);
        recyclerView = findViewById(R.id.recyclerView_detail);
        imageView = findViewById(R.id.image_disease_detail);
    }
}