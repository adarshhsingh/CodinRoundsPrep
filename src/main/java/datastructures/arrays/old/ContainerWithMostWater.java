package datastructures.arrays.old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints
 * of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int height[] = {1,8,6,2,5,4,8,3,7};
        long startTime = System.currentTimeMillis();
        System.out.println(maxArea(height));
        long endTime = System.currentTimeMillis();
        System.out.println("Time took in seconds: "+ (endTime-startTime));
        height = new int[]{1, 1};
        startTime = System.currentTimeMillis();
        System.out.println(maxArea(height));
        endTime = System.currentTimeMillis();
        System.out.println("Time took in " + height.length +" seconds: "+ (endTime-startTime));

        // write code to read file input.txt into an array

        String fileName = "/Users/adarshsingh/workspace/DSARound/src/main/java/datastructures/arrays/input.txt";
            List<Integer> numberList = readFileToList(fileName);
            height = listToArray(numberList);
        System.out.println(maxArea(height));
        endTime = System.currentTimeMillis();
        System.out.println("Time took in " + height.length +" seconds: "+ (endTime-startTime));

        }

        private static List<Integer> readFileToList(String fileName) {
            List<Integer> numberList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine();
                String[] numbers = line.split(",");
                for (String num : numbers) {
                    numberList.add(Integer.parseInt(num.trim()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return numberList;
        }

        private static int[] listToArray(List<Integer> list) {
            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }


    public static int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length -1;

        while (i<j) {
            int area = (j-i)*Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, area);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}
