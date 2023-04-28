package ptithcm.chitaitruong.diemdanhsystem.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ptithcm.chitaitruong.diemdanhsystem.R;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;
import ptithcm.chitaitruong.diemdanhsystem.model.SinhVien;

public class SinhVienListAdapter extends RecyclerView.Adapter<SinhVienListAdapter.ViewHolder> {
    private ArrayList<SinhVien> ds_sinhvien;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvHoTen, tvMaSv;
        private LinearLayout layout_click;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvHoTen = (TextView) view.findViewById(R.id.tvHoTen);
            tvMaSv = (TextView) view.findViewById(R.id.tvMaSv);
            layout_click = (LinearLayout) view.findViewById(R.id.layout_click3);
        }

        public TextView getTvHoTen() {
            return tvHoTen;
        }

        public TextView getTvMaSv() {
            return tvMaSv;
        }

        public LinearLayout getLayout_click() {
            return layout_click;
        }

        public void setLayout_click(LinearLayout layout_click) {
            this.layout_click = layout_click;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public SinhVienListAdapter(ArrayList<SinhVien> ds_sinhvien) {
        this.ds_sinhvien = ds_sinhvien;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SinhVienListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sinhvien_adapter, viewGroup, false);

        return new SinhVienListAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(SinhVienListAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTvHoTen().setText(ds_sinhvien.get(position).getHoTen());
        viewHolder.getTvMaSv().setText(ds_sinhvien.get(position).getMaSv());

    }

    @Override
    public int getItemCount() {
        return ds_sinhvien.size();
    }
}
