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
 int ans;

 public int maxSumBST(TreeNode root) {
     ans = 0;
     dfs(root);
     return ans;
 }

 // Returns: [minValue, maxValue, sumOfSubtree]
 private int[] dfs(TreeNode root) {
     if (root == null) {
         return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
     }

     int[] leftSubtree = dfs(root.left);
     int[] rightSubtree = dfs(root.right);

     // Check BST validity
     if (root.val > leftSubtree[1] && root.val < rightSubtree[0]) {
         int currSum = leftSubtree[2] + rightSubtree[2] + root.val;
         ans = Math.max(ans, currSum);

         int minValue = Math.min(root.val, leftSubtree[0]);
         int maxValue = Math.max(root.val, rightSubtree[1]);

         return new int[]{minValue, maxValue, currSum};
     }

     // Mark subtree as invalid BST
     return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
 }
}