package come.example.myone;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.TextDelegate;

public class SplashScreen extends BaseActivity {
    SharedPreferences sharedPreferences;
    Boolean firstTime;
    ProgressBar progressBar;
    private static int SPlASH_TIME_OUT = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        begin();
    }
        void begin(){
        progressBar = findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.VISIBLE);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime", true);
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, 0, 100);
        anim.setDuration(2000);
        progressBar.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                firstTime = false;
                editor.putBoolean("firstTime", firstTime);
                editor.apply();
                Intent homeIntent = new Intent(SplashScreen.this, SignHome.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPlASH_TIME_OUT);
    }
}




