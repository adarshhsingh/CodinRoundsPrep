package datastructures.arrays.old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFiletoArray {
    public static void main(String[] args) {
        String fileName = "/Users/adarshsingh/workspace/DSARound/src/main/java/datastructures/arrays/input.txt";
        List<Integer> numberList = readFileToList(fileName);
        int[] numberArray = listToArray(numberList);

        // Print the array
        for (int num : numberArray) {
            System.out.print(num + " ");
        }
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
}

