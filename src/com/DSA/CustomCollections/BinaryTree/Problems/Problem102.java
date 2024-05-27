package com.DSA.CustomCollections.BinaryTree.Problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        while (!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> current = new ArrayList<>(level);
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
