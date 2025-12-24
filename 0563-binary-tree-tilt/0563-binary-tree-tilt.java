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
    int sum=0;
    private int traverse(TreeNode root){
        if(root==null) return 0;
        int left=0;
        left=traverse(root.left);
        int right=0;
        right=traverse(root.right);
        sum+=Math.abs(left-right);
        return left+right+root.val;
    }
    public int findTilt(TreeNode root) {
        traverse(root);
        return sum;
    }
}