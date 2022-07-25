package w2.leetcode._146_LRU_cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity;
    int size = 0;
    Entry head = new Entry(-1, -1);
    Entry tail = new Entry(-1, -1);
    Map<Integer, Entry> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Entry e = map.get(key);
        if (e == null) return -1;

        // e还不在头部
        if(e != head.next) {
            // 从原位置删除e
            e.pre.next = e.next;
            e.next.pre = e.pre;

            // 把e挪到头部
            Entry originalFirstEle = head.next;
            head.next = e;
            e.pre = head;
            e.next = originalFirstEle;
            originalFirstEle.pre = e;
        }

        printEntryList(head, "after get");
        return e.val;
    }

    public void put(int key, int value) {
        // System.out.println("size before put "+ key + " :" + size);

        while (size >= capacity && !map.containsKey(key)) {
            // System.out.println("size:" + size);
            // System.out.println("map:" + map);
            // delete from map
            map.remove(tail.pre.key);
            // System.out.println("after deletion map:" + map);

            //尾部出列
            Entry last2Ele = tail.pre.pre;
            last2Ele.next = tail;
            tail.pre = last2Ele;

            printEntryList(head, "After tail dequeue:");

            size --;
        }

        if (map.containsKey(key)) {
            Entry e = map.get(key);
            e.val = value;

            // e还不在头部
            if(e != head.next) {
                // 从原位置删除e
                e.pre.next = e.next;
                e.next.pre = e.pre;

                // 把e挪到头部
                Entry originalFirstEle = head.next;
                head.next = e;
                e.pre = head;
                e.next = originalFirstEle;
                originalFirstEle.pre = e;
            }
        } else {
            Entry newEntry = new Entry(key, value);
            //加入map
            map.put(key, newEntry);

            //头部入列
            Entry originalFirstEle = head.next;
            head.next = newEntry;
            newEntry.pre = head;
            newEntry.next = originalFirstEle;
            originalFirstEle.pre = newEntry;

            size ++;
        }

        printEntryList(head, "Finally:");
    }

    void printEntryList(Entry cur, String msg) {
        // System.out.println(msg);
        // int limit = 6;
        // while (cur != null && limit > 0) {
        //     System.out.print(String.format("(%d,%d) -> ", cur.key, cur.val));
        //     cur = cur.next;
        //     limit--;
        // }
        // System.out.print("\n");
    }
}
class Entry {
    Integer key;
    Integer val;
    Entry next;
    Entry pre;

    Entry(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }

    public String toString() {
        if(next == null) return String.format("%d:%d", key, val);

        return String.format("%d:%d,%s", key, val, next);
    }
}
