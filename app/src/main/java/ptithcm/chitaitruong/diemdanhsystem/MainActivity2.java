package ptithcm.chitaitruong.diemdanhsystem;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import ptithcm.chitaitruong.diemdanhsystem.adapter.ClassListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.RecyclerItemClickListener;
import ptithcm.chitaitruong.diemdanhsystem.databinding.ActivityMain2Binding;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;

public class MainActivity2 extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    FloatingActionButton fab_main;
    RecyclerView recyclerView;
    TextView sample;

    ClassListAdapter mAdapter;

    ArrayList<LopTinChi> data_loptinchi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setEnterTransition(null);

        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab_main = findViewById(R.id.fab_main);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Them lop", Toast.LENGTH_SHORT).show();
            }
        });

        // realm = Realm.getDefaultInstance();

        // RealmResults<Class_Names> results;

        // results = realm.where(Class_Names.class)
        //         .findAll();


        sample = findViewById(R.id.classes_sample);
        recyclerView = findViewById(R.id.recyclerView_main);

        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        loadData();
        mAdapter = new ClassListAdapter(data_loptinchi);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity2.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                        intent.putExtra("idlop",data_loptinchi.get(position).getMamonhoc());
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(MainActivity2.this, "Long click" + data_loptinchi.get(position).getId(), Toast.LENGTH_SHORT).show();

                    }
                })
        );
    }

    void loadData() {
        data_loptinchi = new ArrayList<>();
        data_loptinchi.add(new LopTinChi(1L,"INT10202","Ky thuat giau tin",2L,"2022-2023"));
        data_loptinchi.add(new LopTinChi(2L,"INT10203","Kiem thu xam nhap",2L,"2022-2023"));
    }

}