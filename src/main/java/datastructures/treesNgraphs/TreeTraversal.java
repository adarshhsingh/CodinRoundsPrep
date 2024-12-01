package datastructures.treesNgraphs;

public class TreeTraversal {

    // PRE-ORDER
    static void printPreorder(TreeNode temp) {
        if (temp == null)
            return;
        System.out.print(temp.val + " ");
        printPreorder(temp.left);
        printPreorder(temp.right);
    }

    // POST-ORDER
    static void printPostorder(TreeNode temp) {
        if (temp == null)
            return;
        printPostorder(temp.left);
        printPostorder(temp.right);
        System.out.print(temp.val + " ");
    }


    // IN _ ORDER
    static void printInorder(TreeNode temp) {
        if (temp == null)
            return;
        printInorder(temp.left);
        System.out.print(temp.val + " ");
        printInorder(temp.right);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, null, null);
        root.left = new TreeNode(2, null, null);
        root.right = new TreeNode(3, null, null);
        root.left.left = new TreeNode(4, null, null);
        root.left.right = new TreeNode(5, null, null);

        System.out.printf("\nPreorder Traversal of Binary Tree : \n \t");
        printPreorder(root);
        System.out.printf("\nInorder Traversal of Binary Tree : \n \t");
        printInorder(root);
        System.out.printf("\nPostorder Traversal of Binary Tree : \n \t");
        printPostorder(root);
    }
}
