// Java implementation
class Solution {
    int totalSum = 0;
    long res = 0;

    public int maxProduct(TreeNode root) {
        // First traversal to get total sum of the tree
        getTotalSum(root);

        // Second traversal to compute max product of split
        getSubtreeSum(root);

        return (int)(res % (int)(1e9 + 7));
    }

    // Helper method to get total sum of tree nodes
    void getTotalSum(TreeNode node){
        if(node == null) return;
        getTotalSum(node.left);
        totalSum += node.val;
        getTotalSum(node.right);
    }

    // Helper method to compute subtree sum and update max product
    long getSubtreeSum(TreeNode node){
        if(node == null) return 0;

        long left = getSubtreeSum(node.left);
        long right = getSubtreeSum(node.right);

        long subtreeSum = node.val + left + right;
        long complementSum = totalSum - subtreeSum;

        // Calculate product of this split and update max
        res = Math.max(res, subtreeSum * complementSum);

        return subtreeSum;
    }
}