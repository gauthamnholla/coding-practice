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
    public int cg(TreeNode root, int maxi) {
      
        if (root == null) {
            return 0;
        }
          int c = 0;
        if (root.val >=maxi) {
            c = 1;
            maxi = Math.max(root.val, maxi);

        }
        return c + cg(root.left, maxi) +
                cg(root.right, maxi);

    }

    public int goodNodes(TreeNode root) {
        // List<Integer> l1 = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        int maxi = root.val;
        int k = cg(root, maxi);
        return k;
    }
}