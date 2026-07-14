class Solution {
    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[][] dp = new int[max + 1][max + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] ndp = new int[max + 1][max + 1];

            for (int g1 = 0; g1 <= max; g1++) {
                for (int g2 = 0; g2 <= max; g2++) {
                    int cur = dp[g1][g2];
                    if (cur == 0) continue;

                    // Skip current number
                    ndp[g1][g2] = (ndp[g1][g2] + cur) % MOD;

                    // Put into first subsequence
                    int ng1 = (g1 == 0) ? num : gcd(g1, num);
                    ndp[ng1][g2] = (ndp[ng1][g2] + cur) % MOD;

                    // Put into second subsequence
                    int ng2 = (g2 == 0) ? num : gcd(g2, num);
                    ndp[g1][ng2] = (ndp[g1][ng2] + cur) % MOD;
                }
            }

            dp = ndp;
        }

        long ans = 0;
        for (int g = 1; g <= max; g++) {
            ans += dp[g][g];
        }

        return (int) (ans % MOD);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}