class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        // Length 2 initialization
        for (int i = 0; i < m; i++) {
            up[i] = i;               // previous value < current
            down[i] = m - 1 - i;     // previous value > current
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];

            long[] prefDown = new long[m + 1];
            long[] prefUp = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefDown[i + 1] = (prefDown[i] + down[i]) % MOD;
                prefUp[i + 1] = (prefUp[i] + up[i]) % MOD;
            }

            for (int j = 0; j < m; j++) {
                // Need previous comparison to be down,
                // and previous value < current value
                newUp[j] = prefDown[j];

                // Need previous comparison to be up,
                // and previous value > current value
                newDown[j] =
                        (prefUp[m] - prefUp[j + 1] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        if (n == 2) {
            for (int i = 0; i < m; i++) {
                ans = (ans + up[i] + down[i]) % MOD;
            }
            return (int) ans;
        }

        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}