package come.example.myone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DietInfo extends AppCompatActivity {
    SharedPreferences sp;

String[] description;
    String[] rank;
    ViewPager viewPager;
    ArrayList<String> ar = new ArrayList<String>();
    String value2 = null;
    private StringBuilder text4 = new StringBuilder();
    PagerAdapter adapter;
    ImageView imageView;
TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_info);
        tabLayout = findViewById(R.id.tabDots);
        imageView = findViewById(R.id.NutritionHeadImage);
        imageView.setColorFilter(getResources().getColor(R.color.greyTint), android.graphics.PorterDuff.Mode.MULTIPLY);
        // Generate sample data
        rank = new String[]{"1", "2", "3", "4"};
        description = new String[]{"1"};

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("some_key");
         value2 = getIntent().getExtras().getString("some_other_key");
        String s = value.replace("R.drawable.", "");
        int id = getResources().getIdentifier(s, "drawable", getPackageName());
        imageView.setImageResource((id));

        if(value2!=null) {
             showDiet(value2, 1);
             showDiet(value2, 2);
             showDiet(value2, 3);
             showDiet(value2, 4);
             description = ar.toArray(new String[ar.size()]);

             // Locate the ViewPager in viewpager_main.xml
             viewPager = findViewById(R.id.pager);
             tabLayout.setupWithViewPager(viewPager, true);

             // Pass results to ViewPagerAdapter Class

             adapter = new DietAdapter(DietInfo.this, rank, description);
             // Binds the Adapter to the ViewPager
             viewPager.setAdapter(adapter);
         }
    }

    // Function to add x in arr
    public String showDiet(String diet,int i){

    BufferedReader reader = null;

    try {
        reader = new BufferedReader(
                new InputStreamReader(getApplicationContext().getAssets().open(diet + "_" + i + "_" + "sample_text.txt")));

        // do reading, usually loop until end of file reading
        String mLine;
        while ((mLine = reader.readLine()) != null) {
            text4.append(mLine);
            text4.append('\n');
            ar.add(String.valueOf(text4));
            text4.setLength(0);
        }

    } catch (IOException e) {
        Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                //log the exception
            }
        }

    }
    return diet;
}

    public void goBack(View view) {
        finish();

    }
}

