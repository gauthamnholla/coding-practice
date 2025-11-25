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
    List<TreeNode> sortedArr = new ArrayList<>();
    public void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        sortedArr.add(root);
        inorder(root.right);
    }
    public TreeNode sortedArrToBST(int lo,int hi){
        if(lo>hi) return null;
        int mid = (lo+hi)/2;
        TreeNode root = sortedArr.get(mid);
        root.left = sortedArrToBST(lo,mid-1);
        root.right = sortedArrToBST(mid+1,hi);
        return root;
    } 
    public TreeNode balanceBST(TreeNode root) {
        inorder(root);
        int n = sortedArr.size();
        return sortedArrToBST(0,n-1);
    }
}