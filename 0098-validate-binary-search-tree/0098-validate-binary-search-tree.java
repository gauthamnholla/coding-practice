/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public boolean isValidBST(TreeNode root) {
        // start with full long range to handle Integer limits safely
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode node, long minAllowed, long maxAllowed) {
        if (node == null) return true;
        long v = node.val;
        // current node must be strictly between minAllowed and maxAllowed
        if (v <= minAllowed || v >= maxAllowed) return false;
        // left subtree values must be < v
        if (!helper(node.left, minAllowed, v)) return false;
        // right subtree values must be > v
        return helper(node.right, v, maxAllowed);
    }
}
