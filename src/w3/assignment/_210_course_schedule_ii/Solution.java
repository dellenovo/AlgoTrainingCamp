package w3.assignment._210_course_schedule_ii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    List<Integer>[] out;
    Queue<Integer> finishedCourses;
    int[] indegrees;
    int numCourses;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 1) return new int[]{0};
        this.numCourses = numCourses;

        out = new List[numCourses];

        for (int i = 0; i < numCourses; i++)
            out[i] = new ArrayList<Integer>();

        finishedCourses = new LinkedList<>();

        // build out-rage array and indegree array
        indegrees = new int[numCourses];

        for (int[] dep: prerequisites) {
            int x = dep[1], y = dep[0];
            out[x].add(y);
            indegrees[y]++;
        }

        // BFS
        bfs();

        int[] ans = new int[0];

        if (finishedCourses.size() == numCourses) {
            ans = new int[finishedCourses.size()];

            int i = 0;
            for (int course: finishedCourses) ans[i++] = course;
        }

        return ans;
    }

    void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        //add all nodes with 0 indegree to the queue
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
