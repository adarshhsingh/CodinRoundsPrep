package leetcode.treesandgraphs;

import java.util.Arrays;
import java.util.List;

public class ValidBinaryTree {
    Integer previous = null;
    public boolean isValidBST(TreeNode<Integer> root) {
        return isValidBSTHelper(root);
    }

    private boolean isValidBSTHelper(TreeNode<Integer> root) {
        if(root == null) return true;
        boolean isLeftValidBST = isValidBSTHelper(root.left);
        boolean isCurrentNodeValidBST =  true;
        if(previous!=null && root.val <= previous) {
            isCurrentNodeValidBST = false;
        }
        previous = root.val;
        boolean isRightValidBST = isValidBSTHelper(root.right);
        return isRightValidBST && isLeftValidBST && isCurrentNodeValidBST;
    }

    // Driver code
    public static void main(String args[]) {
        ValidBinaryTree obj = new ValidBinaryTree();
        List<List<TreeNode<Integer>>> lists = Arrays.asList(
                Arrays.asList(new TreeNode<Integer>(2), new TreeNode<Integer>(1), new TreeNode<Integer>(3))
       );

        for (int i = 0; i < lists.size(); i++) {
            BinaryTree<Integer> t = new BinaryTree<Integer>(lists.get(i));
            BinaryTree.printTree(t.root,0);
            System.out.println((i+1)+ "\t isValidBST = "+ obj.isValidBST(t.root));

        }
    }
}
