package w3.leetcode._207_course_schedule;

import java.util.*;

public class Solution {
    List<Integer>[] out;
    Set<Integer> finishedCourses;
    int[] indegrees;
    int numCourses;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.numCourses = numCourses;

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
        // Set<Integer> roots = new HashSet<>();
        // for (int i = 0; i < numCourses; i++) {
        //     if (indegrees[i] > 0) continue;
        //     roots.add(i);
        // }

        // for(int node: roots) {
        //     bfs(node);
        // }

        bfs();

        return finishedCourses.size() == numCourses;
    }

    void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] > 0) continue;
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.remove();
            finishedCourses.add(node);

            for (Integer to: out[node]) {
                indegrees[to]--;
                if (indegrees[to] == 0)
                    queue.add(to);
            }
        }
    }
}
