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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode previousNode = dummyNode;
        while (previousNode.next != null) {
            if (previousNode.next.val != val) {
                previousNode = previousNode.next;
            } else {
                previousNode.next = previousNode.next.next;
            }
        }
        return dummyNode.next;
    }
}