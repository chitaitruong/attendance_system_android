package ptithcm.chitaitruong.diemdanhsystem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ptithcm.chitaitruong.diemdanhsystem.R;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;



public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {

    private ArrayList<LopTinChi> ds_loptinchi;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvHocKy;
        private final TextView tvLopTinChi;
        private final ImageView iv;
        private final RelativeLayout frameLayout;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvHocKy = (TextView) view.findViewById(R.id.hocKyName_adapter);
            tvLopTinChi = (TextView) view.findViewById(R.id.subjectName_adapter);
            iv = (ImageView) view.findViewById(R.id.imageClass_adapter);
            frameLayout = (RelativeLayout) view.findViewById(R.id.frame_bg);
        }

        public TextView getTvHocKy() {
            return tvHocKy;
        }

        public TextView getTvLopTinChi() {
            return tvLopTinChi;
        }

        public ImageView getIv() {
            return iv;
        }

        public RelativeLayout getFrameLayout() {
            return frameLayout;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public ClassListAdapter(ArrayList<LopTinChi> ds_loptinchi) {
        this.ds_loptinchi = ds_loptinchi;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.class_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTvHocKy().setText("Học kỳ " + ds_loptinchi.get(position).getHocky() + " Năm học " +ds_loptinchi.get(position).getNamhoc());
        viewHolder.getTvLopTinChi().setText(ds_loptinchi.get(position).getMamonhoc() + " - " +ds_loptinchi.get(position).getMonhoc());
        switch ((int) (ds_loptinchi.get(position).getId()%6)) {
            case 0:
                viewHolder.getIv().setImageResource(R.drawable.asset_bg_paleblue);
                viewHolder.getFrameLayout().setBackgroundResource(R.drawable.gradient_color_1);
                break;
            case 1:
                viewHolder.getIv().setImageResource(R.drawable.asset_bg_green);
                viewHolder.getFrameLayout().setBackgroundResource(R.drawable.gradient_color_2);
                break;
            case 2:
                viewHolder.getIv().setImageResource(R.drawable.asset_bg_yellow);
                viewHolder.getFrameLayout().setBackgroundResource(R.drawable.gradient_color_3);
                break;
            case 3:
                viewHolder.getIv().setImageResource(R.drawable.asset_bg_palegreen);
                viewHolder.getFrameLayout().setBackgroundResource(R.drawable.gradient_color_4);
                break;
            case 4:
                viewHolder.getIv().setImageResource(R.drawable.asset_bg_paleorange);
                viewHolder.getFrameLayout().setBackgroundResource(R.drawable.gradient_color_5);
                break;
            case 5:
                viewHolder.getIv().setImageResource(R.drawable.asset_bg_white);
                viewHolder.getFrameLayout().setBackgroundResource(R.drawable.gradient_color_6);
//                holder.subject_name.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.text_color_secondary));
//                holder.class_name.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.text_color_secondary));
//                holder.total_students.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.text_color_secondary));
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ds_loptinchi.size();
    }

}

