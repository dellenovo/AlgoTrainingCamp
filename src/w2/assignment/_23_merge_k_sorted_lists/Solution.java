package w2.assignment._23_merge_k_sorted_lists;

import domain.ListNode;

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int len = lists.length;

        return mergeKLists(lists, 0, len);
    }

    ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start + 1 == end) return lists[start];

        if (start >= end) return null;

        int mid = start + (end - start)/2;

        ListNode a = mergeKLists(lists, start, mid);
        ListNode b = mergeKLists(lists, mid, end);

        ListNode protect = new ListNode();
        ListNode cur = protect;

        while (a != null && b != null) {
            if (a.val.compareTo(b.val) <= 0) {
                cur.next = a;
                cur = cur.next;
                a = a.next;
            } else {
                cur.next = b;
                cur = cur.next;
                b = b.next;
            }
        }

        if ( b != null) {
            cur.next = b;
        }

        if ( a != null) {
            cur.next = a;
        }

        return protect.next;
    }
}
