package w1.assignment._21_merge_two_sorted_list;

import domain.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode protect = new ListNode();
        ListNode current = protect;

        while (list1 != null && list2 != null) {

            if (list1.val.compareTo(list2.val) <= 0) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return protect.next;
    }
}
