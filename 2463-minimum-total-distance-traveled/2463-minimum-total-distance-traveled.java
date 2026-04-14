import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        // Expand factories
        List<Integer> factories = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0], limit = f[1];
            for (int i = 0; i < limit; i++) {
                factories.add(pos);
            }
        }

        int n = robot.size();
        int m = factories.size();

        long[][] dp = new long[n + 1][m + 1];

        // Initialize
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // skip factory
                dp[i][j] = dp[i][j - 1];

                // use factory
                dp[i][j] = Math.min(
                    dp[i][j],
                    dp[i - 1][j - 1] + Math.abs(robot.get(i - 1) - factories.get(j - 1))
                );
            }
        }

        return dp[n][m];
    }
}