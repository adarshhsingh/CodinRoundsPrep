package leetcode.twoPointers;

import reusables.PrintModules;

public class SringCompression {

    public static void main(String[] args) {
        char chars[] = {'a','a','a','b','b','a','a'};
        // a,a,b,b,c,c,c
        //
        System.out.println(compress(chars));
        PrintModules.print(chars);
    }

    public static int compress(char[] chars) {
        int pointerAlphabet = 0;
        int pointerNum = pointerAlphabet+1;
        // initialization
        int num = 1;
        for(int i = pointerAlphabet+1; i<chars.length; i++) {
            if(chars[pointerAlphabet] == chars[i]) {
                num++;
            } else {
                if(num>1) {
                    String s = "" + num;
                    char nums[] = s.toCharArray();
                    for (int j = 0; j < (nums.length); j++) {
                        chars[pointerNum] = nums[j];
                        ++pointerNum;
                    }
                }
                pointerAlphabet = pointerNum;
                pointerNum = pointerNum + 1;
                chars[pointerAlphabet] = chars[i];
                num = 1;
            }
        }

        if(num>1) {
            String s = "" + num;
            char nums[] = s.toCharArray();
            for (int j = 0; j < (nums.length); j++) {
                chars[pointerNum] = nums[j];
                ++pointerNum;
            }
        }
        return pointerNum;
    }
}
