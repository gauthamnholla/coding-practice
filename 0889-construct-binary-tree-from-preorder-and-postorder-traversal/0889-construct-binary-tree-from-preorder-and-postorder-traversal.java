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
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return helper(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int[] postorder,
                            int preStart, int preEnd,
                            int postStart, int postEnd) {
        if (preStart > preEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        if (preStart == preEnd) return root; // Leaf node

        // Find index of left child in postorder
        int leftChildInPostorder = findIndex(postorder, preorder[preStart + 1], postStart);
        int leftSubtreeSize = leftChildInPostorder - postStart + 1;

        root.left = helper(preorder, postorder,
                            preStart + 1,
                            preStart + leftSubtreeSize,
                            postStart,
                            leftChildInPostorder);

        root.right = helper(preorder, postorder,
                             preStart + leftSubtreeSize + 1,
                             preEnd,
                             leftChildInPostorder + 1,
                             postEnd - 1);

        return root;
    }

    private int findIndex(int[] arr, int value, int start) {
        for (int i = start; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1; // Not found
    }
}