/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
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
    private ListNode headPtr; // pointer to current list node while building tree

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        // compute length
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }

        headPtr = head;
        return buildTree(0, n - 1);
    }

    // build BST for nodes in index range [l, r] (inclusive) using in-order consumption of list
    private TreeNode buildTree(int l, int r) {
        if (l > r) return null;

        int mid = l + (r - l) / 2;

        // build left subtree from [l, mid-1]
        TreeNode left = buildTree(l, mid - 1);

        // headPtr now points to the list node corresponding to mid
        TreeNode root = new TreeNode(headPtr.val);
        root.left = left;

        // move the list pointer forward
        headPtr = headPtr.next;

        // build right subtree from [mid+1, r]
        root.right = buildTree(mid + 1, r);

        return root;
    }
}
