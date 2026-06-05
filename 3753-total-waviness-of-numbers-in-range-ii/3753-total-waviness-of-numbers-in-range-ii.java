import java.util.*;

class Solution {

    static class Node {
        long ways;
        long sum;

        Node(long w, long s) {
            ways = w;
            sum = s;
        }
    }

    String s;
    HashMap<Long, Node> memo;

    private long encode(int pos, int tight, int started, int last1, int last2) {
        long key = pos;
        key = key * 2 + tight;
        key = key * 2 + started;
        key = key * 11 + (last1 + 1);
        key = key * 11 + (last2 + 1);
        return key;
    }

    private Node dfs(int pos, int tight, int started, int last1, int last2) {
        if (pos == s.length()) {
            return new Node(1, 0);
        }

        long key = encode(pos, tight, started, last1, last2);

        if (tight == 0 && memo.containsKey(key))
            return memo.get(key);

        int limit = tight == 1 ? s.charAt(pos) - '0' : 9;

        long ways = 0;
        long sum = 0;

        for (int d = 0; d <= limit; d++) {
            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {
                Node nxt = dfs(pos + 1, ntight, 0, -1, -1);
                ways += nxt.ways;
                sum += nxt.sum;
            } else {
                int add = 0;

                if (started == 1 && last2 != -1) {
                    if ((last1 > last2 && last1 > d)
                            || (last1 < last2 && last1 < d)) {
                        add = 1;
                    }
                }

                int nlast2 = started == 0 ? -1 : last1;
                int nlast1 = d;

                Node nxt = dfs(pos + 1, ntight, 1, nlast1, nlast2);

                ways += nxt.ways;
                sum += nxt.sum + (long) add * nxt.ways;
            }
        }

        Node res = new Node(ways, sum);

        if (tight == 0)
            memo.put(key, res);

        return res;
    }

    private long solve(long x) {
        if (x <= 0)
            return 0;

        s = Long.toString(x);
        memo = new HashMap<>();

        return dfs(0, 1, 0, -1, -1).sum;
    }

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }
}