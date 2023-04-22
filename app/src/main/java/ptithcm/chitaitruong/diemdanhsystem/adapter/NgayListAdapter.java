package ptithcm.chitaitruong.diemdanhsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ptithcm.chitaitruong.diemdanhsystem.R;
import ptithcm.chitaitruong.diemdanhsystem.model.Ngay;

public class NgayListAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Ngay> data;
    public NgayListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ngay> data) {
        super(context,resource,data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder.tvNgay = convertView.findViewById(R.id.tvNgay);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Ngay ngay = data.get(position);
        viewHolder.tvNgay.setText(ngay.getNgay());
        return convertView;
    }
    private class ViewHolder {
        TextView tvNgay;
    }
}
