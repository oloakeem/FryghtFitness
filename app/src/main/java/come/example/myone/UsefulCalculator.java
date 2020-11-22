package come.example.myone;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class UsefulCalculator extends Fragment {

    GridLayout grid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.useful_calculators, container, false);
        grid = (GridLayout) mView.findViewById(R.id.usefulCal);
        setSingleEvent(grid);


    return mView;}

        private void setSingleEvent(GridLayout grid) {
            for (int i= 0; i < grid.getChildCount(); i++) {
                final CardView cardView = (CardView) grid.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment selectedFragment = null;

                        switch (view.getId()) {
                            case R.id.calculator1:
                                selectedFragment = new CalorieCalculatorFrag();
                                break;
                            case R.id.calculator2:
                                selectedFragment = new SimpleCalculator();

                                break;


                        }
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                        transaction.replace(R.id.fragment_container,selectedFragment);

                //   transaction.addToBackStack(tag);
                        transaction.commit();
                        //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        //  selectedFragment).setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).commit();

                    }
                });
            }}}