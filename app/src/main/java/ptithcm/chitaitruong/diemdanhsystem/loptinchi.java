package ptithcm.chitaitruong.diemdanhsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.adapter.ClassListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.RecyclerItemClickListener;
import ptithcm.chitaitruong.diemdanhsystem.helper.RetrofitClientCreator;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.service.LopTinChiService;
import retrofit2.Call;
import retrofit2.Response;
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
    Toolbar toolbar;
    NavigationView navigationView;
    DangNhapFragment dangNhapFragment = null;
    SettingFragment settingFragment = null;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loptinchi);
        retrofit = RetrofitClientCreator.getClientWithInterceptor(this);
        sharedPreferences = getSharedPreferences("USER_DETAIL_SHAREDPREFERANCE", MODE_PRIVATE);
        getWindow().setEnterTransition(null);

        //bottomAppBar = findViewById(R.id.bottomAppBar);
//        fab_main = findViewById(R.id.fab_main);
//        fab_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity2.this, "Them lop", Toast.LENGTH_SHORT).show();
//            }
//        });

        //toolbar = findViewById(R.id.toolbar_beginner);
        sample = findViewById(R.id.classes_sample);
        recyclerView = findViewById(R.id.recyclerView_main);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerLayout);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.toString().equals("Đăng xuất")) {
//                    dangxuat();
//                } else {
//                    xemthongtintaikhoan();
//                }
//                return false;
//            }
//        });
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
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.hoten);
        nav_user.setText(getIntent().getStringExtra("username").toString());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.mnDangXuat:
                        /*Toast.makeText(loptinchi.this, "Dang nhap", Toast.LENGTH_SHORT).show();
                        if (dangNhapFragment == null) {
                            dangNhapFragment = new DangNhapFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.Content, dangNhapFragment).commit();
                        */
                        dangxuat();
                        break;
                    case R.id.mnTaiKhoan:
                        /*Toast.makeText(loptinchi.this, "Cai dat", Toast.LENGTH_SHORT).show();
                        if (settingFragment == null) {
                            settingFragment = new SettingFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.Content, settingFragment).commit();
                        */
                        break;
                    case R.id.mnLop:
                        /*Toast.makeText(loptinchi.this, "Cai dat", Toast.LENGTH_SHORT).show();
                        if (settingFragment == null) {
                            settingFragment = new SettingFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.Content, settingFragment).commit();
                        */
                        break;
                    case R.id.mnLich:
                        /*Toast.makeText(loptinchi.this, "Cai dat", Toast.LENGTH_SHORT).show();
                        if (settingFragment == null) {
                            settingFragment = new SettingFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.Content, settingFragment).commit();
                        */
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void xemthongtintaikhoan() {
    }

    private void dangxuat() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(loptinchi.this, signin.class);
        startActivity(intent);
        finish();
    }

    void loadData() throws IOException, JSONException {
        LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
        Call<ResponseBody> call = lopTinChiService.getAll();
        final int[] code = new int[1];
        Response<ResponseBody> response = call.execute();
        code[0] = response.code();
        Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
        if (code[0] == 200) {
            data_loptinchi = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.body().string());
            int i = 0;
            while (i<jsonArray.length()) {
                Long id = new Long(jsonArray.getJSONObject(i).getInt("id"));
                String mamonhoc = jsonArray.getJSONObject(i).getJSONObject("monhoc").getString("ma");
                String monhoc = jsonArray.getJSONObject(i).getJSONObject("monhoc").getString("monhoc");
                String namhoc = jsonArray.getJSONObject(i).getJSONObject("hocky").getString("namhoc");
                Long hocky = new Long(jsonArray.getJSONObject(i).getJSONObject("hocky").getInt("hocky"));
                data_loptinchi.add(new LopTinChi(id,mamonhoc,monhoc,hocky,namhoc));
                i++;
            }
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        //data test
//        data_loptinchi.add(new LopTinChi(1L,"INT123","Mobile",2L,"2022-2023", "Nguyen Van A"));
//        data_loptinchi.add(new LopTinChi(2L,"INT124","Web",2L,"2022-2023", "Nguyen Van B"));
    }

}