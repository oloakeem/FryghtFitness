package come.example.myone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class WorkoutAdapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public WorkoutAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.workout_item, container, false);


        ImageView imageView,imageView1;
        TextView title, desc;

        imageView = view.findViewById(R.id.locked);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        imageView1 = view.findViewById(R.id.image);

        imageView1.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());
        switch(position){
            case 0:
                title.setTextColor(Color.parseColor("#FFE045"));
                break;
            case 1:
                title.setTextColor(Color.parseColor("#FF4141"));

                break;
            case 2:
                title.setTextColor(Color.parseColor("#8042E1FF"));
                break;
            case 3:
                title.setTextColor(Color.parseColor("#8027FF27"));
                break;
        }
        if(position >1){
        imageView.setVisibility(View.VISIBLE);
        }

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = null;
                switch(position){
                    case 0:
                        selectedFragment = new MobilityHome();
                        break;
                    case 1:
                        selectedFragment = new ExerciseGenerator();
                        break;
                    case 2:
                        //selectedFragment = new MobilityHome();
                        break;
                    case 3:
                       // selectedFragment = new MobilityHome();
                        break;
                }
                if(selectedFragment!=null) {
                    FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    transaction.replace(R.id.fragment_container, selectedFragment);
                    transaction.commit();
/*
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container,
                        new ExerciseGenerator()).commit();
                        */
                }else {
                     Toast.makeText(context, "Feature Locked" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}