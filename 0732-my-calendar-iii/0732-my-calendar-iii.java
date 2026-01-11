class MyCalendarThree {
    class Node {
        int max, lazy;
        Node left, right;
        Node() {
            max = 0;
            lazy = 0;
        }
    }
    private Node root;
    public MyCalendarThree() {
        root = new Node();
    }
    
    public int book(int startTime, int endTime) {
        update(root, 0, 1_000_000_000, startTime, endTime - 1);
        return root.max;
    }
    
    private void update(Node node, int min, int max, int start, int end) {
        if(max < start || min > end) return;
        if(start <= min && max <= end) {
            ++node.max;
            ++node.lazy;
            return;
        }

        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();

        int m = min + (max - min) / 2;
        update(node.left, min, m, start, end);
        update(node.right, m + 1, max, start, end);

        node.max = node.lazy + Math.max(node.left.max, node.right.max);
    }
}