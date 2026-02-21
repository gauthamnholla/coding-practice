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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return null;

        Deque<TreeNode> mdStack = new ArrayDeque<>();
        for (int num: nums) {
            TreeNode node = new TreeNode(num); // initialize Tree(e2)

            // while e1 < e2: polling e1
            // Set e2.left = LAST e1 < e2 polled.
            while (
                !mdStack.isEmpty() && mdStack.peekLast().val < num
            ) {
                node.left = mdStack.pollLast();
            }

            // Meet first e1 > e2. Set e1.right = Tree of e2
            if (!mdStack.isEmpty()) {
                mdStack.peekLast().right = node;
            }

            // offer Tree of e2 to mdStack
            mdStack.offerLast(node);
        }

        // return the first TreeNode in mdStack as result
        return mdStack.pollFirst();
    }
}