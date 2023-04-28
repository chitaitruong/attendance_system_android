package ptithcm.chitaitruong.diemdanhsystem.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ptithcm.chitaitruong.diemdanhsystem.R;
import ptithcm.chitaitruong.diemdanhsystem.model.DiemDanh;
import ptithcm.chitaitruong.diemdanhsystem.model.LopTinChi;

public class MonHocListAdapter extends RecyclerView.Adapter<MonHocListAdapter.ViewHolder> {
    private ArrayList<LopTinChi> ds_loptinchi;
    private RecyclerViewActionListener mListener;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public interface RecyclerViewActionListener {
        void onViewClicked(int clickedViewId, int clickedItemPosition);
        void onViewLongClicked(int clickedViewId, int clickedItemPosition);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMonHoc;
        private final TextView tvHocKy;
        private LinearLayout layout_click2;
        private ImageButton imageButtonUpdate, imageButtonDelelte;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvMonHoc = (TextView) view.findViewById(R.id.tvLopTinChi);
            tvHocKy = (TextView) view.findViewById(R.id.tvHocKy);
            layout_click2 = (LinearLayout) view.findViewById(R.id.layout_click2);
            imageButtonDelelte = (ImageButton) view.findViewById(R.id.delete);
            imageButtonUpdate = (ImageButton) view.findViewById(R.id.update);

        }

        public TextView getTvMonHoc() {
            return tvMonHoc;
        }

        public TextView getTvHocKy() {
            return tvHocKy;
        }

        public LinearLayout getLayout_click2() {
            return layout_click2;
        }

        public void setLayout_click2(LinearLayout layout_click2) {
            this.layout_click2 = layout_click2;
        }

        public ImageButton getImageButtonUpdate() {
            return imageButtonUpdate;
        }

        public void setImageButtonUpdate(ImageButton imageButtonUpdate) {
            this.imageButtonUpdate = imageButtonUpdate;
        }

        public ImageButton getImageButtonDelelte() {
            return imageButtonDelelte;
        }

        public void setImageButtonDelelte(ImageButton imageButtonDelelte) {
            this.imageButtonDelelte = imageButtonDelelte;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public MonHocListAdapter(ArrayList<LopTinChi> ds_loptinchi, RecyclerViewActionListener mListener) {
        this.ds_loptinchi = ds_loptinchi;
        this.mListener = mListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MonHocListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.loptinchi_adapter, viewGroup, false);

        return new MonHocListAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MonHocListAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTvMonHoc().setText(ds_loptinchi.get(position).getMamonhoc() + " - " + ds_loptinchi.get(position).getMonhoc());
        viewHolder.getTvMonHoc().setText("Học kỳ " + ds_loptinchi.get(position).getHocky() + " Năm " + ds_loptinchi.get(position).getNamhoc());
        viewHolder.getLayout_click2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());
            }
        });
        viewHolder.getLayout_click2().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.onViewLongClicked(view.getId(), viewHolder.getAdapterPosition());
                return false;
            }
        });
        viewHolder.getImageButtonDelelte().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());

            }
        });
        viewHolder.getImageButtonUpdate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());

            }
        });

    }

    @Override
    public int getItemCount() {
        return ds_loptinchi.size();
    }

    // Return the size of your dataset (invoked by the layout manager)
}
