/**
 * Definition for a binary tree node (LeetCode style).
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val; this.left = left; this.right = right;
 *     }
 * }
 */

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        // push left path
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        // pop k-1 nodes and move to their right subtree
        for (int i = 0; i < k - 1; i++) {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                TreeNode p = cur.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }

        // top of stack is the k-th smallest
        return stack.pop().val;
    }
}
