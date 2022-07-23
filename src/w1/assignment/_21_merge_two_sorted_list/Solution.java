package w1.assignment._21_merge_two_sorted_list;

import domain.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode protect = new ListNode();
        ListNode current = protect, up = list1, down = list2;

        while (up != null && down != null) {

            if (up.val <= down.val) {
                current.next = up;
                up = up.next;
            } else {
                current.next = down;
                down = down.next;
            }

            current = current.next;
        }

        if (up != null) current.next = up;
        if (down != null) current.next = down;

        return protect.next;
    }
}
