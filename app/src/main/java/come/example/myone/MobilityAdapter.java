package come.example.myone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class MobilityAdapter extends BaseAdapter {
    Context context;
    String mobilityTitle[];
    String mobilityDesc[];
    int mobilityImage[];
    LayoutInflater inflter;

    public MobilityAdapter(Context applicationContext, String[] mobilityTitle, String[] mobilityDesc, int[] mobilityImage) {
        this.context = context;
        this.mobilityTitle = mobilityTitle;
        this.mobilityDesc = mobilityDesc;
        this.mobilityImage = mobilityImage;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mobilityTitle.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.mobility_item, null);
        TextView mobTitle = (TextView) view.findViewById(R.id.mobility_title);
        TextView mobDesc = (TextView) view.findViewById(R.id.mobility_desc);
        ImageView mobImage = (ImageView) view.findViewById(R.id.mobility_image);
        mobTitle.setText(mobilityTitle[i]);
        mobDesc.setText(mobilityDesc[i]);
        mobImage.setImageResource(mobilityImage[i]);
        return view;
    }
}