/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private int ans = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);   // pretend start left
        dfs(root, false, 0);  // pretend start right
        return ans;
    }

    private void dfs(TreeNode node, boolean goLeft, int length) {
        if (node == null) return;

        // update max
        ans = Math.max(ans, length);

        if (goLeft) {
            // continue zigzag by going left (next turn will be right)
            dfs(node.left, false, length + 1);
            // restart fresh from right
            dfs(node.right, true, 1);
        } else {
            dfs(node.right, true, length + 1);
            dfs(node.left, false, 1);
        }
    }
}