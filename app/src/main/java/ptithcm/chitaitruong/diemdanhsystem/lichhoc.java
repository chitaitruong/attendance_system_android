package ptithcm.chitaitruong.diemdanhsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.adapter.NgayListAdapter;
import ptithcm.chitaitruong.diemdanhsystem.adapter.RecyclerItemClickListener;
import ptithcm.chitaitruong.diemdanhsystem.helper.RetrofitClientCreator;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.LoginRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.XuatFileRequest;
import ptithcm.chitaitruong.diemdanhsystem.service.AuthService;
import ptithcm.chitaitruong.diemdanhsystem.service.LopTinChiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lichhoc extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView hocky_detail;
    ImageView imageView;
    Retrofit retrofit, retrofit2;
    ArrayList<Ngay> ds_ngay = new ArrayList<>();
    LopTinChi lopTinChi;
    NgayListAdapter ngayListAdapter;
    DownloadManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichhoc);
        getWindow().setExitTransition(null);
        retrofit = RetrofitClientCreator.getClientWithInterceptor(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar_class_detail1);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        retrofit2 = new Retrofit.Builder()

                .baseUrl("http://192.168.1.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setControl();
        try {
            setEvent();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_class_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.xuatfile:
                //request tao file
                String file_name = "DiemDanh_" + lopTinChi.getMamonhoc() + "_" + lopTinChi.getMonhoc() + "_" + lopTinChi.getNamhoc() + "_" + lopTinChi.getHocky();
                LopTinChiService lopTinChiService = retrofit2.create(LopTinChiService.class);
                Call<ResponseBody> call = lopTinChiService.xuatfile(new XuatFileRequest(lopTinChi.getId(), file_name));
                try {
                    call.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://192.168.1.2:9000/" + file_name + ".xlsx");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                long reference = manager.enqueue(request);
                return true;
            case R.id.help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setEvent() throws JSONException, IOException {
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

    private void loadData() throws IOException, JSONException {
        LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
        Call<ResponseBody> call = lopTinChiService.getLich(lopTinChi.getId());
        final int[] code = new int[1];
        Response<ResponseBody> response = call.execute();
        code[0] = response.code();
        Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
        if (code[0] == 200) {
            ds_ngay = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.body().string());
            int i = 0;
            while (i<jsonArray.length()) {
                Long id = new Long(jsonArray.getJSONObject(i).getInt("id"));
                String ngay = jsonArray.getJSONObject(i).getString("ngay");
                ds_ngay.add(new Ngay(id,ngay));
                i++;
            }
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        //test data
//        ds_ngay.add(new Ngay(1L, "27/03/2023"));
//        ds_ngay.add(new Ngay(1L, "27/04/2023"));
//        ds_ngay.add(new Ngay(2L, "27/10/2023"));
//        ds_ngay.add(new Ngay(3L, "27/11/2023"));
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