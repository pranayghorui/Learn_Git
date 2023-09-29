package java_program;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasics {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        int idx = -1;

        public Node buildTree(int[] nodes) {
            idx++;
            Node newNode = null;
            if (idx < nodes.length) {
                if (nodes[idx] == -1) {
                    return null;
                }
                newNode = new Node(nodes[idx]);
                newNode.left = buildTree(nodes);
                newNode.right = buildTree(nodes);
            }
            return newNode;
        }
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.print(-1 + " ");
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root) {
        if (root == null) {
            // System.out.print(-1+" "); // for null leaf node
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) {
            // System.out.print(-1+" "); // for null leaf node
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node currNode = q.remove();
            if (currNode == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(currNode.data + " ");
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
    }

    public static int countOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftNodes = countOfNodes(root.left);
        int rightNodes = countOfNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    public static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftNodeSum = sumOfNodes(root.left);
        int rightNodeSum = sumOfNodes(root.right);
        return leftNodeSum + rightNodeSum + root.data;
    }

    public static int heightOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int diameter(Node root) { // O(n^2)
        if (root == null) {
            return 0;
        }
        int diam1 = diameter(root.left);
        int diam2 = diameter(root.right);
        int diam3 = heightOfTree(root.left) + heightOfTree(root.right) + 1;
        return Math.max(Math.max(diam2, diam1), diam3);
    }

    static class TreeInfo {
        int ht;
        int diam;

        TreeInfo(int ht, int diam) {
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo diameter2(Node root) { // Optimized O(n)
        if (root == null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);
        int myHeight = Math.max(left.ht, right.ht) + 1;

        int diamL = left.diam;
        int diamR = right.diam;
        int diam3 = left.ht + right.ht + 1;
        int myDiam = Math.max(Math.max(diamL, diamR), diam3);
        TreeInfo myInfo = new TreeInfo(myHeight, myDiam);
        return myInfo;
    }

    public static boolean isSubtree(Node root, Node subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.data == subRoot.data) {
            if (isIdentical(root, subRoot)) {
                return true;
            }
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static boolean isIdentical(Node root, Node subRoot) { // when root.data==subaroot.data check subtree of both
                                                                 // is same or not

        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.data == subRoot.data) {
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.println("Tree 1 root : " + root.data);

        int[] nodes2 = { 2, 4, -1, -1, 5 };
        BinaryTree tree2 = new BinaryTree();
        Node subRoot = tree2.buildTree(nodes2);
        System.out.println("Sub Tree root : " + subRoot.data);

        System.out.println("preOrder : ");
        preOrder(root);
        System.out.println();

        System.out.println("inOrder : ");
        inOrder(root);
        System.out.println();

        System.out.println("postOrder : ");
        postOrder(root);
        System.out.println();

        System.out.println("levelOrder : ");
        levelOrder(root);
        System.out.println();

        System.out.println("Count of Nodes : " + countOfNodes(root));

        System.out.println("Sum of Nodes : " + sumOfNodes(root));

        System.out.println("Height of Tree : " + heightOfTree(root));

        System.out.println("Diameter of Tree : " + diameter(root));

        System.out.println("Diameter of Tree(optimized way) : " + diameter2(root).diam);

        // check if Tree2 is subtree of Tree1
        System.out.println("check if Tree2 is subtree of Tree1 : " + isSubtree(root, subRoot));
    }
}