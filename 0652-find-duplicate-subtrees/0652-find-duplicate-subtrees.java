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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> arr = new ArrayList<>();

        getSubTreesString(root, map, arr);

        return arr;
    }

    private String getSubTreesString( TreeNode root, Map<String, Integer> map, List<TreeNode> arr ) {
        if( root == null ) return null;
        
        String left = getSubTreesString( root.left, map, arr );
        String right = getSubTreesString( root.right, map, arr );

        String subTree = root.val + "," + left +"," + right;

        int count = map.getOrDefault(subTree, 0);
        if( count == 1 ) {
            arr.add(root);
        }

        map.put( subTree, count + 1 );

        return subTree;
    }

}