package come.example.myone;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NutritionGrid extends Fragment {
    SharedPreferences sharedpreference;

    private TextView summary;
    GridLayout grid;
    ImageView imageView;
    private StringBuilder text = new StringBuilder();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_weight_cal,container,false);
        grid  = (GridLayout) mView.findViewById(R.id.NGrid);
        setSingleEvent(grid);
        sharedpreference= PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        imageView = mView.findViewById(R.id.mytesting);
       // imageView.setColorFilter(getResources().getColor(R.color.greyTint), android.graphics.PorterDuff.Mode.MULTIPLY);

        /*Section gives the first introduction paragraph of the Nutrition/Diet layout */
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open("sample_text.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getActivity(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

            TextView output = (TextView)  mView.findViewById(R.id.paragraph);
            output.setText((CharSequence) text);

        }

        return mView;

    }

    private void setSingleEvent(GridLayout grid) {
        for (int i= 0; i < grid.getChildCount(); i++){
            final CardView cardView = (CardView) grid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String dietPicked = null;
                    String imagePicked = null;
                    switch (view.getId()){
                        case R.id.CV1:
                            dietPicked = "IIFYM";
                            imagePicked = "iffym_image";
                           // Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
                                break;
                        case R.id.CV2:
                         //   dietPicked = "Keto";
                            dietPicked = "IIFYM";

                            imagePicked = "keto_image";

                            //  Toast.makeText(getActivity(),"2",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.CV3:
                            imagePicked = "vegan_image";
                            dietPicked = "IIFYM";

                            //   dietPicked = "Vegan";

                            //Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.CV4:
                            imagePicked = "vegeterian_image";
                            dietPicked = "IIFYM";

                            // dietPicked = "Vegetarian";

                           // Toast.makeText(getActivity(),"4",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.CV5:
                           // dietPicked = "LowCarb";
                            imagePicked = "lowcarb_image";
                            dietPicked = "IIFYM";

                          //  Toast.makeText(getActivity(),"5",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.CV6:
                          //  dietPicked = "Paleo";
                            imagePicked = "paleo_image";
                            dietPicked = "IIFYM";

                            //Toast.makeText(getActivity(),"6",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.CV7:
                           // dietPicked = "Glutten";
                            imagePicked = "glutten_image";
                            dietPicked = "IIFYM";

                           // Toast.makeText(getActivity(),"7",Toast.LENGTH_LONG).show();
                            break;
                            case R.id.CV8:
                                dietPicked = "NoSugar";
                                imagePicked = "nosugar_image";
                                dietPicked = "IIFYM";

                               // Toast.makeText(getActivity(),"8",Toast.LENGTH_LONG).show();
                            break;


                        default:
                            break;
                    }

                    Intent intent = new Intent(getActivity(),DietInfo.class);
                    intent.putExtra("some_key", imagePicked);
                    intent.putExtra("some_other_key", dietPicked);
                    startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());


                }

            });
        }



    }
}
