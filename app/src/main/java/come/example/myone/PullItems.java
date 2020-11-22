package come.example.myone;

import java.util.ArrayList;
import java.util.Random;

public class PullItems {
    public String[] getworkout() {
        String pullMain[] =
                /*Main exercises */{"Barbell Bent-Over Row", "Pull Ups","Dumbbell Single Arm Row",
                "Chest-Supported Dumbbell Row","Inverted Row","Lat Pull-Downs","Chin Ups"};
        String pullAcc1[]=
                /*accessory/sub1 exercises */{"Incline Dumbbell Hammer Curl", "Dumbbell Kickbacks", "Dips","Standing Barbell Curl",
        "Zottman Curl","Dumbbell Biceps Curl","Hammer Curl"};
        String pullAcc2[]=
                /*accessory/sub2 exercises */ {"Reverse Push-Downs", "Farmer’s Walk"};

        return  shuffle(pullMain,pullAcc1,pullAcc2);
    }


    public static String[] shuffle(String[] a, String[]b, String[]c) {
        Random random = new Random();
        String tempA, tempB, tempC;
        for (int i = a.length - 1; i > 0; i--) {    //Shuffles mainPush
            int m = random.nextInt(i + 1);
            tempA = a[i];
            a[i] = a[m];
            a[m] = tempA;
        }
        for (int i = b.length - 1; i > 0; i--) {    //Shuffles pushAcc1
            int m = random.nextInt(i + 1);
            tempB = b[i];
            b[i] = b[m];
            b[m] = tempB;
        }
        for (int i = c.length - 1; i > 0; i--) {    //Shuffles pushAcc2
            int m = random.nextInt(i + 1);
            tempC = c[i];
            c[i] = c[m];
            c[m] = tempC;
        }
        ArrayList<String> mylist = new ArrayList<String>();
        mylist.add(a[0]);
        mylist.add(a[1]);
        mylist.add(b[0]);
        mylist.add(b[1]);
        mylist.add(c[0]);

        System.out.println("Original List : \n" + mylist);
        String[] array = mylist.toArray(new String[0]);

        return array;

}}
