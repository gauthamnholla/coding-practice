public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head; // moves 2 steps
        ListNode slow = head; // moves 1 step

        // Move until fast reaches end or both meet
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // If they meet → cycle exists
            if (fast == slow) {
                return true;
            }
        }

        // If loop ends, no cycle found
        return false;
    }
}
