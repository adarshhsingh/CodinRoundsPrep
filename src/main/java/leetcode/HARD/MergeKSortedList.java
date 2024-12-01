package leetcode.HARD;

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null) return null;

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(ListNode list : lists) {
            if(lists != null) {
                priorityQueue.add(list);
            }
        }

        ListNode newHead = null;
        ListNode curr = null;

        while (!priorityQueue.isEmpty()) {
            ListNode x = priorityQueue.poll();
            if(newHead == null) {
                newHead = x;
                curr = x;
            } else {
                curr.next = x;
                curr = x;
            }
            if(x.next != null) {
                priorityQueue.add(x.next);
            }
        }
        return newHead;
    }

    public static void main(String[] args) {
        // lists = [[1,4,5],[1,3,4],[2,6]]
        // Output: [1,1,2,3,4,4,5,6]
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(5);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);

        ListNode listNode3 = new ListNode(2);
        listNode3.next = new ListNode(6);

        ListNode[] list1 = new ListNode[]{listNode1, listNode2, listNode3};

        MergeKSortedList obj = new MergeKSortedList();
        ListNode list1Output = obj.mergeKLists(list1);
        System.out.println("Output: ");
        while (list1Output != null) {
            System.out.print(list1Output.val+", ");
            list1Output  = list1Output.next;
        }
    }
}
