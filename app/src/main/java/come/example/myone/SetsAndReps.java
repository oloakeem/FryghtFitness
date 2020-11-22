package come.example.myone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SetsAndReps {

    static String[] intensity(String level) {
        ArrayList<String> mylist = new ArrayList<String>();
        Random random = new Random();

        if (level.equals("Hypertrophy")) {
            int lowS = 3;
            int highS = 5;
            int lowR = 9;
            int highR = 13;
            for (int i = 0; i < 5; i++) {
                int set = random.nextInt(highS-lowS) + lowS;
                int reps = random.nextInt(highR-lowR) + lowR;
                    mylist.add(i,"Sets x" + set + " " + "Reps x" + reps);
            }


        }else if(level.equals("Strength")){
            int lowS = 3;
            int highS = 4;
            int lowR = 3;
            int highR = 6;
            for (int i = 0; i < 5; i++) {
                int set = random.nextInt(highS-lowS) + lowS;
                int reps = random.nextInt(highR-lowR) + lowR;
                mylist.add(i,"Sets x" + set + " " + "Reps x" + reps);
            }
        }else if (level.equals("Endurance")){
            int lowS = 3;
            int highS = 5;
            int lowR = 12;
            int highR = 16;
            for (int i = 0; i < 5; i++) {
                int set = random.nextInt(highS-lowS) + lowS;
                int reps = random.nextInt(highR-lowR) + lowR;
                mylist.add(i,"Sets x" + set + " " + "Reps x" + reps);
            }

        }
        String[] array = mylist.toArray(new String[0]);

    return array;}
}