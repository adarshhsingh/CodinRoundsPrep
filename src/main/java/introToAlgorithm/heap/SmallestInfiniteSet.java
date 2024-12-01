package introToAlgorithm.heap;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class pair {
    int x;
    int y;
    public pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "[x ="+x+", y= "+y+"]";
    }
}
public class SmallestInfiniteSet {

    public static void main(String[] args) {

        int arr[] = {3,7,1,9,2,0};
        int k = 3;

        int i  = 5;
        long j = 10;
        int r = (int) Math.ceilDiv(j,i);
        System.out.println(r);

        PriorityQueue<pair> queue = new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.x - o2.x;
            }
        });
        int index = 0;
        for(int m:arr) {
            queue.add(new pair(m, index++));
        }


        System.out.println(queue);
        pair p = queue.peek();
        System.out.println(p);
        queue.remove(p);
        System.out.println(queue);

        //System.out.println(queue2.poll());



        /**HashSet<Integer> hashSet = new HashSet<>();

        List<Point> set = new ArrayList<>();

        set.add(new Point(4,10));
        set.add(new Point(3,5));
        set.add(new Point(1,2));
        set.add(new Point(2,7));

        Collections.sort(set, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y-o2.y;
            }
        });

        System.out.println(set);




        hashSet.add(1);
        hashSet.add(3);
        priorityQueue.add(8);
        priorityQueue.add(1);
        priorityQueue.add(3);
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());

        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b-a;
            }
        });
        priorityQueue1.add(1);
        priorityQueue1.add(3);
        System.out.println(priorityQueue1);
        System.out.println(priorityQueue1.peek());*/
    }




}
