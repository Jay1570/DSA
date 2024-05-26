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

//check if left and right side of tree is mirrored or not
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

//return longest possible path from tree. it may or may not pass through root node
class Problem543 {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter - 1;
    }
    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);
        diameter = Math.max(diameter, left + right + 1);
        return (Math.max(left, right) + 1);
    }
}

//invert given tree.for example swap all the left side of nodes with right side of nodes
class Problem226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}

//insert a sorted array in binary tree in a way that tree is balanced
class Problem108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return insert (nums, 0, nums.length - 1);
    }
    private TreeNode insert (int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = insert(nums, start, mid - 1);
        root.right = insert(nums, mid + 1, end);
        return root;
    }
}

//convert given tree into LinkedList in preOrder form in a way that all the left nodes of the tree will be null
class Problem114 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode current = root;
        while (current != null) {
            if(current.left != null) {
                TreeNode temp = current.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
}

//validate binary search tree
class Problem98 {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    private boolean validate(TreeNode node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) {
            return false;
        }
        return (validate(node.left, low, node.val) && validate(node.right, node.val, high));
    }
}

//lowest common ancestor in binary tree
class Problem236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}

//find kth smallest element in BST.(1-indexed)
class Problem230 {
    int n = 0;
    public int kthSmallest(TreeNode root, int k) {
        return smallest(root, k).val;
    }
    private TreeNode smallest(TreeNode node, int k) {
        if (node == null) {
            return null;
        }
        TreeNode left = smallest(node.left, k);
        if (left != null) {
            return left;
        }
        n++;
        if (n == k) {
            return node;
        }
        return smallest(node.right, k);
    }
}

//create a binary tree based on given preorder and inorder traversal
class Problem105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        int r = preorder[0];
        int index = 0;

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == r) {
                index = i;
            }
        }

        TreeNode node = new TreeNode(r);
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
        node.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, preorder.length));

        return node;
    }
}

//problem 297. serialize and deserialize binary tree
class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        helper(root, list);
        return String.join(",", list);
    }

    private void helper(TreeNode node, List<String> list) {
        if (node == null) {
            list.add("null");
            return;
        }
        list.add(String.valueOf(node.val));
        helper(node.left, list);
        helper(node.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
        return helper2(list);
    }

    private TreeNode helper2(List<String> list) {
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.remove(0)));
        node.left = helper2(list);
        node.right = helper2(list);
        return node;
    }
}

//path sum
class Problem112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if(root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}

//sum root to leaf
class Problem129 {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }
    private int helper(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }

        sum = sum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return sum;
        }

        return helper(node.left, sum) + helper(node.right, sum);
    }
}

//maximum path sum
class Solution {
    int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return sum;
    }
    int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = maxPathSum(node.left);
        int right = maxPathSum(node.right);

        left = Math.max(0,left);
        right = Math.max(0, right);

        int addition = left + right + node.val;
        sum = Math.max(sum, addition);
        return Math.max(left, right) + node.val;
    }
}