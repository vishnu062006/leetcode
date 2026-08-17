class Solution {
    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        dp = new int[n][n];
        prefix = new int[n + 1];

        // Prefix sums
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        // Only one stone -> cannot split
        if (i >= j) {
            return 0;
        }

        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int ans = 0;

        for (int k = i; k < j; k++) {

            // Sum of left part [i...k]
            int left = prefix[k + 1] - prefix[i];

            // Sum of right part [k+1...j]
            int right = prefix[j + 1] - prefix[k + 1];

            if (left < right) {
                // Bob removes right
                ans = Math.max(ans,
                        left + solve(i, k));

            } else if (left > right) {
                // Bob removes left
                ans = Math.max(ans,
                        right + solve(k + 1, j));

            } else {
                // Equal -> Alice can choose either
                ans = Math.max(ans,
                        Math.max(
                            left + solve(i, k),
                            right + solve(k + 1, j)
                        ));
            }
        }

        return dp[i][j] = ans;
    }
}