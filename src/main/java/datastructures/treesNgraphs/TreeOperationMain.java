package datastructures.treesNgraphs;

import java.util.*;

public class TreeOperationMain {

    public static void main(String[] args) {

        TreeNode root        = new TreeNode(50, null, null);
        root.left        = new TreeNode(50, null, null);
        root.right       = new TreeNode(50, null, null);
        root.left.left   = new TreeNode(50, null, null);
        root.left.right  = new TreeNode(50, null, null);
        root.right.left   = new TreeNode(50, null, null);

        Map<TreeNode, Boolean> visted = new HashMap<>();
        visted.put(root,true);
        Queue<TreeNode> queue = new LinkedList<>();
        TreeSet<TreeNode> treeSet = new TreeSet<>();

        System.out.println(visted.containsKey(root.left.right));
        System.out.println(visted.containsKey(root.right.left));
        System.out.println(visted.containsKey(root.left.left));
        System.out.println(visted.containsKey(root.left));
        System.out.println(visted.containsKey(root.right));
        System.out.println(visted.containsKey(root));





        //root.right.right  = new node(1);


        TreeNode root2        = new TreeNode(10, null, null);
        root2.left        = new TreeNode(6, null, null);
        root2.right       = new TreeNode(4, null, null);
        root2.left.left   = new TreeNode(3, null, null);
        root2.left.left.left   = new TreeNode(3, null, null);
        root2.left.left.left.left   = new TreeNode(3, null, null);
        root2.left.right  = new TreeNode(3, null, null);
        root2.left.right.right  = new TreeNode(3, null, null);
        root2.left.right.right.left  = new TreeNode(3, null, null);
        root2.left.right.right.left.right  = new TreeNode(3, null, null);


        TreeNode root3 = new TreeNode(26, null, null);
        root3.left = new TreeNode(10, null, null);
        root3.right = new TreeNode(3, null, null);
        root3.left.left = new TreeNode(4, null, null);
        root3.left.right = new TreeNode(6, null, null);
        root3.right.right = new TreeNode(3, null, null);

        System.out.println("----- INPUT 1-----");
        printTree(root,0);
        System.out.println("------ INPUT 2 -----");
        printTree(root3,0);

        System.out.println("------ SUM TREEE 2 -----");
        System.out.println(TreeProperty.isSumTree(root));
        System.out.println(TreeProperty.isSumTree(root3));

        System.out.println(TreeProperty.getSize(root));

    }

    public static void printTree(TreeNode head, int level)
    {
        if(head==null)return;
        printTree(head.right, level+1);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.print(head.val);
        System.out.println();
        printTree(head.left, level+1);
    }

    public static void setupTree(TreeNode ROOT, Integer a[], int start) {
        if (start >= a.length) return;
        if(a[start]!=null) ROOT.left = new TreeNode(a[start],null,null);
        if(start+1 < a.length && a[start+1]!=null) ROOT.right = new TreeNode(a[start+1], null,null);
        if(ROOT.left != null) setupTree(ROOT.left,a,(start*2)+1);
        if(ROOT.right != null) setupTree(ROOT.right,a,((start+1)*2)+1);
    }
}