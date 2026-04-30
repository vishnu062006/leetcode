class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        int[][] dp = new int[n][k + 1];
        for (int j = 0; j < n; j++) {
            for (int c = 0; c <= k; c++) {
                dp[j][c] = -1;
            }
        }
        
        dp[0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            int[][] newDp = new int[n][k + 1];
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    newDp[j][c] = -1;
                }
            }
            
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (dp[j][c] == -1) continue;
                    
                    int cost = (grid[i][j] == 0 ? 0 : 1);
                    int newCost = c + cost;
                    if (newCost > k) continue;
                    
                    int score = dp[j][c] + grid[i][j];
                    
                    // from top
                    newDp[j][newCost] = Math.max(newDp[j][newCost], score);
                }
            }
            
            // LEFT transitions (separate pass!)
            for (int j = 1; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (newDp[j - 1][c] == -1) continue;
                    
                    int cost = (grid[i][j] == 0 ? 0 : 1);
                    int newCost = c + cost;
                    if (newCost > k) continue;
                    
                    int score = newDp[j - 1][c] + grid[i][j];
                    
                    newDp[j][newCost] = Math.max(newDp[j][newCost], score);
                }
            }
            
            dp = newDp;
        }
        
        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[n - 1][c]);
        }
        
        return ans;
    }
}