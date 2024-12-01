package educative;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;

public class FastAndSlowPointerPatternProblems {

    public static void main(String[] args) {
        FastAndSlowPointerPatternProblems obj = new FastAndSlowPointerPatternProblems();
        ListNode node = new ListNode(7);
        System.out.println(obj.getMiddleOfLinkList(node).val);
        node.next = new ListNode(10);
        System.out.println(obj.getMiddleOfLinkList(node).val);
        node.next.next = new ListNode(3);
        System.out.println(obj.getMiddleOfLinkList(node).val);
        node.next.next.next = new ListNode(15);
        System.out.println(obj.getMiddleOfLinkList(node).val);
        node.next.next.next.next = new ListNode(24);
        System.out.println(obj.getMiddleOfLinkList(node).val);

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(7);
        node2.next.next.next = new ListNode(8);

        ListNode sum = obj.addTwoNumbers(node1, node2);
        System.out.print('[');
        while (sum !=null) {
            System.out.print(sum.val);
            if(sum.next!=null) System.out.print(',');
            sum = sum.next;
        }
        System.out.print(']');


        ListNode newHead = obj.reverseLinkList(node);
        System.out.print('[');
        while (newHead !=null) {
            System.out.print(newHead.val);
            if(newHead.next!=null) System.out.print(',');
            newHead = newHead.next;
        }
        System.out.print(']');


        ListNode merge = obj.mergeListNodes(node1, node2);
        System.out.print('[');
        while (merge !=null) {
            System.out.print(merge.val);
            if(merge.next!=null) System.out.print(',');
            merge = merge.next;
        }
        System.out.print(']');


        node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        obj.reorderList(node1);
        node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);
        obj.reorderList(node1);

        System.out.println(isHappyNumber(23));
        System.out.println(isHappyNumber(2));
        System.out.println(isHappyNumber(28));

    }

    // FastAndSlowPointerPatternProblems given head of singly linked list, return the middle node of the linked list
    public ListNode getMiddleOfLinkList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // if only single node, return the first node
        // if only two node, return first node
        // if 3 node, return 2nd
        // if 4 node, return 2nd
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // FastAndSlowPointerPatternProblems check magic number

    public static boolean isHappyNumber(int n) {
        int slowPointer = n;
        int fastPointer = sumOfSquaredDigits(n);

        while (fastPointer != 1 && slowPointer != fastPointer) {
            slowPointer = sumOfSquaredDigits(slowPointer);
            fastPointer = sumOfSquaredDigits(sumOfSquaredDigits(fastPointer));
        }

        return fastPointer == 1;
    }

    public static int sumOfSquaredDigits(int n) {
       int totalSum = 0;
       while (n > 0) {
           int digit = n % 10;
           n = n / 10;
           totalSum += Math.pow(digit, 2);
       }
       return totalSum;
    }




    /**
     * You are given the head of a singly linked-list. The list can be represented as:
     *
     * L0 → L1 → … → Ln - 1 → Ln
     * Reorder the list to be on the following form:
     *
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
     * @param head
     */
    public void reorderList(ListNode head) {
        // find middle
        // reverse after middle
        // merge the two list
        System.out.println("******");
        ListNode middle = getMiddleOfLinkList(head);
        ListNode reversedHead = reverseLinkList(middle.next);
        middle.next = null;
        ListNode merged = mergeListNodes(head, reversedHead);
        System.out.print('[');
        while (merged != null) {
            System.out.print(merged.val);
            if (merged.next != null) System.out.print(',');
            merged = merged.next;
        }
        System.out.print(']');
    }

    private ListNode mergeListNodes(ListNode head, ListNode head2) {
        ListNode start = head; // 1
        // head is either larger by 1 or equal to head2 length
        while (head != null && head2 != null) {    // 2,4
            ListNode temp = head.next; // 2  // 3
            head.next = head2; //5  // 4
            head2 = head2.next; //4  //null
            head.next.next = temp; //2 //3
            head = temp; //2 //3
        }
        return start;
    }

    private ListNode reverseLinkList(ListNode head) {
        ListNode currentNode = head;
        ListNode prevNode = null;
        ListNode nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
        return prevNode;
    }


    // this is not a slow-fast pointer question, just a linkList
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // add first and move ahead, once one is null - keep moving till you go to end of larger one

        ListNode sum = new ListNode();
        ListNode head = sum;
        int carry = 0;
        while (l1 != null && l2 != null) {
            sum.val = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
            if(l1 != null || l2 != null || carry != 0) {
                sum.next = new ListNode();
                sum = sum.next;
            }
        }
        ListNode remaining = l1 == null? l2:l1;
        while (remaining !=null) {
            sum.val = (remaining.val + carry) % 10;
            carry = (remaining.val + carry) / 10;
            remaining = remaining.next;
            if(remaining != null || carry != 0) {
                sum.next = new ListNode();
                sum = sum.next;
            }
        }

        if(carry != 0 ) {
            sum.val = carry;
        }

        return head;
    }

}
