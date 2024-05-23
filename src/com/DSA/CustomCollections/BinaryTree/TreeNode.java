package com.DSA.CustomCollections.BinaryTree;

import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}

//normal BFS(Breadth-First-Search)
class Problem102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        //add root node in the queue
        queue.offer(root);
        while(!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> current= new ArrayList<>(level);
            //add all the nodes of current level in the list
            for (int i = 0; i < level; i++) {
                TreeNode currentNode = queue.poll();
                current.add(currentNode.val);
                //add current node's children for next level
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(current);
        }
        return result;
    }
}

//return tree in zigzag level order
class Problem103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;

        while (!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> current = new ArrayList<>(level);
            for (int i = 0; i < level; i++) {
                if (!reverse) {
                    TreeNode currentNode = queue.removeFirst();
                    current.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.addLast(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.addLast(currentNode.right);
                    }
                } else {
                    TreeNode currentNode = queue.removeLast();
                    current.add(currentNode.val);
                    if (currentNode.right != null) {
                        queue.addFirst(currentNode.right);
                    }
                    if (currentNode.left != null) {
                        queue.addFirst(currentNode.left);
                    }
                }
            }
            reverse = !reverse;
            result.add(current);
        }
        return result;
    }
}

// return average value of each level of given tree
class Problem637 {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int level = queue.size();
            double add = 0;
            for (int i = 0; i < level; i++) {
                TreeNode currentNode = queue.poll();
                add += currentNode.val;
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(add/level);
        }
        return result;
    }
}

//connect nodes with its next right node
class Problem116 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node leftMost = root;
        while (leftMost.left != null) {
            Node current = leftMost;
            while (current != null){
                current.left.next = current.right;
                if (current.next != null) {
                    current.right.next = current.next.left;
                }
                current = current.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}

//return right side view of given perfect binary tree
class Problem199 {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                TreeNode currentNode = queue.poll();
                if(i == level - 1) {
                    result.add(currentNode.val);
                }
                if(currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }
}

//check if x and y are cousins or not
class Problem993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            int level = queue.size();
            boolean xFound = false;
            boolean yFound = false;

            for (int i = 0; i < level; i++) {
                TreeNode current = queue.poll();
                if (current.val == x) {
                    xFound = true;
                }
                if (current.val == y) {
                    yFound = true;
                }
                //check if x and y are siblings
                if (current.left != null && current.right != null) {
                    if ((current.left.val == x && current.right.val == y) || (current.left.val == y && current.right.val == x)) {
                        return false;
                    }
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            if (xFound && yFound) {
                return true;
            }
            if (xFound || yFound) {
                return false;
            }
        }
        return false;
    }
}

class Problem101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }
    boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}