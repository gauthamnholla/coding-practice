public class Codec {

    // Encodes a tree to a single string using BFS
    public String serialize(TreeNode root) {
        if (root == null) return "#";

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("# ");
                continue;
            }
            sb.append(node.val).append(" ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString().trim();
    }

    // Decodes your encoded data to tree
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("#")) return null;

        String[] arr = data.split("\\s+");  // safe split
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < arr.length) {
            TreeNode node = queue.poll();

            // Left child
            if (!arr[index].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(arr[index]));
                node.left = left;
                queue.offer(left);
            }
            index++;

            // Right child
            if (index < arr.length && !arr[index].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(arr[index]));
                node.right = right;
                queue.offer(right);
            }
            index++;
        }
        return root;
    }
}
