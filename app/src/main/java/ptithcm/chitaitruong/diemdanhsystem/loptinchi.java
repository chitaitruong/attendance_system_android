package ptithcm.chitaitruong.diemdanhsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.json.JSONException;

import ptithcm.chitaitruong.diemdanhsystem.adapter.ClassListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.RecyclerItemClickListener;
import ptithcm.chitaitruong.diemdanhsystem.helper.RetrofitClientCreator;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import retrofit2.Retrofit;

public class loptinchi extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    FloatingActionButton fab_main;
    RecyclerView recyclerView;
    TextView sample;

    ClassListAdapter mAdapter;

    ArrayList<LopTinChi> data_loptinchi = new ArrayList<>();
    Retrofit retrofit;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loptinchi);
        retrofit = RetrofitClientCreator.getClientWithInterceptor(this);
        sharedPreferences = getSharedPreferences("USER_DETAIL_SHAREDPREFERANCE", MODE_PRIVATE);
        getWindow().setEnterTransition(null);

        bottomAppBar = findViewById(R.id.bottomAppBar);
//        fab_main = findViewById(R.id.fab_main);
//        fab_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity2.this, "Them lop", Toast.LENGTH_SHORT).show();
//            }
//        });

        // realm = Realm.getDefaultInstance();

        // RealmResults<Class_Names> results;

        // results = realm.where(Class_Names.class)
        //         .findAll();


        sample = findViewById(R.id.classes_sample);
        recyclerView = findViewById(R.id.recyclerView_main);

        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        try {
            loadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        mAdapter = new ClassListAdapter(data_loptinchi);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(loptinchi.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(loptinchi.this, lichhoc.class);
                        //Intent intent = new Intent(loptinchi.this, DetailLopTinChi.class);
                        intent.putExtra("loptinchi",data_loptinchi.get(position));
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(loptinchi.this, "Long click" + data_loptinchi.get(position).getId(), Toast.LENGTH_SHORT).show();

                    }
                })
        );
        Toast.makeText(this, sharedPreferences.getString("PREFS_KEY_TOKEN",null), Toast.LENGTH_SHORT).show();
    }

    void loadData() throws IOException, JSONException {
//        LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
//        Call<ResponseBody> call = lopTinChiService.getAll();
//        final int[] code = new int[1];
//        Response<ResponseBody> response = call.execute();
//        code[0] = response.code();
//        Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
//        if (code[0] == 200) {
//            data_loptinchi = new ArrayList<>();
//            JSONArray jsonArray = new JSONArray(response.body().string());
//            int i = 0;
//            while (i<jsonArray.length()) {
//                Long id = new Long(jsonArray.getJSONObject(i).getInt("id"));
//                String mamonhoc = jsonArray.getJSONObject(i).getJSONObject("monhoc").getString("ma");
//                String monhoc = jsonArray.getJSONObject(i).getJSONObject("monhoc").getString("monhoc");
//                String namhoc = jsonArray.getJSONObject(i).getJSONObject("hocky").getString("namhoc");
//                Long hocky = new Long(jsonArray.getJSONObject(i).getJSONObject("hocky").getInt("hocky"));
//                data_loptinchi.add(new LopTinChi(id,mamonhoc,monhoc,hocky,namhoc));
//                i++;
//            }
//        } else {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//        }
        data_loptinchi.add(new LopTinChi(1L,"INT123","Mobile",2L,"2022-2023", "Nguyen Van A"));
        data_loptinchi.add(new LopTinChi(2L,"INT124","Web",2L,"2022-2023", "Nguyen Van B"));
    }

}