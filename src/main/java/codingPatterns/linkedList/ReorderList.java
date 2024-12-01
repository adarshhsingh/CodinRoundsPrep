package codingPatterns.linkedList;

class LinkedListNode {
    public int data;
    public LinkedListNode next;
    // Constructor will be used to make a LinkedListNode type object
    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}
public class ReorderList {

    public static void main(String[] args) {
        LinkedListNode node1 = new LinkedListNode(6);
        System.out.println("BEFORE ");


        reorderList(node1);
        System.out.println("AFTER ");
        while(node1 != null) {
            System.out.print(node1.data+ " ,");
            node1 = node1.next;
        }

        /*System.out.println(" \nNEXT CASE");

        LinkedListNode node2 = new LinkedListNode(6);
        node2.next = new LinkedListNode(8);
        node2.next.next = new LinkedListNode(7);
        node2.next.next.next = new LinkedListNode(5);
        System.out.println("AFTER ");
        while(node2 != null) {
            System.out.print(node2.data+ " ,");
            node2 = node2.next;
        }

        reorderList(node2);
        System.out.println("AFTER ");
        while(node2 != null) {
            System.out.print(node2.data+ " ,");
            node2 = node2.next;
        }*/
    }
    public static LinkedListNode reorderList(LinkedListNode head) {

        // find the middle node
        LinkedListNode prevSlow = null;
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while(fast != null && fast.next != null) {
            prevSlow = slow;
            System.out.println("previous node: "+  prevSlow.data );
            slow = slow.next;
            fast = fast.next.next;
        }

        prevSlow.next = null;
        System.out.println("Middle node: "+  slow.data );
        LinkedListNode middle = slow;

        // start reversing from middle
        LinkedListNode prev = null;
        LinkedListNode curr = middle;

        while( curr != null ) {
            LinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        LinkedListNode head2 = prev;

        // join the two LinkedListNode
        LinkedListNode head1 = head;
        LinkedListNode next1 = head1.next;
        LinkedListNode next2 = head2.next;

        while(head1 != null && head2 != null) {
            next1 = head1.next;
            next2 = head2.next;
            head1.next = head2;
            if(next1 != null)head2.next = next1;
            head1 = next1;
            head2 = next2;
        }

        return head;
    }
}
