

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 1) Interleave copied nodes after originals.
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }

        // 2) Assign random pointers for the copied nodes.
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            copy.random = (cur.random == null) ? null : cur.random.next;
            cur = copy.next; // move to the next original node
        }

        // 3) Separate the two lists: restore original and extract copy.
        cur = head;
        Node pseudoHead = new Node(0);
        Node copyTail = pseudoHead;

        while (cur != null) {
            Node copy = cur.next;
            Node nextOriginal = copy.next;

            // append copy to copied list
            copyTail.next = copy;
            copyTail = copy;

            // restore original's next
            cur.next = nextOriginal;

            cur = nextOriginal;
        }

        // terminate copied list
        copyTail.next = null;
        return pseudoHead.next;
    }
}
