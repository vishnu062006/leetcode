class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        long total = 0;
        
        // Total sum
        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }
        
        // If total is odd → can't split equally
        if (total % 2 != 0) return false;
        
        long target = total / 2;
        
        // Try horizontal cuts
        long prefix = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                prefix += grid[i][j];
            }
            if (prefix == target) return true;
        }
        
        // Try vertical cuts
        prefix = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                prefix += grid[i][j];
            }
            if (prefix == target) return true;
        }
        
        return false;
    }
}