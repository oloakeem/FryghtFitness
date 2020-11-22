package come.example.myone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.textfield.TextInputLayout;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.BasePieLegendsView;
import com.razerdp.widget.animatedpieview.DefaultCirclePieLegendsView;
import com.razerdp.widget.animatedpieview.DefaultPieLegendsView;
import com.razerdp.widget.animatedpieview.callback.OnPieLegendBindListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import static android.content.ContentValues.TAG;
import static java.lang.Float.parseFloat;

/*
 * Mifflin St Jeor equation
 * @param isMale <code>true</code> for Male, <code>false</code> for Female
 * @param height in cm
 * @param weight in kg
 * @param age in years
 * @return daily energy expenditure
 */
public class CalorieCalculatorFrag extends Fragment implements View.OnClickListener {
RadioGroup group1,group2;
Button submit;
boolean isaMale;
float weightInt;
EditText height,age,weight,height_ft,height_inches,weight_lbs;
int heightInt,ageInt;
double  activityLvl,TotalCal;
TextView textView;
Spinner myspinner;
    String weightStr,heightStr, hft,hInch,Lweight;
    RadioButton FemaleButton,MaleButton,cm,inches;
    Animation fader;
    Bundle bundle = new Bundle();
    BottomSheet bottomSheet = new BottomSheet();
    TextInputLayout height_L,weight_L,height_ft_L,height_inches_L,weight_lbs_L;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_calories_cal, container, false);
        group1 =  mView.findViewById(R.id.radioGroup);
        group2 = mView.findViewById(R.id.radioGroup2);
        submit =  mView.findViewById(R.id.calculateBmr);
        height =  mView.findViewById(R.id.edheight);
        weight =  mView.findViewById(R.id.edweight);
        height_ft = mView.findViewById(R.id.edheight_Ft);
        height_inches = mView.findViewById(R.id.height_inch);
        weight_lbs = mView.findViewById(R.id.edweight_lbs);
        height_L = mView.findViewById(R.id.edheight_layout);
        weight_L = mView.findViewById(R.id.edweight_layout);
        height_ft_L = mView.findViewById(R.id.edheight_Ft_layout);
        height_inches_L = mView.findViewById(R.id.height_inch_layout);
        weight_lbs_L = mView.findViewById(R.id.edweight_lbs_layout);

        textView = mView.findViewById(R.id.genderError);
        age =  mView.findViewById(R.id.edage);
        age.setFilters(new InputFilter[]{new InputFilterMinMax("0", "70")});
        weight.setFilters(new InputFilter[]{new InputFilterMinMax("0", "400")});
        height.setFilters(new InputFilter[]{new InputFilterMinMax("0","251")});
        height_inches.setFilters(new InputFilter[]{new InputFilterMinMax("0", "11")});
        height_ft.setFilters(new InputFilter[]{new InputFilterMinMax("0", "7")});
        weight_lbs.setFilters(new InputFilter[]{new InputFilterMinMax("0", "600")});

        myspinner = mView.findViewById(R.id.activity_LVL);
        fader = AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
        MaleButton =  mView.findViewById(R.id.rbMale);
        FemaleButton =  mView.findViewById(R.id.rbFmale);
        cm = mView.findViewById(R.id.rbCM);

        inches = mView.findViewById(R.id.rbInches);
        String text = (calculateM(isaMale,heightInt,weightInt,ageInt,activityLvl));
        TotalCal = Double.parseDouble(text);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.cc_spinner_item
                ,getResources().getStringArray(R.array.activity_level));
        spinnerArrayAdapter.setDropDownViewResource(R.layout.cc_spinner_item);
        myspinner.setAdapter(spinnerArrayAdapter);
        myspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                        switch (position)
                        {
                            case 0:
                                activityLvl =  1.2;
                                bundle.putDouble("activityLvl", activityLvl);
                                bottomSheet.setArguments(bundle);
                                break;
                            case 1:
                                activityLvl = 1.375;
                                bundle.putDouble("activityLvl", activityLvl);
                                bottomSheet.setArguments(bundle);
                                break;
                            case 2:
                                activityLvl = 1.55;
                                bundle.putDouble("activityLvl", activityLvl);
                                bottomSheet.setArguments(bundle);
                                break;
                            case 3:
                                activityLvl = 1.725;
                                bundle.putDouble("activityLvl", activityLvl);
                                bottomSheet.setArguments(bundle);
                                break;
                            case 4:
                                activityLvl = 1.9;
                                bundle.putDouble("activityLvl", activityLvl);
                                bottomSheet.setArguments(bundle);
                                break;

                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT);
                    }
                });

        submit.setOnClickListener(this);

        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.rbCM:
                        height.setVisibility(View.VISIBLE);
                        height_L.setVisibility(View.VISIBLE);
                        weight.setVisibility(View.VISIBLE);
                        weight_L.setVisibility(View.VISIBLE);

                        //-------------------------------------

                        weight_lbs.setVisibility(View.INVISIBLE);
                        height_inches.setVisibility(View.INVISIBLE);
                        height_ft.setVisibility(View.INVISIBLE);
                        weight_lbs_L.setVisibility(View.INVISIBLE);
                        height_inches_L.setVisibility(View.INVISIBLE);
                        height_ft_L.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rbInches:
                        height_L.setVisibility(View.INVISIBLE);
                        height.setVisibility(View.INVISIBLE);
                        weight.setVisibility(View.INVISIBLE);
                        weight_L.setVisibility(View.INVISIBLE);

                        //-------------------------------------
                        weight_lbs.setVisibility(View.VISIBLE);
                        height_inches.setVisibility(View.VISIBLE);
                        height_ft.setVisibility(View.VISIBLE);
                        weight_lbs_L.setVisibility(View.VISIBLE);
                        height_inches_L.setVisibility(View.VISIBLE);
                        height_ft_L.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        /*
         * Checks users gender
         * */
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.rbFmale:
                        isaMale = false;
                        bundle.putBoolean("isaMale", isaMale);
                        bottomSheet.setArguments(bundle);
                        break;
                    case R.id.rbMale:
                        isaMale = true;
                        bundle.putBoolean("isaMale", isaMale);
                        bottomSheet.setArguments(bundle);
                        break;
                }
            }
        });

  return mView;

    }
