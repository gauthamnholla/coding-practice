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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create a dummy node (acts as a placeholder head for result list)
        ListNode dummy = new ListNode();
        
        // Keep another pointer 'res' to always return the start of the list
        ListNode res = dummy;
        
        // Variables to store the sum and carry
        int total = 0, carry = 0;

        // Loop until both lists are fully processed AND no carry is left
        while (l1 != null || l2 != null || carry != 0) {
            // Start with the carry from previous iteration
            total = carry;

            // If l1 is not null → add its value and move to next
            if (l1 != null) {
                total += l1.val;   // add l1’s digit
                l1 = l1.next;      // move l1 forward
            }

            // If l2 is not null → add its value and move to next
            if (l2 != null) {
                total += l2.val;   // add l2’s digit
                l2 = l2.next;      // move l2 forward
            }

            // Extract digit and carry
            int num = total % 10;   // digit to store in result node
            carry = total / 10;     // carry for next iteration

            // Create a new node with this digit and attach it
            dummy.next = new ListNode(num);

            // Move dummy forward to build the next node in the chain
            dummy = dummy.next;
        }

        // Return the actual head of the result (skip dummy placeholder)
        return res.next;        
    }
}
