package selfpractice.flexport;

// Connection board game

//                   B
//                   R
//       R     B  R  R  R
//       -  -  -  -  -  -  -  -  -
// ...  -3 -2 -1  0  1  2  3  4  5  ...


/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    final static int BOUND = Integer.MAX_VALUE;

    // positon -> vertical list
    static Map<Integer, List<Block>> posMap = new HashMap<>();

    final static int CONTIGUOUS_COUNT = 3;

    public static void main(String[] args) {
        // putBlock(Block.B, 0);
        // putBlock(Block.B, 0);
        // putBlock(Block.B, 0);
        // putBlock(Block.R, 0);
        // putBlock(Block.B, 0);
        // putBlock(Block.B, 0);
        // putBlock(Block.B, 0);

        putBlock(Block.B, -1);
        putBlock(Block.B, 0);
        putBlock(Block.B, 1);
        putBlock(Block.R, 1);
        putBlock(Block.R, 1);
        putBlock(Block.R, 1);
        putBlock(Block.B, 2);
        putBlock(Block.B, 2);

        putBlock(Block.R, 0);
        putBlock(Block.R, -1);
    }

    public static void putBlock(Block blk, int pos) {
        if (posMap.containsKey(pos)) {
            posMap.get(pos).add(blk);
        } else {
            List<Block> blks = new ArrayList<>();
            blks.add(blk);
            posMap.put(pos, blks);
        }

        System.out.println(isSuccess(pos));
    }

    public static boolean isSuccess(int pos) {
        if (hasContiguousBlkFromTop(posMap.get(pos))
                || hasContiguousBlkHorizontally(pos)) return true;

        return false;
    }

    public static boolean hasContiguousBlkHorizontally(int pos) {
        List<List<Block>> scope = new ArrayList<>();

        List<Block> mark = posMap.get(pos);

        for (int i = 0; i < 2 * CONTIGUOUS_COUNT - 1; i++) {
            List<Block> t = posMap.getOrDefault(pos - CONTIGUOUS_COUNT + 1 + i, new ArrayList<Block>());

            scope.add(t);
        }

        OUT: for (int i = 0; i < CONTIGUOUS_COUNT; i ++) {
            int height = mark.size();
            Block markBlock = mark.get(height - 1);

            for (int j = 0; j < CONTIGUOUS_COUNT; j++) {
                if (scope.get(i + j).size() >= height) {
                    if (scope.get(i + j).get(height - 1) != markBlock) continue OUT;
                } else continue OUT;
            }

            return true;
        }

        return false;
    }

    public static boolean hasContiguousBlkFromTop(List<Block> blks) {
        if (blks.size() < CONTIGUOUS_COUNT) return false;

        Block mark = blks.get(blks.size() - 1);
        for (int i = 0; i < CONTIGUOUS_COUNT - 1; i ++) {
            if ( mark != blks.get(blks.size() - 2 - i)) return false;
        }

        return true;
    }

    public static boolean hasContiguousSameBlk(List<Block> blks) {

        OUTER: for (int i = 0; i < blks.size() - CONTIGUOUS_COUNT + 1; i++) {
            Block cur = blks.get(i);

            for (int j = 0; j < CONTIGUOUS_COUNT - 1; j++) {
                if (blks.get(i + 1 + j) != cur) {
                    break OUTER;
                }
            }

            return true;

        }
        return false;
    }
}

enum Block {
    B, R
}
