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

class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeftPath(root); // push all left children
    }

    // Return next smallest number
    public int next() {
        TreeNode node = stack.pop();  // smallest unvisited
        if (node.right != null) {
            pushLeftPath(node.right);  // push left path from right child
        }
        return node.val;
    }

    // Check if next smallest exists
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // Helper: push left children until null
    private void pushLeftPath(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
