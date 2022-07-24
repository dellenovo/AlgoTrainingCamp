package w2.assignment.mymap;

public class MyMap {
    private int capacity = 99991;
    private int size = 0;
    private Entry[] buckets = new Entry[capacity];

    public MyMap() {}

    public void put(String key, Integer val) {
        if (key == null) throw new RuntimeException("Key cannot be null for MyMap!");
        if (size == capacity) throw new RuntimeException("The MyMap is full.");

        int k = key2hashCode(key);

        if (buckets[k] == null) {
            buckets[k] = new Entry(key, val);
        } else {
            Entry cur = buckets[k];
            Entry last = null;
            while (cur != null && !cur.key.equals(key)) {
                last = cur;
                cur = cur.next;
            }

            if (cur == null) {
                last.next = new Entry(key, val);
            } else {
                cur.val = val;
            }
        }

        size++;
    }

    public Integer get(String key) {
        if (key == null) throw new RuntimeException("Key cannot be null for MyMap!");
        if(isEmpty()) return null;

        int k = key2hashCode(key);

        Entry e = buckets[k];

        while (e != null && !e.key.equals(key)) {
            e = e.next;
        }

        return e == null ? null : e.val;
    }

    public void delete(String key) {
        if (key == null) throw new RuntimeException("Key cannot be null for MyMap!");
        if (isEmpty()) return;

        int k = key2hashCode(key);

        Entry e = buckets[k];
        Entry head = e;
        Entry last = e;

        while (e != null && !e.key.equals(key)) {
            last = e;
            e = e.next;
        }

        if (e == null) return;

        if (last == head) {
            buckets[k] = null;
        } else {
            last.next = last.next.next;
        }

        size --;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(String key) {
        if (key == null) throw new RuntimeException("Key cannot be null for MyMap!");

        int k = key2hashCode(key);

        Entry e = buckets[k];

        while (e != null) {
            if (e.key.equals(key)) return true;
            e = e.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    private int key2hashCode(String key) {
        int ret = 0;
        char[] chars = key.toCharArray();
        int len = chars.length;
        int base = 27;

        for (int i = 0; i < len; i++) {
            ret += (chars[i] - 'a' + 1) * Math.pow(base, i);
        }

        return ret % capacity;
    }

    class Entry {
        String key;
        Integer val;
        Entry next;

        Entry(String key, Integer val) {
            this.key = key;
            this.val = val;
        }

        public String toString() {
            if(next == null) return String.format("\"%s\":%d", key, val);

            return String.format("\"%s\":%d,%s", key, val, next);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean isFirst = true;

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            if (isFirst) {
                sb.append(buckets[i]);
                isFirst = false;
            } else {
                sb.append("," + buckets[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyMap map = new MyMap();

        System.out.println("Initialize MyMap");

        System.out.println(map);

        System.out.println("Is map empty? " + map.isEmpty());

        System.out.println("map size:" + map.size());

        System.out.println("get lifei:" + map.get("lifei"));

        System.out.println("contains lifei? " + map.containsKey("lifei"));

        map.put("lifei", 34);

        System.out.println("map.put(\"lifei\", 34)");

        System.out.println(map);

        System.out.println("Is map empty? " + map.isEmpty());

        System.out.println("map size:" + map.size());

        System.out.println("get lifei:" + map.get("lifei"));

        System.out.println("contains lifei? " + map.containsKey("lifei"));

        map.put("simi", 6);

        System.out.println("map.put(\"simi\", 6)");

        System.out.println(map);

        System.out.println("Is map empty? " + map.isEmpty());

        System.out.println("map size:" + map.size());

        System.out.println("get lifei:" + map.get("lifei"));

        System.out.println("get simi:" + map.get("simi"));

        System.out.println("contains simi? " + map.containsKey("simi"));

        map.delete("lifei");

        System.out.println("map.delete(\"lifei\")");

        System.out.println(map);

        System.out.println("Is map empty? " + map.isEmpty());

        System.out.println("map size:" + map.size());

        System.out.println("get lifei:" + map.get("lifei"));

        System.out.println("get simi:" + map.get("simi"));

        System.out.println("contains lifei? " + map.containsKey("lifei"));
    }
}
