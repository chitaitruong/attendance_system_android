package ptithcm.chitaitruong.diemdanhsystem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import ptithcm.chitaitruong.diemdanhsystem.R;
import ptithcm.chitaitruong.diemdanhsystem.model.DiemDanh;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;

public class NgayListAdapter extends RecyclerView.Adapter<NgayListAdapter.ViewHolder> {
    private ArrayList<Ngay> ds_ngay;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNgay;
        private final TextView tvBuoi;
        private LinearLayout layout_click;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvNgay = (TextView) view.findViewById(R.id.tvNgay);
            tvBuoi = (TextView) view.findViewById(R.id.tvBuoi);
            layout_click = (LinearLayout) view.findViewById(R.id.layout_click1);
        }

        public TextView getTvNgay() {
            return tvNgay;
        }

        public TextView getTvBuoi() {
            return tvBuoi;
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
    public NgayListAdapter(ArrayList<Ngay> ds_ngay) {
        this.ds_ngay = ds_ngay;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NgayListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ngay_adapter, viewGroup, false);

        return new NgayListAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(NgayListAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        try {
            if (new SimpleDateFormat("yyyy-MM-dd").parse(ds_ngay.get(position).getNgay()).before(new Date())) {
                viewHolder.getLayout_click().setBackgroundColor(R.color.Java);
            }
            viewHolder.getTvNgay().setText(ds_ngay.get(position).getNgay());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        viewHolder.getTvBuoi().setText("Buổi " + String.valueOf(position));
//        DateTimeFormatter formatter = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        }
//        LocalDate date1 = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            date1 = LocalDate.parse(ds_ngay.get(position).getNgay(), formatter);
//        }
//        int result = 0;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            result = date1.compareTo(LocalDate.now());
//        }
//        if (result == 0) {
//            viewHolder.getLayout_click().setBackgroundColor(R.color.purple_500);
//        } else if (result > 0) {
//            viewHolder.getLayout_click().setBackgroundColor(R.color.purple_700);
//        } else if (result < 0) {
//            viewHolder.getLayout_click().setBackgroundColor(R.color.purple_200);
//        }

    }

    @Override
    public int getItemCount() {
        return ds_ngay.size();
    }
}
