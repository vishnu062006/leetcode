class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Count non-zero digits
        int m = 0;
        for (char c : s.toCharArray()) {
            if (c != '0') m++;
        }

        int[] digit = new int[m + 1];
        int[] pos = new int[m + 1];

        int idx = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                digit[idx] = s.charAt(i) - '0';
                pos[idx] = i;
                idx++;
            }
        }

        // Prefix digit sums
        long[] prefSum = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            prefSum[i] = prefSum[i - 1] + digit[i];
        }

        // Prefix concatenated numbers
        long[] prefNum = new long[m + 1];
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;

        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
            prefNum[i] = (prefNum[i - 1] * 10 + digit[i]) % MOD;
        }

        // next[i] = first non-zero digit index >= i
        int[] next = new int[n];
        int p = 1;
        for (int i = 0; i < n; i++) {
            while (p <= m && pos[p] < i) p++;
            next[i] = p;
        }

        // prev[i] = last non-zero digit index <= i
        int[] prev = new int[n];
        p = m;
        for (int i = n - 1; i >= 0; i--) {
            while (p >= 1 && pos[p] > i) p--;
            prev[i] = p;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int L = next[l];
            int R = prev[r];

            if (L > R) {
                ans[i] = 0;
                continue;
            }

            int len = R - L + 1;

            long x = (prefNum[R]
                    - prefNum[L - 1] * pow10[len] % MOD
                    + MOD) % MOD;

            long sum = prefSum[R] - prefSum[L - 1];

            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }
}