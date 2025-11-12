public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode lista = headA;
        ListNode listb = headB;

        // Traverse both lists; when one reaches the end, switch to the other list's head
        // This ensures both pointers travel the same total distance
        while (lista != listb) {
            lista = (lista != null) ? lista.next : headB;
            listb = (listb != null) ? listb.next : headA;
        }

        // Either intersection node or null (if no intersection)
        return lista;
    }
}
