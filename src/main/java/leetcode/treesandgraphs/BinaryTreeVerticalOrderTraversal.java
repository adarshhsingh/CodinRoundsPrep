package leetcode.treesandgraphs;

import java.util.*;
 class pair {
    int position;
    TreeNode<Integer> node;

    pair(int position, TreeNode node) {
        this.position = position;
        this.node = node;
    }
}

public class BinaryTreeVerticalOrderTraversal {
    List<List<Integer>> verticalOrder;
    Map<Integer, List<Integer>> verticalPosition;


    public List<List<Integer>> verticalOrder(TreeNode root) {
        // current position = 0
        // we move left with position - 1
        // we move right with position + 1
        List<List<Integer>> verticalOrder;
        Map<Integer, List<Integer>> verticalPosition;
        verticalPosition = new HashMap<>();
        verticalOrder = new ArrayList<>();
        int max = 0;
        int min = 0;

        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(0, root));

        while (!queue.isEmpty()) {
            // insert left with position-1
            // insert right with position+1
            pair current = queue.poll();
            if(verticalPosition.get(current.position) == null) {
                verticalPosition.put(current.position, new ArrayList<>());
            }
            verticalPosition.get(current.position).add(current.node.val);
            min = Math.min(min, current.position);
            max = Math.max(max, current.position);
            if(current.node.left != null) {
                queue.add(new pair(current.position-1, current.node.left));
            }
            if(current.node.right != null) {
                queue.add(new pair(current.position+1, current.node.right));
            }
        }
        int index = 0;
        for(int i = min; i <= max; i++) {
            verticalOrder.add(verticalPosition.get(i));
        }

        return verticalOrder;
    }

    public void traverse(TreeNode node, int position) {

    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode<Integer>(3);
        node.left = new TreeNode<>(9);
        node.right = new TreeNode<>(8);
        node.left.left = new TreeNode<>(4);
        node.left.right = new TreeNode<>(0);
        node.left.right.left= new TreeNode<>(5);
        node.right.right = new TreeNode<>(7);
        node.right.left = new TreeNode<>(1);
        node.left.right.right= new TreeNode<>(2);
        BinaryTreeVerticalOrderTraversal obj = new BinaryTreeVerticalOrderTraversal();
        System.out.println(obj.verticalOrder(node));
    }




}
