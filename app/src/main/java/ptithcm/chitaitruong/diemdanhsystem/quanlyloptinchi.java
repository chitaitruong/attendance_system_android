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
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import ptithcm.chitaitruong.diemdanhsystem.adapter.DiemDanhListApater;
import ptithcm.chitaitruong.diemdanhsystem.adapter.MonHocListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.RecyclerItemClickListener;
import ptithcm.chitaitruong.diemdanhsystem.model.DiemDanh;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.MonHoc;

public class quanlyloptinchi extends AppCompatActivity implements MonHocListAdapter.RecyclerViewActionListener {

    ArrayList<LopTinChi> ds_loptinchi = new ArrayList<>();
    BottomAppBar bottomAppBar;
    MonHocListAdapter monHocListAdapter;
    RecyclerView recyclerView;
    FloatingActionButton fab_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlyloptinchi);
        getWindow().setExitTransition(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab_main = findViewById(R.id.fab_main);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(quanlyloptinchi.this, "Them lop tin chi", Toast.LENGTH_SHORT).show();
            }
        });
        setControl();
        setEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_class_menu, menu);
        return true;
    }

    private void setEvent() {
        loadData();
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        monHocListAdapter = new MonHocListAdapter(ds_loptinchi, this);
        recyclerView.setAdapter(monHocListAdapter);
    }

    private void loadData() {
        ds_loptinchi.add(new LopTinChi(1L,"INT123","Mobile",2L,"2022-2023","Nguyen Van A"));
        ds_loptinchi.add(new LopTinChi(2L,"INT124","Web",2L,"2022-2023","Nguyen Van B"));

    }

    private void setControl() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_detail);
    }
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) {
        switch (clickedViewId){
            case R.id.layout_click2:
                Intent intent = new Intent(quanlyloptinchi.this, DetailLopTinChi.class);
                //Intent intent = new Intent(loptinchi.this, DetailLopTinChi.class);
                intent.putExtra("loptinchi",ds_loptinchi.get(clickedItemPosition));
                startActivity(intent);                break;
            case R.id.update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onViewLongClicked(int clickedViewId, int clickedItemPosition) {

    }
}