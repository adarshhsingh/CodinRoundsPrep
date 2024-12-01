package datastructures.arrays.old;

import reusables.PrintModules;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

/**
 * Given a time represented in the format "HH:MM", form the next closest
 * time by reusing the current digits. There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid.
 * For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: time = "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39,
 * which occurs 5 minutes later.
 * It is not 19:33, because this occurs 23 hours and 59 minutes later.
 *
 *
 * Example 2:
 *
 * Input: time = "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since
 * it is smaller than the input time numerically.
 *
 *  // 12:09 -> 12:10
 *
 * Constraints:
 *
 * time.length == 5
 * time is a valid time in the form "HH:MM".
 * 0 <= HH < 24
 * 0 <= MM < 60
 */
public class NextClosestTime {

    final static int MAX_HH = 23;
    final static int MAX_MM = 59;

    public static void main(String[] args) {
        String time[] = {"04:23", "03:23", "11:19", "01:22", "21:22","12:22", "23:59"};
        for (String inputTime : time) {
            PrintModules.print("Next Time for "+inputTime+" is : "+nextClosestTime(inputTime));
        }
    }

    public static String nextClosestTime(String time) {
        /*
        List<String> validMM = validMMFromTime(time);
        List<String> validHH = validHHFromTime(time);
        return getNextMinTime(validHH,validMM,time);
        */
        char nums[] = {time.charAt(0), time.charAt(1), time.charAt(3), time.charAt(4)};
        Arrays.sort(nums);
        boolean validNum[] = new boolean[10];
        for (int i=0;i<10;i++){
            if(time.indexOf(String.valueOf(i))!= -1) validNum[i] = true;
        }

        // case when only last minute change would get minimum
        int i= Integer.parseInt(time.substring(4,5));
        while(true) {
            i++;
            if (i>9) break;
            if(validNum[i] == true) {
                return (time.charAt(0)+""+time.charAt(1)+":"+time.charAt(3)+""+i);
            }
        }

        // case when minute first digit of 2 number minute can change
        i= Integer.parseInt(time.substring(3,4));
        while (true){
            i++;
            if (i>5) break;
            if(validNum[i] == true) {
                return (time.charAt(0)+""+time.charAt(1)+":"+i+nums[0]);
            }
        }

        // case when last digit of hour can change
        i= Integer.parseInt(time.substring(1,2));
        while (true){

            i++;
            if ((time.charAt(0)=='1' || time.charAt(0)=='0') && i>9) break;
            if ((time.charAt(0)=='2') && i>3) break;

            if(validNum[i] == true) {
                return (time.charAt(0)+""+i+":"+nums[0]+nums[0]);
            }

        }

        // case when first digit of hour has to change
        i= Integer.parseInt(time.substring(0,1));
        while (true){
            i++;
            if (i>2) break;
            if(validNum[i] == true) {
                return (i+""+nums[0]+":"+nums[0]+""+nums[0]);
            }
        }

        return (nums[0]+""+nums[0]+":"+nums[0]+""+nums[0]);
    }



    private static String getNextMinTime(List<String> validHH, List<String> validMM, String time) {
        String closest = "23:59";
        //System.out.println("Checking combination: ");
        int found=0;
        List<String> validTime = new ArrayList<>();
        boolean foundTime= false;
        boolean foundNext= false;
        int index = 0;
        for (int i=0;i<validHH.size();i++) {
            if (foundNext) break;
            for (int j=0;j<validMM.size();j++) {
                String checkTime = validHH.get(i)+":"+validMM.get(j);
                //System.out.println(checkTime);
                validTime.add(checkTime);
                index++;
                if (foundTime) {
                    foundNext = true;
                    //System.out.println("Found the time and next "+ checkTime);
                    break;
                }
                if (checkTime.equals(time)) {
                    foundTime = true;
                    //System.out.println("Found the time "+ checkTime);
                }
            }
        }
        if(foundTime && foundNext) {
            return validTime.get(index-1);
        }

        return validTime.get(0);

    }

    private static List<String> validHHFromTime(String time) {
        List<String> validMM = new ArrayList<>();
        for (int i = 0; i<24; i++) {
            if(i<10){
                if(time.indexOf(String.valueOf(i))!=-1 && time.indexOf("0")!=-1) validMM.add("0"+i);
            } else {
                if((time.indexOf(String.valueOf(i/10))!=-1) &&
                        (time.indexOf(String.valueOf(i%10))!=-1)) {
                    validMM.add(String.valueOf(i));
                }
            }
        }
        return validMM;
    }

    private static List<String> validMMFromTime(String time) {
        List<String> validMM = new ArrayList<>();
        for (int i = 0; i<60; i++) {
            if(i<10){
                if(time.indexOf(String.valueOf(i))!=-1 && time.indexOf("0")!=-1) validMM.add("0"+i);
            } else {
                if((time.indexOf(String.valueOf(i/10))!=-1) &&
                        (time.indexOf(String.valueOf(i%10))!=-1)) {
                    validMM.add(String.valueOf(i));
                }
            }
        }
        return validMM;
    }
}
