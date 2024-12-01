package leetcode.treesandgraphs;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
public class CloneGraph {
    static HashMap<Node, Node> visited = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if(node == null) return node;

        if(visited.containsKey(node)) {
            System.out.println("already visit "+node.val);
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node,cloneNode);
        System.out.println("first time visit "+node.val);

        for (Node neighbour : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbour));
        }

        return cloneNode;
    }


    public static Node cloneGraphAllNode(Node node) {
        HashMap<Node, Node> visited = new HashMap<>();
        System.out.println("cloneGraphAllNode started");
        Node root = null;
        if(node != null) {
            //System.out.println("node is not null ");
            Queue<Node> queueMain = new LinkedList<>();
            queueMain.add(node);
            visited.put(node, new Node(node.val));
            while (!queueMain.isEmpty()) {
                //System.out.println("Queue is not null ");
                Node currentMain = queueMain.poll();
                Node currentCopy = visited.get(currentMain);
                for(Node i: currentMain.neighbors) {
                    if(visited.get(i) == null) {
                        System.out.println("first time visit "+i.val);
                        visited.put(i, new Node(i.val));
                        currentCopy.neighbors.add(visited.get(i));
                        queueMain.add(i);
                    } else {
                        System.out.println("already visit "+i.val);
                        currentCopy.neighbors.add(visited.get(i));
                    }
                }

            }
        }
        return visited.get(node);
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        one.neighbors.add(two);
        one.neighbors.add(four);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(two);
        three.neighbors.add(four);
        four.neighbors.add(one);
        four.neighbors.add(three);
        visited = new HashMap<>();
        printAdjacent(one);
        printAdjacent(cloneGraphAllNode(one));

        /*visited = new HashMap<>();
        Node one1 = new Node(1);
        printAdjacent(one1);
        printAdjacent(cloneGraph(one1));

        visited = new HashMap<>();
        Node one2 = null;
        printAdjacent(one2);
        printAdjacent(cloneGraph(one2));*/
    }

    public static void printAdjacent(Node node){
        int passedNode[]=new int[100];
        Node root = node;
        System.out.print('[');
        if(node != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            passedNode[root.val] = 1;
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                List<Node> adjacents = current.neighbors;
                System.out.print('[');
                boolean addOnce = false;
                for (int i = 0; i < adjacents.size(); i++) {
                    Node adj = adjacents.get(i);
                    System.out.print(adj.val);
                    if (!addOnce && passedNode[adj.val] != 1) {
                        queue.add(adj);
                        passedNode[adj.val] = 1;
                        addOnce = true;
                    }
                    if (i != adjacents.size() - 1) {
                        System.out.print(',');
                    }
                }
                System.out.print(']');
            }
        }
        System.out.println(']');
    }
}
