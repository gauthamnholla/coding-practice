/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Min-heap based on node value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Push the head of each non-empty list
        for (ListNode head : lists) {
            if (head != null) pq.add(head);
        }

        // Dummy node to build result
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // Process heap
        while (!pq.isEmpty()) {
            ListNode node = pq.poll(); // Get smallest
            curr.next = node;
            curr = curr.next;

            if (node.next != null) pq.add(node.next); // Push next node of same list
        }

        return dummy.next;
    }
}