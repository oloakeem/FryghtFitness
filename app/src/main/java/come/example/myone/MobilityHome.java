package come.example.myone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MobilityHome extends Fragment {
    // Array of strings...
    ListView simpleList;
    String mobTitle[] = {"Dynamic Stretching", "Static Stretching", "Foam Rolling", "Power Band"};
    String mobDesc[] = {"Brief Description", "Brief Description", "Brief Description", "Brief Description"};
    int mobImage[] = {R.drawable.dynamic_stre, R.drawable.static_stretch, R.drawable.foam_roll, R.drawable.bands_power};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.mobility_home, container, false);
            simpleList = (ListView)mView.findViewById(R.id.mobility_listview);
            MobilityAdapter mobilityAdapter = new MobilityAdapter(getContext(),mobTitle,mobDesc,mobImage);
            simpleList.setAdapter(mobilityAdapter);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = simpleList.getItemAtPosition(position);
                showEditDialog();
            }


        });

        return mView;

    }

    private void showEditDialog() {
        int newMob = 1;
        MobileDialog dialog = new MobileDialog();

        Bundle args = new Bundle();
        args.putInt("newMob", newMob);
        dialog.setArguments(args);
// setup link back to use and display
        dialog.setTargetFragment(this,1);
        dialog.show(getFragmentManager().beginTransaction(), "MyProgressDialog");


    }

}