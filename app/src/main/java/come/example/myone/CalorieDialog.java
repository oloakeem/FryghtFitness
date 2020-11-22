package come.example.myone;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CalorieDialog extends DialogFragment {

    private NumberPicker proPicker,carbPicker,fatPicker;
    TextView fatText,proText,carbText,totalCalTextView;
    TextView percentText;
    Button percent,finishButton;
    int minValue = 1;
    int maxValue = 20;
    int step = 5;
    int totalCal = 2500;
    Handler handler=new Handler();
    Bundle bundle = new Bundle();

    /*Briefly, if you want 5~100 arrange in NumberPicker and expected step is 5 :
    set minValue = 1, maxValue = 20 and step = 10;*/
    String[] numberValues = new String[maxValue - minValue + 1];

    public CalorieDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }
        public static CalorieDialog newInstance(String title) {
            CalorieDialog frag = new CalorieDialog();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.calorie_dialog, container);

    }
    public void onResume() {
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        totalCal = this.getArguments().getInt("newKcal");
        getDialog().getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation; /*sets fade animation*/
        getDialog().getWindow().setLayout(width, height);
        // Call super onResume after sizing

        super.onResume();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        proPicker = view.findViewById(R.id.proteinNumberPicker);
        carbPicker = view.findViewById(R.id.carbsNumberPicker);
        fatPicker = view.findViewById(R.id.fatNumberPicker);
        percentText = view.findViewById(R.id.textPercent);
        percent = view.findViewById(R.id.buttonPercent);
        fatText = view.findViewById(R.id.fatTextView);
        carbText = view.findViewById(R.id.carbTextView);
        proText = view.findViewById(R.id.proteinTextView);
        totalCalTextView = view.findViewById(R.id.totalCalTextView);

        handler.post(new Runnable(){
            @Override
            public void run() {
                int total = getProPicker()+getFatPicker()+getCarbPicker();
                if (total!= 100) {
                    resultsBad();
                    percentText.setText(String.valueOf(total));
                    carbText.setText((showCarb()+" g"));
                    fatText.setText((showFats()+" g"));
                    proText.setText((showPro()+" g"));
                    totalCalTextView.setText(String.valueOf(totalCal));

                } else {
                    resultsGood();
                    carbText.setText((showCarb()+" g"));
                    fatText.setText((showFats()+" g"));
                    proText.setText((showPro()+" g"));
                    percentText.setText(String.valueOf(total));

                }
                // upadte textView here
                handler.postDelayed(this,500); // set time here to refresh textView
            }
        });
        getProPicker();getCarbPicker();getFatPicker();
        percent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int total = getProPicker()+getFatPicker()+getCarbPicker();

                if (total!= 100) {
                    carbText.setText((showCarb()+" g"));
                    fatText.setText((showFats()+" g"));
                    proText.setText((showPro()+" g"));
                } else {

                    Intent i = new Intent()
                            .putExtra("newPro_R", (showPro()))
                            .putExtra("newFat_R", (showFats()))
                            .putExtra("newCarb_R", (showCarb()));

                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                    dismiss();

                }            }
        });

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }



    public void resultsBad() {
        if(isAdded()) {
            percentText.setTextColor(getResources().getColor(R.color.myRed));
        }}
    public void resultsGood() {
        if(isAdded()) {
            totalCalTextView.setTextColor(getResources().getColor(R.color.myBlue));
             percentText.setTextColor(getResources().getColor(R.color.myBlue));
        }}
    public int showFats(){
        //Integer division has no fractions, so 500 / 1000 = 0.5 (that is no integer!) which gets truncated to integer 0.
        float x = (int) (((double) getFatPicker() / (double) 100) * totalCal)/9f;
        int a = Math.round(x);
    return a;
    }
    public int showPro( ){
        float x = (int) (((double) getProPicker() / (double) 100) * totalCal)/4f;
        int a = Math.round(x);

        return a;
    }
    public int showCarb( ){
        float x = (int) (((double) getCarbPicker() / (double) 100) * totalCal)/4f;
        int a = Math.round(x);

        return a;
    }


    public  int getProPicker() {
        for (int i = 0; i <= maxValue - minValue; i++) {
            numberValues[i] = String.valueOf((minValue + i) * step);
        }
        proPicker.setMinValue(minValue);
        proPicker.setMaxValue(maxValue);
        proPicker.setWrapSelectorWheel(false);
        proPicker.setDisplayedValues(numberValues);
        int myPro = proPicker.getValue()*step;

        return myPro;
    }

    public int getCarbPicker() {
        for (int i = 0; i <= maxValue - minValue; i++) {
            numberValues[i] = String.valueOf((minValue + i) * step);
        }
        carbPicker.setMinValue(minValue);
        carbPicker.setMaxValue(maxValue);
        carbPicker.setWrapSelectorWheel(false);
        carbPicker.setDisplayedValues(numberValues);
        int myCarb = carbPicker.getValue()*step;

        return myCarb;
    }

    public int getFatPicker() {
        for (int i = 0; i <= maxValue - minValue; i++) {
            numberValues[i] = String.valueOf((minValue + i) * step);
        }
        fatPicker.setMinValue(minValue);
        fatPicker.setMaxValue(maxValue);
        fatPicker.setWrapSelectorWheel(false);
        fatPicker.setDisplayedValues(numberValues);
        int myFat = fatPicker.getValue()*step;

        return myFat;
    }
}

