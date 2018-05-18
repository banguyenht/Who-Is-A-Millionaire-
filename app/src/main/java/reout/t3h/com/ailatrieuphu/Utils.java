package reout.t3h.com.ailatrieuphu;

import android.annotation.SuppressLint;

import java.util.Comparator;
import java.util.List;

public class Utils {

//    public static boolean IS_CALLED = false;
//    public static boolean IS_5050 = false;
//    public static boolean IS_RESET = false;
//    public static int questionPass = 0;
//    public static boolean isHightscore;
//
//  //  public static boolean isPlaying = true;
//    public static int id;
//    public static int money;
  //  public static int questionPass = 0;
  @SuppressLint("NewApi")
  public static void sortHightscore(List<HightScore> hightScores) {
      Comparator<HightScore> comparator = new Comparator<HightScore>() {
          @Override
          public int compare(HightScore o1, HightScore o2) {
              if (o1.getName().equals(o2.getName())) {
                  //coi nhu bang nhau
                  if (o1.getQuestion_pass() == o2.getQuestion_pass()) {
                      return 0;
                  } else {
                      if (o1.getQuestion_pass() > o2.getQuestion_pass()) {
                          return -1;
                      } else {
                          return 1;
                      }
                  }
              }
              if (o1.getName().compareTo(o2.getName()) > 0) {
                  return 1;
              } else {
                  return -1;
              }
          }

      };
      hightScores.sort(comparator);

  }


}
