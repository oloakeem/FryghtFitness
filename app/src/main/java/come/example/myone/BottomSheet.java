package come.example.myone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class BottomSheet extends BottomSheetDialogFragment {
    public static final int DATEPICKER_FRAGMENT = 1; // class variable
    boolean isaMale;
    float weightInt;
    String goalbasedTotal;
    int heightInt,ageInt;
    double  activityLvl,TotalCal;
    AnimatedPieView mAnimatedPieView;
    Animation fader;
    TextView view ;
    TextView protienView ;
    TextView fatView;
    TextView carbView ;
    TextView carbsR_New,fatsR_New,proteinR_New;
    Button customMacros;
    Spinner spinner;
    int newKCal;
    int sendI;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        customMacros = v.findViewById(R.id.customMacros);
        view =  v.findViewById(R.id.resultsBmr_New);
         protienView =  v.findViewById(R.id.protien_New);
         carbView =  v.findViewById(R.id.carbs_New);
         fatView = v.findViewById(R.id.fats_New);
        carbsR_New = v.findViewById(R.id.myCarbs);
        fatsR_New = v.findViewById(R.id.myFatsText);
        proteinR_New = v.findViewById(R.id.myProteinText);
        mAnimatedPieView = v.findViewById(R.id.anime_New);
        fader = AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
        heightInt = this.getArguments().getInt("height");
        weightInt = this.getArguments().getFloat("weight");
        ageInt = this.getArguments().getInt("age");
        activityLvl = this.getArguments().getDouble("activityLvl");
        isaMale = this.getArguments().getBoolean("isaMale");
         spinner = v.findViewById(R.id.myTotalText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.goals_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goalbasedTotal = (calculateM(isaMale,heightInt,weightInt,ageInt,activityLvl));
        sendI = Integer.parseInt(goalbasedTotal);
        String text = (calculateM(isaMale,heightInt,weightInt,ageInt,activityLvl));
        TotalCal = Double.parseDouble(text);
        begin();
        customMacros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View myView, int pos, long id) {
                Object item = adapterView.getItemAtPosition(pos);
                String goal = item.toString();
                switch (goal){
                    case "Maintenance":
                        view.setText(String.valueOf(sendI));
                        newKCal =  (sendI);
                        fatView.setText(fats(finalKcal())+"g");
                        carbView.setText(carbs(finalKcal())+"g");
                        carbView.startAnimation(fader);
                        protienView.startAnimation(fader);
                        fatView.startAnimation(fader);
                        break;

                    case "Weight loss":
                        float f = (500/finalKcal());
                        float x = Math.round((carbs(finalKcal()))- (carbs(finalKcal())*f));
                        float y = Math.round((fats(finalKcal()))- (fats(finalKcal())*f));
                          view.setText(String.valueOf(sendI-500));
                        newKCal =  (sendI - 500);
                        fatView.setText((y)+"g");
                        carbView.setText((x)+"g");
                        view.setText(String.valueOf(sendI-500));
                        carbView.startAnimation(fader);
                        protienView.startAnimation(fader);
                        fatView.startAnimation(fader);
                        break;

                    case "Weight gain":
                        float c = (500/finalKcal());
                        float a = Math.round((carbs(finalKcal()))+ (carbs(finalKcal())*c));
                        float b = Math.round((fats(finalKcal()))+ (fats(finalKcal())*c));
                        view.setText(String.valueOf(sendI+500));
                        newKCal = (sendI+500);
                        fatView.setText((b)+"g");
                        carbView.setText((a)+"g");
                        carbView.startAnimation(fader);
                        protienView.startAnimation(fader);
                        fatView.startAnimation(fader);
                        break;
                        default:
                            break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DATEPICKER_FRAGMENT:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    int is_pro = bundle.getInt("newPro_R");
                    int is_fat = bundle.getInt("newFat_R");
                    int is_carb = bundle.getInt("newCarb_R");

                    AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                    config.startAngle(-90)// Starting angle offset
                            .addData(new SimplePieInfo(is_pro, Color.parseColor("#a31b0f"), "proteins(g)"))
                            .addData(new SimplePieInfo(is_fat, Color.parseColor("#2a8a1d"), "fats(g)"))
                            .addData(new SimplePieInfo(is_carb, Color.parseColor("#0cccf2"), "carbs(g)"))//Data (bean that implements the IPieInfo interface)
                            .drawText(false)// Whether to draw a text description
                            .strokeMode(false) // Whether to draw ring-chart(default:true)
                            .textSize(42)// Text description size0cccf2
                            .splitAngle(1f)
                            .canTouch(true)// Whether to allow the pie click to enlarge
                            .duration(2000);// draw pie animation duration

                    mAnimatedPieView.applyConfig(config);
                    mAnimatedPieView.start();
                    protienView.setText(is_pro+"g");
                    fatView.setText(is_fat+"g");
                    carbView.setText(is_carb+"g");


                } else if (resultCode == Activity.RESULT_CANCELED) {
               // ...
                    Toast.makeText(getContext(), "didnt work", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
    private void showEditDialog() {
        CalorieDialog dialog = new CalorieDialog();
        Bundle args = new Bundle();
        args.putInt("newKcal", newKCal);
        dialog.setArguments(args);
// setup link back to use and display
        dialog.setTargetFragment(this, DATEPICKER_FRAGMENT);
        dialog.show(getFragmentManager().beginTransaction(), "MyProgressDialog");


    }
    public float protiens(){
        float  protien = ((weightInt * 2.20462f) * 0.9f)* 4f;
        return Math.round(protien);
    }
    public float carbs (float x){
        float carb = (finalKcal() * .7f)/4f;
        return Math.round(carb);
    }
    public float finalKcal( ){
        float totalKcal;
        String text = (calculateM(isaMale,heightInt,weightInt,ageInt,activityLvl));
        totalKcal = Float.parseFloat(text) - protiens();
        return totalKcal;
    }
    public float fats (float x){
        float fat = (finalKcal() * .3f)/9f;
        return Math.round(fat);
    }
    public void begin(){
        setText(calculateM(isaMale, heightInt, weightInt, ageInt, activityLvl), (float) Math.round(protiens() / 4f), fats(finalKcal()), carbs(finalKcal()));
        carbsR_New.startAnimation(fader);
        fatsR_New.startAnimation(fader);
        proteinR_New.startAnimation(fader);
        carbsR_New.setVisibility(View.VISIBLE);
        fatsR_New.setVisibility(View.VISIBLE);
        proteinR_New.setVisibility(View.VISIBLE);
    }
    public void setText(String text,Float pro,Float fat, Float carb) {

        view.setText(text);

        String pV = pro.toString();
        protienView.setText(pV+"g");

        String fV = fat.toString();
        fatView.setText(fV+"g");

        String cV = carb.toString();
        carbView.setText(cV+"g");

        carbView.startAnimation(fader);
        protienView.startAnimation(fader);
        fatView.startAnimation(fader);

        drawPie();
    }
    public void drawPie(){

        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(protiens()/4, Color.parseColor("#a31b0f"), "proteins(g)"))
                .addData(new SimplePieInfo(fats(finalKcal()), Color.parseColor("#2a8a1d"), "fats(g)"))
                .addData(new SimplePieInfo(carbs(finalKcal()), Color.parseColor("#0cccf2"), "carbs(g)"))//Data (bean that implements the IPieInfo interface)
                .drawText(false)// Whether to draw a text description
                .strokeMode(false) // Whether to draw ring-chart(default:true)
                .textSize(42)// Text description size0cccf2
                .splitAngle(1f)
                .canTouch(true)// Whether to allow the pie click to enlarge
                .duration(2000);// draw pie animation duration

        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }
    public String calculateM(boolean isMale, int height, float weight, int age,double alvl) {
        int s;
        if (isMale) {
            s = 5;
        }
        else {
            s = -161;
        }
        return String.valueOf(Math.round(((10 * weight) + (6.25f * height) - (5 * age) + s)*alvl));
    }



}
