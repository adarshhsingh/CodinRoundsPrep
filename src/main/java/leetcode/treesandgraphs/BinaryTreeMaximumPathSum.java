package leetcode.treesandgraphs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/3022/

public class BinaryTreeMaximumPathSum {
    int maxsum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxsum = Integer.MIN_VALUE;
        calculateMaxPathSum(root);
        return maxsum;
    }

    private int calculateMaxPathSum(TreeNode root) {
        if(root == null) return 0;
        int left = (root.left==null)?0: Math.max(calculateMaxPathSum(root.left),0);
        int right = (root.right==null)?0: Math.max(calculateMaxPathSum(root.right),0);
        maxsum = Math.max(maxsum, Math.max((int)root.val,left+(int)root.val+right));
        return Math.max((int)root.val, (int)root.val+Math.max(left,right));
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum obj = new BinaryTreeMaximumPathSum();
        List<List<TreeNode<Integer>>> lists = Arrays.asList(
                Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2), new TreeNode<Integer>(3)),
                Arrays.asList(new TreeNode<Integer>(2), new TreeNode<Integer>(-1)),
                Arrays.asList(new TreeNode<Integer>(-2)),
                Arrays.asList(new TreeNode<Integer>(-2), new TreeNode<Integer>(1)),
                Arrays.asList(new TreeNode<Integer>(-2), new TreeNode<Integer>(-1)),
                Arrays.asList(new TreeNode<Integer>(-10), new TreeNode<Integer>(9), new TreeNode<Integer>(20), null, null, new TreeNode<Integer>(15), new TreeNode<Integer>(7))
        );
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("******"+i+"*******");
            BinaryTree<Integer> t = new BinaryTree<Integer>(lists.get(i));
            BinaryTree.printTree(t.root,0);
            System.out.println(obj.maxPathSum(t.root));

        }
    }
}
