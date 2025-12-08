/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> copiedMap = new HashMap<>();
        bfs(node, copiedMap);
        return copiedMap.get(node);
    }

    private void bfs(Node startNode, Map<Node, Node> copiedMap) {
        Queue<Node> queue = new ArrayDeque<>(); // include nodes that have been visited (copied) only

        // check & copy node at Generation
        copiedMap.put(startNode, new Node(startNode.val));
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // copy & link, add neighbors to Queue
            for (Node neighbor: node.neighbors) {
                // check & copy node at Generation
                if (copiedMap.containsKey(neighbor)) { // neighbor is already visited (copied), just link
                    // IMPORTANT: DON'T JUST CONTINUE!
                    copiedMap.get(node).neighbors.add(copiedMap.get(neighbor));
                } else { // copy, link, add neighbor to queue to be visited
                    copiedMap.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                    copiedMap.get(node).neighbors.add(copiedMap.get(neighbor));
                }
                
            }
        }
    }
}