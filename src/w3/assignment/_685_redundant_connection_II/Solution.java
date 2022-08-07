package w3.assignment._685_redundant_connection_II;

import java.util.*;

public class Solution {
    //出边数组
    List<Integer>[] out;
    // 候选的可删除边
    Deque<Integer[]> canDeleteEdges = new LinkedList<>();
    // 输入的图是否有根，是否有根影响到对候选可删除边的选取
    boolean hasRoot;
    // 如果有根，则为根节点；不然为-1;
    int root = -1;
    //true: Found a circle, false: Found a meet node
    Boolean circleOrMeet;
    // 节点是否访问过
    boolean[] visited;
    // 记录每个节点的访问路径
    Map<Integer, List<Integer[]>> nodeInEdges;
    //出度数组
    int[] outdegree;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        // determine the vertices number
        int n = 1;

        for (int i = 0; i < edges.length; i ++) {
            int x = edges[i][0], y = edges[i][1];
            n = Math.max(n, Math.max(x, y));
        }

        // build out-edge array, out-degree array and in-degree array
        out = new List[n + 1];
        for (int i = 1; i < out.length; i++) {
            out[i] = new ArrayList<Integer>();
        }

        int[] indegree = new int[n + 1];
        outdegree = new int[n + 1];

        for (int[] edge: edges) {
            int x = edge[0], y = edge[1];
            out[x].add(y);
            indegree[y]++;
            outdegree[x]++;
        }

        // find the root
        root = -1;

        for(int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                root = i;
                break;
            }
        }

        // System.out.println("root: " + root);
        hasRoot = root > -1;

        canDeleteEdges = new LinkedList<>();
        visited = new boolean[n + 1];
        nodeInEdges = new HashMap<>();

        //situation A: No root, then there must be a cicyle, and any edge in the circle can be deleted
        if (!hasRoot) {
            // iterate all nodes whose in-degree is 1 and out-degree >= 1
            for(int i = 1; i < indegree.length; i++) {
                if (indegree[i] == 1 && outdegree[i] >= 1) {
                    //重新初始化
                    visited = new boolean[n + 1];
                    nodeInEdges = new HashMap<>();
                    dfs(i, 0);
                    if (circleOrMeet != null) break;
                }
            }
        } else {
            //situation B: Root exists
            //   B1: a circle exists, then the last edge forming the circle can be deleted

            //   B2: one node meets another, any one of the two in edge of the meeting node
            dfs(root, 0);
        }

        // System.out.println(String.format("root: %d, hasRoot: %s, cycleOrMeet:%s",
        // root, hasRoot, circleOrMeet));

        // for (Integer[] edge: canDeleteEdges) {
        //     System.out.println(Arrays.toString(edge));
        // }

        for (int i = edges.length - 1; i >= 0; i--) {
            if (inCanDeletedEdges(edges[i])) return edges[i];
        }

        return null;
    }

    void dfs(int node, int father) {
        // System.out.println(String.format("\nvisit node %d father %d", node, father));
        visited[node] = true;

        if (father != 0) {
            List<Integer[]> fatherEdges = nodeInEdges.get(father);
            // System.out.print("father " + father);
            // System.out.print("\n edges: " + fatherEdges);

            List<Integer[]> thisEdge = new ArrayList<>();

            if (fatherEdges != null) {
                thisEdge.addAll(fatherEdges);
            }

            thisEdge.add(new Integer[]{father, node});
            nodeInEdges.put(node, thisEdge);
        }

        // for(Map.Entry<Integer, List<Integer[]>> nodeInedge: nodeInEdges.entrySet()) {
        // System.out.print(String.format("node %d:\n", nodeInedge.getKey()));
        //     for (Integer[] edge: nodeInedge.getValue())
        //         System.out.print(String.format("(%d,%d) -> ", edge[0], edge[1] ));
        // }

        // System.out.println("vsited: " + Arrays.toString(visited));

        for (Integer to: out[node]) {
            if (visited[to]) {
                isMeetOrCircle(node, to);
                determineCanDeleteEdges(node, to);
                return;
            }
            dfs(to, node);
        }
    }

    void determineCanDeleteEdges(Integer currentNode, Integer successorNode) {
        if (!hasRoot) {
            List<Integer[]> circle = new LinkedList<>(nodeInEdges.get(currentNode));
            circle.add(new Integer[]{currentNode, successorNode});
            canDeleteEdges.addAll(circle);
        } else if (circleOrMeet) {
            canDeleteEdges.add(new Integer[]{currentNode, successorNode});
        } else {
            Integer[] recentEdge = new Integer[]{currentNode, successorNode};
            // System.out.println("recentEdge:" + Arrays.toString(recentEdge));
            canDeleteEdges.add(recentEdge);

            List<Integer[]> successorEdges = nodeInEdges.get(successorNode);

            if (successorEdges == null) return;

            Integer[] lastEdgeInSuccessor = successorEdges.get(successorEdges.size() - 1);
            // //如果入度为2的另一条边的起点是根，且根的出度仅为1，那这边不能删除。因为删除后，这个
            // //根就和其他部分不连通了。

            // if(lastEdgeInSuccessor[0].equal(root))
            // System.out.println(String.format("lastEdgeInSuccessor: (%d, %d) outdeg: %d, lastEdgeInSuccessor[0] != root : %s, !lastEdgeInSuccessor[0].equals(root): %s, lastEdgeInSuccessor[0]: %d, root: %d", lastEdgeInSuccessor[0], lastEdgeInSuccessor[1], outdegree[lastEdgeInSuccessor[0]], lastEdgeInSuccessor[0] != root, !lastEdgeInSuccessor[0].equals(root), lastEdgeInSuccessor[0], root));

            if (lastEdgeInSuccessor[0] != root || outdegree[lastEdgeInSuccessor[0]] > 1)
                canDeleteEdges.add(lastEdgeInSuccessor);
        }
    }

    void isMeetOrCircle(Integer currentNode, Integer successorNode) {
        // System.out.println(String.format("isMeetOrCircle currentNode: %d, successorNode: %d", currentNode, successorNode));
        if(nodeInEdges.containsKey(currentNode))
            for (Integer[] edge: nodeInEdges.get(currentNode)) {
                if (edge[0] == successorNode || edge[1] == successorNode) {
                    circleOrMeet = true;
                    return;
                }
            }

        circleOrMeet = false;
    }

    boolean inCanDeletedEdges(int[] edge) {
        for (Integer[] canDeleteEdge: canDeleteEdges) {
            if (canDeleteEdge[0] == edge[0] && canDeleteEdge[1] == edge[1])
                return true;
        }

        return false;
    }
}