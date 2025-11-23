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
    ArrayList<String> list=new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        helper(root,"");
        return list;
    }
    public void helper(TreeNode node,String curPath){
        if(node==null)return ;
        curPath+=node.val;
        if(node.left==null&&node.right==null){
            list.add(curPath);
            return;
        }
        curPath+="->";
        helper(node.left,curPath);
        helper(node.right,curPath);
    }
}