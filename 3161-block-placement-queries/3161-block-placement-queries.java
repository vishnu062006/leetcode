class Solution {

    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void update(int idx, int val) {
            idx++;
            while (idx <= n + 1) {
                bit[idx] = Math.max(bit[idx], val);
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            idx++;
            int res = 0;
            while (idx > 0) {
                res = Math.max(res, bit[idx]);
                idx -= idx & -idx;
            }
            return res;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int maxX = 0;

        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(maxX + 1);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        Fenwick bit = new Fenwick(maxX + 2);

        TreeSet<Integer> curr = new TreeSet<>(obstacles);

        Integer prev = curr.first();
        for (Integer x : curr.tailSet(prev, false)) {
            bit.update(x, x - prev);
            prev = x;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];

            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];

                Integer p = curr.floor(x);

                int best = bit.query(p);
                int tailGap = x - p;

                ans.add(Math.max(best, tailGap) >= sz);
            } else {
                int x = q[1];

                Integer l = curr.lower(x);
                Integer r = curr.higher(x);

                bit.update(r, r - l);

                curr.remove(x);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}