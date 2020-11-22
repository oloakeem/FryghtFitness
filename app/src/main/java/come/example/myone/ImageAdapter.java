package come.example.myone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    int[] image;
    LayoutInflater inflater;

    public ImageAdapter(Context context,int[] image) {
        this.context = context;
        this.image = image;


    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        ImageView myImage;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.exercise_image_item, container,
                false);

                // Locate the TextViews in viewpager_item.xml
        myImage =  itemView.findViewById(R.id.exercise_image);
        myImage.setImageResource(image[position]);
        // Capture position and set to the TextViews
       // myImage.setImageResource(image[position]);

        // Add viewpager_item.xml to ViewPager
        (container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
     //   (container).removeView((RelativeLayout) object);

    }
}