public void myConverter(int x){
    switch(x) {
        case  1:
            height_inches.getText().clear();
            height_ft.getText().clear();
            weight_lbs.getText().clear();
                heightStr = height.getText().toString();
                weightStr = weight.getText().toString();
                ItoM(heightStr,weightStr);
            break;
        case  2:
            height.getText().clear();
            weight.getText().clear();
                hInch = height_inches.getText().toString();
                hft = height_ft.getText().toString();
                Lweight = weight_lbs.getText().toString();
                MtoI(hInch,hft,Lweight);
            break;
            default:
                break;
    }
}
public void ItoM(String myHeight, String myWeight) {
        if (myHeight.isEmpty()) {
        height.setError("enter height");
        height.requestFocus();
    } else if (myWeight.isEmpty()) {
        weight.setError("enter weight");
        weight.requestFocus();
    } else {
        /*
         *
         * */
        if (!myHeight.isEmpty() && !myWeight.isEmpty()&& MaleButton.isChecked() || FemaleButton.isChecked()) {
            try {
                heightInt = Integer.parseInt(myHeight);
                weightInt = Float.parseFloat(myWeight);
            } catch (NumberFormatException e) {
                Log.e(TAG, e.getMessage());
            }
            bundle.putInt("height",heightInt);
            bottomSheet.setArguments(bundle);
            bundle.putInt("age",ageInt);
            bottomSheet.setArguments(bundle);
            bundle.putFloat("weight",weightInt);
            bottomSheet.setArguments(bundle);
            bottomSheet.show(getFragmentManager(), "exampleBottomSheet");

        }
    }
}
public void MtoI(String myInch,String myHeight, String myWeight ){
        if(myHeight.isEmpty()) {
            height_ft.setError("enter height");
            height_ft.requestFocus();
        } else if (myWeight.isEmpty()) {
            weight_lbs.setError("enter weight");
            weight_lbs.requestFocus();
        }else if(myInch.isEmpty()) {
            height_inches.setError("enter weight");
            height_inches_L.requestFocus();
        }

            if (!myHeight.isEmpty() && !myWeight.isEmpty() && !myInch.isEmpty()&& MaleButton.isChecked() || FemaleButton.isChecked()) {
                try {
                    int x = Integer.parseInt(myHeight);
                    int y = Integer.parseInt(myInch);
                    float z= Float.parseFloat(myWeight);
                    x = (int) (x * 30.48);
                    y = (int) (y * 2.54);
                    z = (float) (z * 0.453592);
                    String newHeight = String.valueOf(x + y);
                    String newWeight = String.valueOf(z);
                    heightInt = Integer.parseInt(newHeight);
                    weightInt = Float.parseFloat(newWeight);
                } catch (NumberFormatException e) {
                    Log.e(TAG, e.getMessage());
                }
                bundle.putInt("height",heightInt);
                bottomSheet.setArguments(bundle);
                bundle.putInt("age",ageInt);
                bottomSheet.setArguments(bundle);
                bundle.putFloat("weight",weightInt);
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getFragmentManager(), "exampleBottomSheet");

            }
    }


    @Override
    public void onClick(View view) {
        //showEditDialog();

         begin();
    }
    public void begin(){
          String ageStr = age.getText().toString();
          if(inches.isChecked()){
              myConverter(2);
          }
          if (cm.isChecked()){
              myConverter(1);
          }

        if (MaleButton.isChecked() || FemaleButton.isChecked()){
            FemaleButton.setError(null);
            MaleButton.setError(null);
        }
     else if (MaleButton.isChecked() || FemaleButton.isChecked()) {
         FemaleButton.setError(null);
     }else{
            MaleButton.setError("");
            group1.requestFocus();
        }
         if (ageStr.isEmpty()) {
            age.setError("enter age");
            age.requestFocus();
          }else {
                      ageInt = Integer.parseInt(ageStr);
              }

          }

    /*/*
     * Mifflin St Jeor equation
     * @param isMale <code>true</code> for Male, <code>false</code> for Female
     * @param height in cm
     * @param weight in kg
     * @param age in years
     * @return daily energy expenditure
     */
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