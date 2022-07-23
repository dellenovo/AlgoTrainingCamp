package w1.assignment._641_design_circular_deque;

import java.util.List;

public class MyCircularDeque {
    List<Integer> list = null;
    int capacity = 0, size = 0;
    ListNode head = new ListNode(-1), tail = new ListNode(-1);

    public MyCircularDeque(int k) {
        capacity = k;
        head.next = tail;
        tail.pre = head;
    }

    public boolean insertFront(int value) {
        if(size + 1 > capacity) return false;
        ListNode node = new ListNode(value);
        ListNode next = head.next;

        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;

        size ++;
        return true;
    }

    public boolean insertLast(int value) {
        if(size + 1 > capacity) return false;

        ListNode node = new ListNode(value);
        ListNode last = tail.pre;
        last.next = node;
        node.pre = last;
        node.next = tail;
        tail.pre = node;

        size ++;
        return true;
    }

    public boolean deleteFront() {
        if (size <= 0) return false;

        ListNode next = head.next.next;
        head.next = next;
        next.pre = head;

        size --;
        return true;
    }

    public boolean deleteLast() {
        if (size <= 0) return false;

        ListNode pre = tail.pre.pre;
        pre.next = tail;
        tail.pre = pre;

        size --;
        return true;
    }

    public int getFront() {
        if (size > 0) {
            return head.next.val;
        }

        return -1;
    }

    public int getRear() {
        if (size > 0) {
            return tail.pre.val;
        }

        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
class ListNode {
    public int val;
    public ListNode next;
    public ListNode pre;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}