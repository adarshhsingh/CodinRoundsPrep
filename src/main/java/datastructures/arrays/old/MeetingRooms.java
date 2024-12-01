package datastructures.arrays.old;

import math.MathModule;
import reusables.PrintModules;

import java.util.Map;

/**
 * Given an array of meeting time intervals,
 * intervals where intervals[I] = [startI, endI],
 * return the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * Constraints:
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class MeetingRooms {
    public static void main(String[] args) {
        int n[][] = {{0,30},{5,10},{15,20}};
        PrintModules.print(minMeetingRooms(n));
        n = new int[][]{{7, 10}, {2, 4}};
        PrintModules.print(minMeetingRooms(n));
        n = new int[][]{{9, 10}, {4, 9}, {4, 11}};
        PrintModules.print(minMeetingRooms(n));
    }

    public static int minMeetingRooms(int[][] intervals) {
        String n[] = new String[1000000];
        int minT = 1000000;
        int maxT = 0;
        int maxRoom = 0;
        for(int i=0;i<intervals.length;i++){
            n[intervals[i][0]] += "1";
            n[intervals[i][1]] += "2";
            minT = Math.min(minT, intervals[i][0]);
            maxT = Math.max(maxT, intervals[i][1]);
        }
        int roomNeed = 0;
        for(int i=minT; i<=maxT;i++) {
            if(n[i]!=null) {
                char c[] = n[i].toCharArray();
                for (char x : c) {
                    if(x == '1') roomNeed++;
                    if(x == '2') roomNeed--;
                }
                maxRoom = Math.max(roomNeed, maxRoom);
            }
        }
        return maxRoom;
    }
}
