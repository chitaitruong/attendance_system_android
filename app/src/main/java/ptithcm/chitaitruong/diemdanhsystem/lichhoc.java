package ptithcm.chitaitruong.diemdanhsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Objects;

import ptithcm.chitaitruong.diemdanhsystem.adapter.NgayListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.RecyclerItemClickListener;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;

public class lichhoc extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView hocky_detail;
    ImageView imageView;
    ArrayList<Ngay> ds_ngay = new ArrayList<>();
    LopTinChi lopTinChi;
    NgayListAdapter ngayListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichhoc);
        getWindow().setExitTransition(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar_class_detail1);
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
        loadData();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        ngayListAdapter = new NgayListAdapter(ds_ngay);
        recyclerView.setAdapter(ngayListAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(lichhoc.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(lichhoc.this, diemdanh.class);
                        intent.putExtra("loptinchi",getIntent().getSerializableExtra("loptinchi"));
                        intent.putExtra("ngay",ds_ngay.get(position));
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(lichhoc.this, "Long click" + ds_ngay.get(position).getId(), Toast.LENGTH_SHORT).show();

                    }
                })
        );
    }

    private void loadData() {
        ds_ngay.add(new Ngay(1L, "27/03/2023"));
        ds_ngay.add(new Ngay(1L, "27/04/2023"));
        ds_ngay.add(new Ngay(2L, "27/10/2023"));
        ds_ngay.add(new Ngay(3L, "27/11/2023"));
    }

    private void setControl() {
        imageView = (ImageView) findViewById(R.id.image_disease_detail);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_detail);
        hocky_detail = (TextView) findViewById(R.id.hocky_detail);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//        startActivity(intent);
//        return super.onOptionsItemSelected(item);
//    }

}