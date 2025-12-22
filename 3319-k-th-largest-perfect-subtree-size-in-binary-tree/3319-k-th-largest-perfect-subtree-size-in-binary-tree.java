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
import java.util.*;

class Solution {
    List<Integer> ans = new ArrayList<>();

    // Method to recursively check for perfect subtree and calculate its size
    public Pair<Boolean, Integer> makeTree(TreeNode root) {
        if (root == null) {
            return new Pair<>(true, 0);
        }

        Pair<Boolean, Integer> l = makeTree(root.left);
        Pair<Boolean, Integer> r = makeTree(root.right);

        if (l.getKey() && r.getKey() && l.getValue().equals(r.getValue())) {
            int s = l.getValue() + r.getValue() + 1;
            ans.add(s);
            return new Pair<>(true, s);
        }

        return new Pair<>(false, 0);
    }

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        ans.clear();
        makeTree(root);

        Collections.sort(ans, Collections.reverseOrder());

        if (ans.size() >= k) {
            return ans.get(k - 1);
        }

        return -1;
    }

}