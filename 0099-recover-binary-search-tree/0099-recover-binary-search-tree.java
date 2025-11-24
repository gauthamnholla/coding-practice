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
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null; // previously visited node in inorder

    public void recoverTree(TreeNode root) {
        // In-order traversal to find the two nodes
        inorder(root);

        // swap values of the two nodes to recover the BST
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        // visit current node: check for violation with prev
        if (prev != null && prev.val > node.val) {
            // found a violation
            if (first == null) {
                // first violation: prev is the larger one
                first = prev;
            }
            // second might be this node (for adjacent swap) or later (for non-adjacent)
            second = node;
        }
        prev = node; // update prev

        inorder(node.right);
    }
}
