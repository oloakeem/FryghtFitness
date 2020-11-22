package come.example.myone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ExerciseGenerator extends Fragment {
    GridLayout grid;
    String exercisePicked = null;
ImageView imageView,imageView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_exercise_generator, container, false);
        grid  = (GridLayout) mView.findViewById(R.id.mainGrid);
        imageView = mView.findViewById(R.id.sample);
        //imageView.setColorFilter(getResources().getColor(R.color.greyTint), android.graphics.PorterDuff.Mode.MULTIPLY);
        imageView2 = mView.findViewById(R.id.sample2);
        //imageView2.setColorFilter(getResources().getColor(R.color.greyTint), android.graphics.PorterDuff.Mode.MULTIPLY);
        setSingleEvent(grid);
        return mView;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.training_level_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.option1:
                doOptionOne();
                return true;
            case R.id.option2:
                doOptionTwo();
                return true;
            case R.id.option3:
                doOptionThree();
                return true;
            default:
                return false;
        }
    }

    private void doOptionOne() {
       String muscleTraining = "Strength";
        Intent intent = new Intent(getActivity(), DisplayExercise.class);
        intent.putExtra("exerciseKey", exercisePicked);
        intent.putExtra("muscleKey", muscleTraining);
        startActivity(intent);
    }
    private void doOptionTwo() {
        String   muscleTraining = "Hypertrophy";
        Intent intent = new Intent(getActivity(), DisplayExercise.class);
        intent.putExtra("exerciseKey", exercisePicked);
        intent.putExtra("muscleKey", muscleTraining);
        startActivity(intent);


    }
    private void doOptionThree() {
        String  muscleTraining = "Endurance";
        Intent intent = new Intent(getActivity(), DisplayExercise.class);
        intent.putExtra("exerciseKey", exercisePicked);
        intent.putExtra("muscleKey", muscleTraining);

        startActivity(intent);
    }
    private void setSingleEvent(GridLayout grid) {
            for (int i = 0; i < grid.getChildCount(); i++) {
                final CardView cardView = (CardView) grid.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        switch (view.getId()) {
                            case R.id.EXC1:
                                exercisePicked = "Push";
                                registerForContextMenu(view);
                                view.setLongClickable(false);
                                getActivity().openContextMenu(view);
                                break;
                            case R.id.EXC2:
                                exercisePicked = "Pull";
                                registerForContextMenu(view);
                                view.setLongClickable(false);
                                getActivity().openContextMenu(view);
                                break;
                            case R.id.EXC3:
                                exercisePicked = "Legs";
                                registerForContextMenu(view);
                                view.setLongClickable(false);
                                getActivity().openContextMenu(view);
                                break;
                            case R.id.EXC4:
                                exercisePicked = "cor";
                            Toast.makeText(getActivity(), "Feature locked" ,Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                break;
                        }


                    }
                });
            }

        }}