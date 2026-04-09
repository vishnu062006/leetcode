import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;

        // group queries by k and remainder (l % k)
        Map<Integer, Map<Integer, List<int[]>>> small = new HashMap<>();
        List<int[]> large = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k <= B) {
                small.putIfAbsent(k, new HashMap<>());
                int rem = l % k;
                small.get(k).putIfAbsent(rem, new ArrayList<>());
                small.get(k).get(rem).add(q);
            } else {
                large.add(q);
            }
        }

        // process small k using diff array
        for (int k : small.keySet()) {
            Map<Integer, List<int[]>> remMap = small.get(k);

            for (int rem : remMap.keySet()) {
                List<Integer> indices = new ArrayList<>();
                for (int i = rem; i < n; i += k) {
                    indices.add(i);
                }

                int m = indices.size();
                long[] diff = new long[m + 1];
                Arrays.fill(diff, 1);

                for (int[] q : remMap.get(rem)) {
                    int l = q[0], r = q[1], v = q[3];

                    int start = (l - rem) / k;
                    int end = (r - rem) / k;

                    diff[start] = (diff[start] * v) % MOD;

                    if (end + 1 < m) {
                        diff[end + 1] = (diff[end + 1] * modInv(v)) % MOD;
                    }
                }

                long curr = 1;
                for (int i = 0; i < m; i++) {
                    curr = (curr * diff[i]) % MOD;
                    int idx = indices.get(i);
                    nums[idx] = (int) ((nums[idx] * curr) % MOD);
                }
            }
        }

        // process large k brute force
        for (int[] q : large) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int i = l; i <= r; i += k) {
                nums[i] = (int) ((long) nums[i] * v % MOD);
            }
        }

        // compute XOR
        int xor = 0;
        for (int x : nums) xor ^= x;

        return xor;
    }

    private long modInv(long x) {
        return pow(x, MOD - 2);
    }

    private long pow(long a, long b) {
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}