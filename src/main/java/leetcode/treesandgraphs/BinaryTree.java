package leetcode.treesandgraphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinaryTree<T> {
    TreeNode<T> root;

    BinaryTree(List<TreeNode<T>> ListOfNodes) {
        root = createBinaryTree(ListOfNodes);
    }

    private TreeNode<T> createBinaryTree(List<TreeNode<T>> ListOfNodes) {
        if (ListOfNodes.isEmpty()) {
            return null;
        }

        // Create the root node of the binary tree
        TreeNode<T> root = new TreeNode<>(ListOfNodes.get(0).val);

        // Create a queue and add the root node to it
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);

        // Start iterating over the list of ListOfNodes starting from the second node
        int i = 1;
        while (i < ListOfNodes.size()) {
            // Get the next node from the queue
            TreeNode<T> curr = q.remove();

            // If the node is not null, create a new TreeNode object for its left child,
            // set it as the left child of the current node, and add it to the queue
            if (ListOfNodes.get(i) != null) {
                curr.left = new TreeNode<>(ListOfNodes.get(i).val);
                q.add(curr.left);
            }

            i++;

            // If there are more ListOfNodes in the list and the next node is not null,
            // create a new TreeNode object for its right child, set it as the right child
            // of the current node, and add it to the queue
            if (i < ListOfNodes.size() && ListOfNodes.get(i) != null) {
                curr.right = new TreeNode<>(ListOfNodes.get(i).val);
                q.add(curr.right);
            }

            i++;
        }

        // Return the root of the binary tree
        return root;
    }

    public static void printTree(TreeNode head, int level) {
        if(head==null)return;
        printTree(head.right, level+1);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.print(head.val);
        System.out.println();
        printTree(head.left, level+1);
    }
}
