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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // found the node to delete
            if (root.left == null) {
                return root.right; // includes case both null
            } else if (root.right == null) {
                return root.left;
            } else {
                // two children: replace value with inorder successor (min in right subtree)
                TreeNode succ = findMin(root.right);
                root.val = succ.val;
                // delete successor node from right subtree
                root.right = deleteNode(root.right, succ.val);
            }
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
