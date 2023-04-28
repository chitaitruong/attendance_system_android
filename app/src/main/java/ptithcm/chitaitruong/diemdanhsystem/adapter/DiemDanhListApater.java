package ptithcm.chitaitruong.diemdanhsystem.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DiemDanhListApater extends RecyclerView.Adapter<DiemDanhListApater.ViewHolder> {
    private ArrayList<DiemDanh> ds_diemdanh;
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
        private final TextView tvMaSv;
        private final TextView tvHoTen;
        private RadioGroup radioGroup;
        private RadioButton radio_present, radio_late, radio_absent;
        private LinearLayout layout_click;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvMaSv = (TextView) view.findViewById(R.id.tvMaSv);
            tvHoTen = (TextView) view.findViewById(R.id.tvHoTen);
            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
            radio_present = (RadioButton) view.findViewById(R.id.radio_present);
            radio_late = (RadioButton) view.findViewById(R.id.radio_late);
            radio_absent = (RadioButton) view.findViewById(R.id.radio_absent);
            layout_click = (LinearLayout) view.findViewById(R.id.layout_click1);
        }

        public TextView getTvMaSv() {
            return tvMaSv;
        }

        public TextView getTvHoTen() {
            return tvHoTen;
        }

        public RadioGroup getRadioGroup() {
            return radioGroup;
        }

        public void setRadioGroup(RadioGroup radioGroup) {
            this.radioGroup = radioGroup;
        }

        public RadioButton getRadio_present() {
            return radio_present;
        }

        public void setRadio_present(RadioButton radio_present) {
            this.radio_present = radio_present;
        }

        public RadioButton getRadio_late() {
            return radio_late;
        }

        public void setRadio_late(RadioButton radio_late) {
            this.radio_late = radio_late;
        }

        public RadioButton getRadio_absent() {
            return radio_absent;
        }

        public void setRadio_absent(RadioButton radio_absent) {
            this.radio_absent = radio_absent;
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
    public DiemDanhListApater(ArrayList<DiemDanh> ds_diemdanh, RecyclerViewActionListener mListener) {
        this.ds_diemdanh = ds_diemdanh;
        this.mListener = mListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DiemDanhListApater.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.diemdanh_adapter, viewGroup, false);

        return new DiemDanhListApater.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DiemDanhListApater.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTvHoTen().setText(ds_diemdanh.get(position).getHoTen());
        viewHolder.getTvMaSv().setText(ds_diemdanh.get(position).getMaSv());
        switch (ds_diemdanh.get(position).getTrangThai().intValue()) {
            case 0:
                viewHolder.getRadio_absent().setChecked(true);
                //viewHolder.getLayout_click().setBackgroundResource(R.drawable.gradient_color_2);
                break;
            case 1:
                viewHolder.getRadio_present().setChecked(true);
                //viewHolder.getLayout_click().setBackgroundResource(R.drawable.gradient_color_1);
                break;
            case 2:
                viewHolder.getRadio_late().setChecked(true);
                //viewHolder.getLayout_click().setBackgroundResource(R.drawable.gradient_color_3);
                break;
        }
        viewHolder.getLayout_click().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());
            }
        });
        viewHolder.getLayout_click().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.onViewLongClicked(view.getId(), viewHolder.getAdapterPosition());
                return false;
            }
        });
        viewHolder.getRadio_late().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());
            }
        });
        viewHolder.getRadio_present().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());
            }
        });
        viewHolder.getRadio_absent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClicked(view.getId(), viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds_diemdanh.size();
    }

    // Return the size of your dataset (invoked by the layout manager)
}
