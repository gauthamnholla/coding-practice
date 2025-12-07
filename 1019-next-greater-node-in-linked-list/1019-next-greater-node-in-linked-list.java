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

    public ListNode reverse(ListNode head) {
        ListNode prev = null, next = null, curr = head;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
    
    public int[] nextLargerNodes(ListNode head) {

        ListNode curr = reverse(head);
        Stack<Integer> s = new Stack<>();
        List<Integer> arrList = new ArrayList<>();

        while (curr != null) {
            
            while (!s.isEmpty() && curr.val >= s.peek()) {
                s.pop();
            }

            arrList.add(s.isEmpty() ? 0 : s.peek());
            s.push(curr.val);
            curr = curr.next;
        }

        int[] ans = new int[arrList.size()];

        for (int i = arrList.size()-1;i >= 0;i--) {
            ans[arrList.size()-i-1] = arrList.get(i);
        }

        return ans;
    }
}