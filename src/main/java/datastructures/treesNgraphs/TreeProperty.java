package datastructures.treesNgraphs;

public class TreeProperty {


    public static int getSize(TreeNode root) {

        if(root==null)return 0;
        return getSize(root.left) + 1 + getSize(root.right);
    }

    public static boolean isSumTree(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (isSumTree(root.left) && isSumTree(root.right)) {
            if (root.val == (sumOfAllChilds(root.left) + sumOfAllChilds(root.right))){
                return true;
            }
        }
        return false;
    }
    public static int sumOfAllChilds (TreeNode root) {
        if (root == null ) return 0;
        return root.val + sumOfAllChilds(root.left) + sumOfAllChilds(root.right);
    }

    public static void main(String[] args) {
        // Test Case 1: Empty tree
        TreeNode root1 = null;
        System.out.println("Test Case 1: " + isSumTree(root1)); // Expected output: true

        // Test Case 2: Tree with only one node
        TreeNode root2 = new TreeNode(10, null, null);
        System.out.println("Test Case 2: " + isSumTree(root2)); // Expected output: true

        // Test Case 3: Tree that satisfies the sum tree property
        //      26
        //     /  \
        //    10   3
        //   / \    \
        //  1   2    3
        TreeNode root3 = new TreeNode(26, null, null);
        root3.left = new TreeNode(10, null, null);
        root3.right = new TreeNode(3, null, null);
        root3.left.left = new TreeNode(4, null, null);
        root3.left.right = new TreeNode(6, null, null);
        root3.right.right = new TreeNode(3, null, null);
        System.out.println("Test Case 3: " + isSumTree(root3)); // Expected output: true

        // Test Case 4: Tree that does not satisfy the sum tree property
        //      10
        //     /  \
        //    5    3
        //   / \
        //  1   2
        TreeNode root4 = new TreeNode(10, null, null);
        root4.left = new TreeNode(5, null, null);
        root4.right = new TreeNode(3, null, null);
        root4.left.left = new TreeNode(1, null, null);
        root4.left.right = new TreeNode(2, null, null);
        System.out.println("Test Case 4: " + isSumTree(root4)); // Expected output: false
    }
}
