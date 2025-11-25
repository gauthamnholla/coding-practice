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
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        // Step 1: Generate sorted list of values using Morris inorder
        List<Integer> inorder = new ArrayList<>();
        morrisInorder(root, inorder);

        // Step 2: Answer each query using binary search
        List<List<Integer>> ans = new ArrayList<>();
        for (int q : queries) {
            int floor = findFloor(inorder, q);
            int ceil = findCeil(inorder, q);
            ans.add(List.of(floor, ceil));
        }
        return ans;
    }

    // Morris Inorder Traversal (O(1) extra space)
    private void morrisInorder(TreeNode root, List<Integer> inorder) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                inorder.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }
    }

    // Binary search: floor -> largest value <= target
    private int findFloor(List<Integer> arr, int target) {
        int l = 0, r = arr.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) <= target) {
                ans = arr.get(mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // Binary search: ceil -> smallest value >= target
    private int findCeil(List<Integer> arr, int target) {
        int l = 0, r = arr.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) >= target) {
                ans = arr.get(mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}