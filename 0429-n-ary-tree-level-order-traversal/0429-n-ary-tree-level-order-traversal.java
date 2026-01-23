/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int queueLength = queue.size();
            List<Integer> level = new ArrayList<Integer>();

            for(int i = 0; i < queueLength; i++) {
                Node node = queue.poll();
                if(!node.children.isEmpty()) {
                    int lengthChildren = node.children.size();
                    for(int j = 0; j < lengthChildren; j++) {
                        queue.offer(node.children.get(j));
                    }
                }
                level.add(node.val);
            }
            result.add(level);
        }
        return result;
    }
}