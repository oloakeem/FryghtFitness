package come.example.myone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>{
    String set[];
    String  name[];
    Context context;
    public ExerciseAdapter(Context ct, String nameT[],String setT[]){
        context = ct;
        set = setT;
        name = nameT;



    }
    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.myText1.setText(name[position]);
        holder.myText2.setText(set[position]);
        holder.myText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = holder.myText1.getText().toString();
                Intent intent = new Intent("custom-event-name");
                // You can also include some extra data.
                intent.putExtra("quantity", value);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
               // Toast.makeText(context, value ,Toast.LENGTH_SHORT).show();

                }
        });

    }
    @Override
    public int getItemCount() {
        return 5;
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView myText1;
        TextView myText2;
       // ImageView myImage;

        private SparseBooleanArray selectedItems = new SparseBooleanArray();

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.exerciseName);
            myText2 = itemView.findViewById(R.id.exerciseSR);

        }

    }

}
