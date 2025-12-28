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
    Map<Integer, TreeNode> map = new HashMap<>();
    Set<Integer> leaves = new HashSet<>();

    public TreeNode canMerge(List<TreeNode> trees) {
        // preparation
        for (TreeNode tree : trees) {
            map.put(tree.val, tree);
            if (tree.left != null) leaves.add(tree.left.val);
            if (tree.right != null) leaves.add(tree.right.val);
        }

        // now finding the unique root :
        TreeNode root = null;
        for (TreeNode treeNode : trees) {
            if (!leaves.contains(treeNode.val)) {
                root = treeNode;
                break;
            }
        }

        // if there is no unique node, then return 'null'
        if (root == null) return null;

        map.remove(root.val);

        if (!validateAndMergeBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) return null;
        if (!map.isEmpty()) return null;

        return root;
    }

    private boolean validateAndMergeBST(TreeNode node, long minValue, long maxValue) {
        if (node == null) return true;

        //BST Validation Condition :
        if (node.val <= minValue || node.val >= maxValue ) {
            return false;
        }

        if (node.left != null && map.containsKey(node.left.val)) {
            //attach that tree
            node.left = map.get(node.left.val);
            map.remove(node.left.val);
        }
        if (node.right != null && map.containsKey(node.right.val)) {
            //attach that tree
            node.right = map.get(node.right.val);
            map.remove(node.right.val);
        }

        return validateAndMergeBST(node.left, minValue, node.val) 
            && validateAndMergeBST(node.right, node.val, maxValue);
    }
}