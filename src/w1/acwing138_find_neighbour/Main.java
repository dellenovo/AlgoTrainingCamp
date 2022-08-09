package w1.acwing138_find_neighbour;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static Node head = new Node(-20_0000_0000, -1);
    static Node tail = new Node(20_0000_0000, -1);

    public static void main(String[] args) {
        // 1. 读取输入
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//
//        int[] a = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//        }

        int[] a = {4, 5, 6, 1, 2, 3, 7, 8, 9, 10 };

        // 2. 保存排序后的节点数组
        List<Node> nodes = IntStream.range(0, a.length).mapToObj(i -> new Node(a[i], i)).sorted(
                Comparator.comparingInt(o -> o.val)).collect(Collectors.toCollection(LinkedList::new));

        Node[] pos = new Node[a.length];

        for (Node node: nodes) {
            pos[node.idx] = node;
        }

        // 3. 构造双链表
        head.next = tail;
//        System.out.println("finish head next assignment.");
        tail.pre = head;
//        System.out.println("finish tail pre assignment.");
        printLinkedList(head);

        for (Node node: nodes) {
            printLinkedList(head);
            append(node);
        }

        // 4. 寻找合适的前驱、后继
        int[] ans = new int[a.length];

        // 这里很关键，是从后往前寻找最小值。这样可以避免判断只考虑下标在当前考虑元素之前的元素，因为在最后的元素肯定是可以考虑所有在它之前的
        // 元素的。这里告诉我们，当当前值的逻辑被限制在只在前面出现的元素时，可以试试看从后面开始倒着考虑。当然如果倒过来考虑，就要考虑如何去除
        // 已经考虑过的元素，所以这里结合链表删除节点，是非常妙的一招。
        for (int i = a.length - 1; i > 0; i--) {
            Node cur = pos[i];
            int preDiff = cur.val - cur.pre.val;
            int nextDiff = cur.next.val - cur.val;

            if (preDiff <= nextDiff) {
                ans[i] = cur.pre.idx;
            } else {
                ans[i] = cur.next.idx;
            }

            deleteNode(cur);
        }

        // 5. 输出结果
        for (int i = 1; i < a.length; i++) {
            System.out.println(String.format("%d %d", Math.abs(a[ans[i]] - a[i]), ans[i] + 1));
        }
    }

    private static void deleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private static void append(Node node) {
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
    }

    private static void printLinkedList(Node node) {
//        while (node != null) {
//            System.out.print(node.val + "->");
//            node = node.next;
//        }
//        System.out.println();
    }
}

class Node {
    int val;
    int idx;
    Node pre;
    Node next;
    public Node(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}