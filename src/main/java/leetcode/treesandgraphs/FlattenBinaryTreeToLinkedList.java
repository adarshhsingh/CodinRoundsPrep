package leetcode.treesandgraphs;

import java.util.Arrays;
import java.util.List;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the
 * right child pointer points to the next node in the list and the left child pointer is always null.
 *
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 */
public class FlattenBinaryTreeToLinkedList {

    TreeNode<Integer> previous = null;

    public void flatten(TreeNode<Integer> root) {
       // pre-order -- root, left, right
       if(root == null) return;
       TreeNode<Integer> left = root.left;
       TreeNode<Integer> right = root.right;
       if(previous !=null) {
           previous.right = root;
           previous = previous.right;
           previous.left = null;
           previous.right = null;
       } else {
           previous = root;
           previous.left = null;
           previous.right = null;
       }
       flatten(left);
       flatten(right);
    }

    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList obj = new FlattenBinaryTreeToLinkedList();

        List<List<TreeNode<Integer>>> lists = Arrays.asList(
                Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2), new TreeNode<Integer>(3)),
                Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2), new TreeNode<Integer>(5), new TreeNode<Integer>(3), new TreeNode<Integer>(4), null , new TreeNode<Integer>(6))
        );
        for (int i = 0; i < lists.size(); i++) {
            obj.previous = null;
            System.out.println("******"+i+"*******");
            BinaryTree<Integer> t = new BinaryTree<Integer>(lists.get(i));
            BinaryTree.printTree(t.root,0);
            System.out.println("******");
            obj.flatten(t.root);
            BinaryTree.printTree(t.root,0);

        }
    }
}
