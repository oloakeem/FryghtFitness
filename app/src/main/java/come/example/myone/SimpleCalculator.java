package come.example.myone;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleCalculator extends Fragment {
    EditText input1;
    TextView input2,input3;
    RadioGroup radioGroup;
    RadioButton KtoL, LtoK;
    boolean LSB;
    TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.simple_calculator, container, false);
        input1 = mView.findViewById(R.id.input1);
        input2 = mView.findViewById(R.id.input2);
        input3 = mView.findViewById(R.id.input3);

        input1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "999")});

        radioGroup = mView.findViewById(R.id.input_switch);
        LtoK = mView.findViewById(R.id.input_LtoK);
        KtoL = mView.findViewById(R.id.input_KtoL);
        textView = mView.findViewById(R.id.input_info);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected

                        switch (checkedId) {
                            case R.id.input_LtoK:
                                LbsToKgs();
                                break;
                            case R.id.input_KtoL:
                                KgstoLbs();
                            break;
                        }

                    }
                });
        return mView;
    }

    public void LbsToKgs() {
        textView.setText("Converting Lb to Kg");
        input1.getText().clear();
        input2.setText("");
        input3.setText("");
        input3.setVisibility(View.INVISIBLE);
        input2.setVisibility(View.VISIBLE);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int lbs;
                long newLBS;
                try {
                    lbs = Integer.parseInt(input1.getText().toString());
                    newLBS = (Math.round(lbs * 0.453592));
                    input2.setText(newLBS + "kg");

}catch(NumberFormatException ex) {
                }
                }
                }, 0, 1000);//put here time 1000 milliseconds=1 second
        return;

            }
    public void KgstoLbs() {
        textView.setText("Converting Kg to Lb");
        input1.getText().clear();
        input2.setText("");
        input3.setText("");
        input2.setVisibility(View.INVISIBLE);
        input3.setVisibility(View.VISIBLE);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                int kgs = 0;
                long newKgs;
                try {

                    kgs = Integer.parseInt(input1.getText().toString());
                    newKgs = (Math.round(kgs * 2.20462));
                    input3.setText(newKgs + "lb");
                } catch (NumberFormatException ex) {
                }
            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second
        return;

    }
}
