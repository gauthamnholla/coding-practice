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
    public String tree2str(TreeNode root) {
        if (root == null) return ""; // Empty tree
        
        StringBuilder result = new StringBuilder();
        preorderTraversal(root, result); // Build string using preorder
        return result.toString();
    }

    private void preorderTraversal(TreeNode node, StringBuilder result) {
        if (node == null) return;

        result.append(node.val); // Add current node value

        // If there is a left or right child, process left (even if null)
        if (node.left != null || node.right != null) {
            result.append("(");
            preorderTraversal(node.left, result);
            result.append(")");
        }

        // If right child exists, process it
        if (node.right != null) {
            result.append("(");
            preorderTraversal(node.right, result);
            result.append(")");
        }
    }
}
