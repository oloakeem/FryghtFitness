package come.example.myone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DisplayExercise extends AppCompatActivity {
RecyclerView myRecyclerView;
    ViewPager viewPager;
    PagerAdapter adapter2;
    InputStreamReader inputStreamReader ;
Spinner myOptions,myOptions2;
    static Integer[] result;

    int value2;
    String passedKey,muscleKey;
    String exerciseItem;
    List<Integer> list = new ArrayList<Integer>();
    private AssetManager assetManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exercise);
        TabLayout tabLayout =  findViewById(R.id.exerTab);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("custom-event-name"));

        myRecyclerView = findViewById(R.id.ExerciseDisplayView);
        Bundle bundle1 = getIntent().getExtras();
        value2 = bundle1.getInt("checked");
        /*Exercise picked from ExerciseGenerator Activity*/
        passedKey = getIntent().getExtras().getString("exerciseKey");
        /*Muscle Training picked from ExerciseGenerator Activity*/

        muscleKey = getIntent().getExtras().getString("muscleKey");
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(DisplayExercise.this, workOut(), getRepSets());
        myRecyclerView.setAdapter(exerciseAdapter);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(DisplayExercise.this, LinearLayoutManager.VERTICAL, false));
        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.exercise_header);
        tabLayout.setupWithViewPager(viewPager, true);


    }
    /* Section receives a value from an adapter to set the image  */

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            exerciseItem  = intent.getStringExtra("quantity");

            if (exerciseItem != null) {
                Toast.makeText(getApplicationContext(), exerciseItem, Toast.LENGTH_SHORT).show();
                adapter2 = new ImageAdapter(DisplayExercise.this, chooseArray(exerciseItem));
                viewPager.setAdapter(adapter2);
        }

        }
    };


    public int[] chooseArray(String diet)   {
        String newDiet= diet.replaceAll("\\s","");
        int ex1 = getResources().getIdentifier(newDiet.toLowerCase()+"_"+"1", "drawable" ,DisplayExercise.this.getPackageName()); // You had used "name"
        int ex2 = getResources().getIdentifier(newDiet.toLowerCase()+"_"+"2", "drawable" ,DisplayExercise.this.getPackageName()); // You had used "name"
        int ex3 = getResources().getIdentifier(newDiet.toLowerCase()+"_"+"3", "drawable" ,DisplayExercise.this.getPackageName()); // You had used "name"

        ArrayList<Integer>  mylist = new ArrayList<Integer>();
        mylist.add(ex1);
        mylist.add(ex2);
        mylist.add(ex3);
        int[] myarray = new int[mylist.size()];
        int i= 0;
        for (int num : mylist) {
            myarray[i++] = num;
        }
        return myarray;
        }


    /* Section assigns an image to what ever exercise was clicked

    public int[] chooseArray(){
        String item = null;
        item = (exerciseItem);
        int[] setImage = new int[0];
        switch (item){

            case "Push Ups":
                setImage = new int[]{R.drawable.pushups_1,R.drawable.pushups_1,R.drawable.pushups_1};
                break;
            case "Chest Squeeze":
                setImage = new int[]{R.drawable.chest_squeeze,R.drawable.chest_squeeze,R.drawable.chest_squeeze};

                break;
            case "Pike Push-Ups":
               setImage = new int[]{R.drawable.pike_pushups,R.drawable.pike_pushups,R.drawable.pike_pushups};

                break;
            default:
                break;
        }
        return setImage;
         Section checks which workout program was selected

         }*/

     public String[] workOut( ){
        String[] choice = new String[0];
      String workOut = null;
    workOut = (passedKey);
        switch (workOut){

            case"Push":
                choice =  getPush();
                break;
            case "Pull":
                choice= getPull();
                break;
            case "Legs":
               choice = getLegs();
                break;
            default:
                break;
        }
        return choice;
    }
    /* Section gives arrays of specific workout programs  */
            public String[] getPush( ) {

                PushItems newPushItems = new PushItems();
                newPushItems.getworkout();
                return newPushItems.getworkout();
            }
                public String[] getPull( ) {

                    PullItems pullItems = new PullItems();
                    pullItems.getworkout();
                    return pullItems.getworkout();
                }
                    public String[] getLegs( ){

                        LegItems legItems = new LegItems();
                        legItems.getworkout();
                        return legItems.getworkout();
    }
    /* Section gives arrays of specific sets & reps  */

    public  String[] getRepSets() {
            SetsAndReps newSetsAndReps = new SetsAndReps();
            return newSetsAndReps.intensity(muscleKey);

    }
}
