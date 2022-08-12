package selfpractice.graph.detectcycle;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int[][][] gs = {
                {{0, 1}, {0, 2}}, // false
                {{0, 1}, {1, 0}}, // true
                {{0, 1}, {2, 3}}, // false
                {{0, 1}, {1, 4}, {2, 3}, {4, 1}}, //true
                {}, //false
        };

        for (int[][] g : gs)
            System.out.println(hasCycle2(g));
    }

    public static boolean hasCycle(int[][] g) {
        // graph conversion
        Map<Integer, Node> graphNodes = new HashMap<>();


        for(int[] subg : g) {
            Node s, e;
            if (graphNodes.containsKey(subg[0])) {
                s = graphNodes.get(subg[0]);
            } else {
                s = new Node(subg[0]);
                graphNodes.put(subg[0], s);
            }

            if (graphNodes.containsKey(subg[1])) {
                e = graphNodes.get(subg[1]);
            } else {
                e = new Node(subg[1]);
                graphNodes.put(subg[1], e);
            }

            s.addChild(e);

            s.outDegree++;
            e.inDegree++;
        }

//        for(Node node: graphNodes.values()) {
//            System.out.println(String.format("node %d indegree %d, outdegree %d", node.val, node.inDegree, node.outDegree));
//        }

        Set<Integer> roots = graphNodes.values().stream().filter(n -> n.inDegree == 0).map(n -> n.val)
                .collect(Collectors.toSet());

        if (roots.isEmpty() && g.length > 0) return true;

        // DFS each connected subgraph
        for(Integer rootSeq: roots) {
            Set<Integer> visited = new HashSet<>();
            if (hasCycle(graphNodes.get(rootSeq), visited)) return true;
        }

        return false;
    }

    static boolean hasCycle(Node root, Set<Integer> visited) {
//        System.out.println(String.format("node:%d, visited:%s", root.val, visited));
        if (root == null) return false;
        if (visited.contains(root.val)) return true;
        visited.add(root.val);

        for (Node child: root.children) {
            if (hasCycle(child, visited)) return true;
        }

        return false;
    }

    public static boolean hasCycle2(int[][] g) {
        int n = 0;
        for (int[] e : g) {
            n = Math.max(Math.max(n, e[0]), e[1]);
        }

        List<Integer>[] to = new LinkedList[n + 1];

        for (int i = 0; i < n + 1; i++) to[i] = new LinkedList<>();

        boolean[] visited = new boolean[n + 1];

        for (int[] e : g) {
            int x = e[0], y = e[1];
            to[x].add(y);
        }

        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(visited, false);
            if (hasCycle2(i, to, visited)) return true;
        }

        return false;
    }

    static boolean hasCycle2(int start, List<Integer>[] to, boolean[] visited) {
        visited[start] = true;

        for (int next : to[start]) {
            if (visited[next]) return true;
            if (hasCycle2(next, to, visited)) return true;
        }

        return false;
    }
}
class Node {
    int val;
    List<Node> children = new LinkedList<>();
    int inDegree;
    int outDegree;

    Node(int val) {
        this.val = val;
    }

    void addChild(Node child) {
        children.add(child);
    }
}
