package w3.leetcode._207_course_schedule;

import java.util.*;

public class WrongSolution {
    List<Integer>[] out;
    Set<Integer> finishedCourses;
    int[] indegrees;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        out = new List[numCourses];

        for (int i = 0; i < numCourses; i++)
            out[i] = new ArrayList<Integer>();

        finishedCourses = new HashSet<>();

        // build out-rage array and indegree array
        indegrees = new int[numCourses];

        for (int[] dep: prerequisites) {
            int x = dep[1], y = dep[0];
            out[x].add(y);
            indegrees[y]++;
        }

        // BFS all node with 0 indegree
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] > 0) continue;
            roots.add(i);
        }

        for(int node: roots) {
            bfs(node);
        }

        return finishedCourses.size() == numCourses;
    }

    void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int node = queue.remove();

            if (indegrees[node] > 0) continue;
            else if (indegrees[node] == 0) {
                finishedCourses.add(node);
            }

            for (Integer to: out[node]) {
                indegrees[to]--;
                queue.add(to);
            }
        }
    }
}
