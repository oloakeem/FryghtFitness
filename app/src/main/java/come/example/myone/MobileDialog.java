package come.example.myone;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

public class MobileDialog extends DialogFragment {
    public MobileDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }
    public static MobileDialog newInstance(String newMob) {
        MobileDialog frag = new MobileDialog();
        Bundle args = new Bundle();
        args.putString("newMob", newMob);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.mobility_dialog, container);

    }
    public void onResume() {
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation; /*sets fade animation*/
        getDialog().getWindow().setLayout(width, height);
        // Call super onResume after sizing

        super.onResume();
    }
}
