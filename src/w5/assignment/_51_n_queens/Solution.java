package w5.assignment._51_n_queens;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    List<int[]> qcols;
    List<List<String>> ans;
    Deque<Integer> qcolstack;
    int n;
    Set<Integer> visited;
    Set<Integer> leftDiagVisited;
    Set<Integer> rightDiagVisited;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        qcols = new LinkedList<>();

        qcolstack = new ArrayDeque<>();

        visited = new HashSet<>();
        leftDiagVisited = new HashSet<>();
        rightDiagVisited = new HashSet<>();

        ans = new LinkedList<>();

        dfs(0);

        for (int[] qcol : qcols) {
            List<char[]> pattern = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] tempRow = new char[n];
                Arrays.fill(tempRow, '.');
                pattern.add(tempRow);
            }

            for (int k = 0; k < n; k++)
                pattern.get(k)[qcol[k]] = 'Q';

            ans.add(pattern.stream().map(String::new).collect(Collectors.toList()));
        }

        return ans;
    }

    void dfs(int index) {
        if (index >= n) {
            assign2Array();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited.contains(i) || leftDiagVisited.contains(i + index) || rightDiagVisited.contains(i - index)) continue;
            qcolstack.push(i);
            visited.add(i);
            leftDiagVisited.add(i + index);
            rightDiagVisited.add(i - index);
            dfs(index + 1);
            qcolstack.pop();
            visited.remove(i);
            leftDiagVisited.remove(i + index);
            rightDiagVisited.remove(i - index);
        }
    }

    void assign2Array() {
        Iterator<Integer> itr = qcolstack.descendingIterator();

        int[] t = new int[n];
        int i = 0;
        while (itr.hasNext()) {
            t[i++] = itr.next();
        }

        qcols.add(t);
    }
}
