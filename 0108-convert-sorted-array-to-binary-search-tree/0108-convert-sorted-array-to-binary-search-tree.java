class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // Convert sorted array to height-balanced BST
        return convert(nums, 0, nums.length - 1);
    }

    private TreeNode convert(int[] nums, int left, int right) {
        // Base case: no elements left to process
        if (left > right) return null;

        // Middle element becomes root
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        // Recursively build left and right subtrees
        node.left = convert(nums, left, mid - 1);
        node.right = convert(nums, mid + 1, right);

        return node; // Return root of this subtree
    }
}
