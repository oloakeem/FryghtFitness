package come.example.myone;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

    public class DietAdapter extends PagerAdapter {
        // Declare Variables
        Context context;
        String[] rank;
        String[] country;
        String[] population;
        LayoutInflater inflater;

        public DietAdapter(Context context, String[] rank,String[] country) {
            this.context = context;
            this.rank = rank;
            this.country = country;

        }

        @Override
        public int getCount() {
            return rank.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // Declare Variables
            TextView txtrank;
            TextView txtcountry;

            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.diet_item, container,
                    false);
            // Locate the TextViews in viewpager_item.xml
            txtrank =  itemView.findViewById(R.id.diet_title);
            txtcountry =  itemView.findViewById(R.id.description);

            // Capture position and set to the TextViews
            txtrank.setText(rank[position]);
            txtcountry.setText(country[position]);


            // Add viewpager_item.xml to ViewPager
            (container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Remove viewpager_item.xml from ViewPager
            (container).removeView((RelativeLayout) object);

        }
    }