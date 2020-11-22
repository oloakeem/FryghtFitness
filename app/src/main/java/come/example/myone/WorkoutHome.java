package come.example.myone;

import android.animation.ArgbEvaluator;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHome extends Fragment {
    ViewPager viewPager;
    WorkoutAdapter adapter;
    List<Model> models;
    LayerDrawable colors ;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TypedArray imgs;
    TransitionDrawable td;
    ImageView imageView;

    Animation fadeIN,fadeOut;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.workout_home, container, false);


        models = new ArrayList<>();
        models.add(new Model("Warm ups", "Various warm ups & \nstretches",R.drawable.smobility));
        models.add(new Model("PPL", "(Push, Pull, Legs)",R.drawable.pushpullleg));
        models.add(new Model("Cardio", "High & low intensity",R.drawable.cardio));
        models.add(new Model("Power Lifting", "Squat,Bench,Dead-lift",R.drawable.power_lifting));

        adapter = new WorkoutAdapter(models, getContext()) {
        };
        imageView = mView.findViewById(R.id.hope);
        imageView.setColorFilter(getResources().getColor(R.color.greyTint), android.graphics.PorterDuff.Mode.MULTIPLY);

        fadeIN = AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
        fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

        viewPager = mView.findViewById(R.id.myNewViewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 0, 100, 0);
        imgs = getResources().obtainTypedArray(R.array.random_imgs);
        List<TransitionDrawable>array = new ArrayList<TransitionDrawable>();


        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setAlpha(normalizedposition);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Drawable currentDrawableInLayerDrawable;
                currentDrawableInLayerDrawable = getResources().getDrawable(imgs.getResourceId(position,+1));

               if (position < (adapter.getCount() - 1) && position < (3 - 1)) {

                    imageView.setImageDrawable(currentDrawableInLayerDrawable);
    imageView.startAnimation(fadeIN);
                } else {
                    if(position<=2){
                        imageView.startAnimation(fadeIN);
                    }
                    imageView.setImageDrawable(getResources().getDrawable(imgs.getResourceId(position,-1)));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    return mView;
    }

}


