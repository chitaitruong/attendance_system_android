package ptithcm.chitaitruong.diemdanhsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.ResponseBody;
import ptithcm.chitaitruong.diemdanhsystem.adapter.DiemDanhListApater;
import ptithcm.chitaitruong.diemdanhsystem.helper.RetrofitClientCreator;
import ptithcm.chitaitruong.diemdanhsystem.model.DiemDanh;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.UpdateDiemDanhRequest;
import ptithcm.chitaitruong.diemdanhsystem.payload.request.XuatFileRequest;
import ptithcm.chitaitruong.diemdanhsystem.service.LopTinChiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    Retrofit retrofit, retrofit2;
    PieChart pieChart;
    DownloadManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diemdanh);
        retrofit = RetrofitClientCreator.getClientWithInterceptor(this);
        getWindow().setExitTransition(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar_class_detail);
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
        setData();
    }

    private void setData() {
        pieChart.setUseInnerValue(true);
        // To animate the pie chart
        pieChart.startAnimation();
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
                        if (diemDanh.getTrangThai() == 2L) {
                            ds_diemdanh.add(diemDanh);
                        }

                    }

                } else  if (items[i].equals("Late")) {
                    for (DiemDanh diemDanh : tam) {
                        if (diemDanh.getTrangThai() == 1L) {
                            ds_diemdanh.add(diemDanh);
                        }

                    }
                } else  if (items[i].equals("Absent")) {
                    for (DiemDanh diemDanh : tam) {
                        if (diemDanh.getTrangThai() == 0L) {
                            ds_diemdanh.add(diemDanh);
                        }

                    }
                } else {
                    ds_diemdanh.addAll(tam);
                }
                pieChart.setInnerValueString(String.valueOf(ds_diemdanh.size()));
                diemDanhListApater.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) throws IOException, JSONException {
        switch (clickedViewId){
            /*case R.id.layout_click1:
                ds_diemdanh.get(clickedItemPosition).setTrangThai(1L);
                diemDanhListApater.notifyDataSetChanged();
                break;*/
            case R.id.radio_present:
                DiemDanh dd = ds_diemdanh.get(clickedItemPosition);
                if (dd.getTrangThai() != 2L) {
                    UpdateDiemDanhRequest updateDiemDanhRequest = new UpdateDiemDanhRequest(dd.getSinhvienId(), ngay.getId(), lopTinChi.getId(),dd.getGhiChu(), 2L);
                    LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
                    Call<ResponseBody> call = lopTinChiService.updateDiemDanh(updateDiemDanhRequest);
                    final int[] code = new int[1];
                    Response<ResponseBody> response = call.execute();
                    code[0] = response.code();
                    Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
                    if (code[0] == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        dd.setTrangThai(2L);
                        dd.setGhiChu(jsonObject.getString("ghi_chu"));
                        dd.setNgayCapNhat(jsonObject.getString("thoi_gian_cap_nhat"));
                        dd.setNgayDiemDiemDanh(jsonObject.getString("thoi_gian_quet_van_tay"));
                        diemDanhListApater.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.radio_absent:
                dd = ds_diemdanh.get(clickedItemPosition);
                if (dd.getTrangThai() != 0L) {
                    UpdateDiemDanhRequest updateDiemDanhRequest = new UpdateDiemDanhRequest(dd.getSinhvienId(), ngay.getId(), lopTinChi.getId(),dd.getGhiChu(), 0L);
                    LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
                    Call<ResponseBody> call = lopTinChiService.updateDiemDanh(updateDiemDanhRequest);
                    final int[] code = new int[1];
                    Response<ResponseBody> response = call.execute();
                    code[0] = response.code();
                    Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
                    if (code[0] == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        dd.setTrangThai(0L);
                        dd.setGhiChu(jsonObject.getString("ghi_chu"));
                        dd.setNgayCapNhat(jsonObject.getString("thoi_gian_cap_nhat"));
                        dd.setNgayDiemDiemDanh(jsonObject.getString("thoi_gian_quet_van_tay"));
                        diemDanhListApater.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.radio_late:
                dd = ds_diemdanh.get(clickedItemPosition);
                if (dd.getTrangThai() != 1L) {
                    UpdateDiemDanhRequest updateDiemDanhRequest = new UpdateDiemDanhRequest(dd.getSinhvienId(), ngay.getId(), lopTinChi.getId(),dd.getGhiChu(), 1L);
                    LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
                    Call<ResponseBody> call = lopTinChiService.updateDiemDanh(updateDiemDanhRequest);
                    final int[] code = new int[1];
                    Response<ResponseBody> response = call.execute();
                    code[0] = response.code();
                    Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
                    if (code[0] == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        dd.setTrangThai(1L);
                        dd.setGhiChu(jsonObject.getString("ghi_chu"));
                        dd.setNgayCapNhat(jsonObject.getString("thoi_gian_cap_nhat"));
                        dd.setNgayDiemDiemDanh(jsonObject.getString("thoi_gian_quet_van_tay"));
                        diemDanhListApater.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
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
    private void loadData() throws IOException, JSONException {
        LopTinChiService lopTinChiService = retrofit.create(LopTinChiService.class);
        Call<ResponseBody> call = lopTinChiService.getDiemDanh(lopTinChi.getId(), ngay.getId());
        final int[] code = new int[1];
        Response<ResponseBody> response = call.execute();
        code[0] = response.code();
        Toast.makeText(this, "" + code[0], Toast.LENGTH_SHORT).show();
        int late = 0, present = 0, absent = 0;
        if (code[0] == 200) {
            ds_diemdanh = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.body().string());
            int i = 0;
            while (i<jsonArray.length()) {
                Long id = new Long(jsonArray.getJSONObject(i).getInt("id"));
                String hoten = jsonArray.getJSONObject(i).getString("hoten");
                String username = jsonArray.getJSONObject(i).getString("username");
                Long trangthai = new Long(jsonArray.getJSONObject(i).getInt("trang_thai"));
                if (trangthai == 2L) {
                    present += 1;
                } else if (trangthai == 1L) {
                    late += 1;
                } else {
                    absent += 1;
                }
                String ghichu = jsonArray.getJSONObject(i).getString("ghi_chu");
                String thoi_gian_cap_nhat = jsonArray.getJSONObject(i).getString("thoi_gian_cap_nhat");
                String thoi_gian_quet_van_tay = jsonArray.getJSONObject(i).getString("thoi_gian_quet_van_tay");
                ds_diemdanh.add(new DiemDanh(id,username,hoten,trangthai,ghichu, thoi_gian_cap_nhat, thoi_gian_quet_van_tay));
                i++;
            }
            pieChart.addPieSlice(
                    new PieModel(
                            "Late",
                            late,
                            Color.parseColor("#FFA726")));
            pieChart.addPieSlice(
                    new PieModel(
                            "Present",
                            present,
                            Color.parseColor("#66BB6A")));
            pieChart.addPieSlice(
                    new PieModel(
                            "Absent",
                            absent,
                            Color.parseColor("#EF5350")));
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
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
        pieChart = findViewById(R.id.piechart);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
//        startActivity(intent);
//        return super.onOptionsItemSelected(item);
//    }
}