package domain;

/**
 * Definition for singly-linked list.
 */
public class ListNode<T extends Comparable<T>> {
    public T val;
    public ListNode next;
    public ListNode() {}
    public ListNode(T val) { this.val = val; }
    public ListNode(T val, ListNode next) { this.val = val; this.next = next; }
}
