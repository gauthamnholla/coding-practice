class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Put preorder values into a queue to consume in order
        Deque<Integer> preorderQueue = new ArrayDeque<>();
        for (int val : preorder) preorderQueue.offer(val);

        return build(preorderQueue, inorder);
    }

    // Build tree using current preorder queue and inorder array
    private TreeNode build(Deque<Integer> preorder, int[] inorder) {
        if (inorder.length > 0) {
            // The next preorder value is the root for this subtree
            int rootVal = preorder.poll();
            int idx = indexOf(inorder, rootVal);

            TreeNode root = new TreeNode(inorder[idx]); // create root node

            // Recursively build left and right subtrees using slices of inorder
            root.left = build(preorder, Arrays.copyOfRange(inorder, 0, idx));
            root.right = build(preorder, Arrays.copyOfRange(inorder, idx + 1, inorder.length));

            return root;
        }
        return null; // empty subtree
    }

    // Find index of value in array (linear search)
    private int indexOf(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1; // should not happen for valid input
    }
}
