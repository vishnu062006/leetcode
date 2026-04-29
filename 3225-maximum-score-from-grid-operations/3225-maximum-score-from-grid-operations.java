class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;

        // Prefix sums for each column
        long[][] pref = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[j][i + 1] = pref[j][i] + grid[i][j];
            }
        }

        long[][][] dp = new long[n][n + 1][n + 1];

        // Initialize for first column
        for (int h = 0; h <= n; h++) {
            dp[0][h][0] = 0;
        }

        for (int col = 1; col < n; col++) {

            // Precompute prefix max and suffix max
            long[][] prefixMax = new long[n + 1][n + 1];
            long[][] suffixMax = new long[n + 1][n + 2];

            for (int prev = 0; prev <= n; prev++) {
                long best = Long.MIN_VALUE;

                for (int k = 0; k <= n; k++) {
                    long val = dp[col - 1][prev][k];
                    val -= Math.max(0, pref[col - 1][k] - pref[col - 1][prev]);
                    best = Math.max(best, val);
                    prefixMax[prev][k] = best;
                }

                best = Long.MIN_VALUE;
                for (int k = n; k >= 0; k--) {
                    best = Math.max(best, dp[col - 1][prev][k]);
                    suffixMax[prev][k] = best;
                }
            }

            for (int curr = 0; curr <= n; curr++) {
                for (int prev = 0; prev <= n; prev++) {

                    long res;

                    if (curr <= prev) {
                        res = suffixMax[prev][0] 
                            + (pref[col][prev] - pref[col][curr]);
                    } else {
                        long option1 = suffixMax[prev][curr];

                        long option2 = prefixMax[prev][curr]
                                + (pref[col - 1][curr] - pref[col - 1][prev]);

                        res = Math.max(option1, option2);
                    }

                    dp[col][curr][prev] = res;
                }
            }
        }

        long ans = 0;

        // Last column must be either 0 or n
        for (int prev = 0; prev <= n; prev++) {
            ans = Math.max(ans, dp[n - 1][0][prev]);
            ans = Math.max(ans, dp[n - 1][n][prev]);
        }

        return ans;
    }
}