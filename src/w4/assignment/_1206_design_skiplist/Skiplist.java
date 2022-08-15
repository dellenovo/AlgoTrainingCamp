package w4.assignment._1206_design_skiplist;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Skiplist {
    // 1206. Design Skiplist
    int LEVEL_COUNT = 16;
    Node upHead = new Node(-1);
    Node upTail = new Node(20001);
    Random r = new Random();

    public Skiplist() {
        upHead.next = upTail;
        upTail.prev = upHead;
        Node cur = upHead;
        for(int i = 1; i < LEVEL_COUNT; i++) {
            cur.down = new Node(-1);
            cur = cur.down;
            cur.next = new Node(20001);
            cur.next.prev = cur;
        }
    }

    public boolean search(int target) {
        return searchNode(target) != null;
    }

    private Node searchNode(int target) {
        Node cur = upHead;

        while (cur != null && cur.val < upTail.val) {
            if (cur.next.val < upTail.val) {
                if (cur.next.val == target) return cur.next;
                if (cur.next.val < target) cur = cur.next;
                else cur = cur.down;
            } else {
                cur = cur.down;
            }
        }

        return null;
    }

    public void add(int num) {
        Node cur = upHead;
        Deque<Node> turnNodes = new ArrayDeque<>();

        while (cur != null && cur.val < upTail.val) {
            if (cur.next.val >= upTail.val || cur.next.val >= num) {
                turnNodes.push(cur);
                cur = cur.down;
            } else {
                cur = cur.next;
            }

            // if (num == 9943)
            // System.out.println(String.format("num: %d, cur: %s, cur.next: %s", num, cur == null ? null : cur.val, cur == null || cur.next == null ? null : cur.next.val));
        }

        // if (num == 9943)
        // System.out.println("stack is empty? " + turnNodes.isEmpty());

        Node pre = null;
        while (!turnNodes.isEmpty()) {
            cur = turnNodes.pop();
            Node temp = new Node(num);
            temp.next = cur.next;
            cur.next = temp;
            temp.next.prev = temp;
            temp.prev = cur;

            temp.down = pre;
            pre = temp;

            if (r.nextBoolean()) break;
        }

        // if (num == 9943)
        //     System.out.println("Does 9943 still exist? " + search(num));

        return;
    }

    public boolean erase(int num) {
        Node t = searchNode(num);
        if (t == null) return false;

        while (t != null) {
            t.prev.next = t.next;
            t.next.prev = t.prev;
            t = t.down;
        }

        // if (num == 14) {
        //     System.out.println("Does 14 still exist? " + search(num));
        // }

        return true;
    }
}

class Node {
    int val;
    Node prev;
    Node next;
    Node down;
    public Node(int val) {
        this.val = val;
    }
}