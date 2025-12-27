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
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return remove(root, limit, 0);
    }
    private TreeNode remove(TreeNode root, int limit, int sum){
        if(root == null){
            return null;
        }
        if(root.left == null && root.right == null){
            if(sum + root.val < limit){
                return null;
            }
            else{
                return root;
            }
        }
        root.left = remove(root.left, limit, sum + root.val);
        root.right = remove(root.right, limit, sum + root.val);
        if(root.left == root.right){
            return null;
        }
        return root;
    }
}