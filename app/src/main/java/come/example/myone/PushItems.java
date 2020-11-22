package come.example.myone;

import java.util.ArrayList;
import java.util.Random;

public class PushItems {
  public String[] getworkout() {
        String pushMain[] =
                /*Main exercises */{"Push Ups", "Incline Bench Press", "Close-Grip Bench Press","Ball Push-Ups",
                "Parallel Dips","Decline Bench Press"};
        String pushAcc1[]=
                /*accessory/sub1 exercises */{"Kneeling Triceps Pressdowns", "Half-Bench Skullcrusher", "EZ Bar Skull Crushers"
                ,"Triceps kickback" ,"Overhead Triceps Extension" ,"Chair Dips"};
        String pushAcc2[]=
        /*accessory/sub2 exercises */ {"Overhead Press With a Barbell", "Cable Reverse Flye","One-Arm Cable Lateral Raise"
        ,"Bent-Over Dumbbell Lateral Raise","Face Pull","Dumbbell Lateral Raise"};

      return  shuffle(pushMain,pushAcc1,pushAcc2);
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
        ArrayList<String>  mylist = new ArrayList<String>();
        mylist.add(a[0]);
        mylist.add(a[1]);
        mylist.add(b[0]);
        mylist.add(b[1]);
        mylist.add(c[0]);

        System.out.println("Original List : \n" + mylist);
        String[] array = mylist.toArray(new String[0]);

return array;
    }
}







