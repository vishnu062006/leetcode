class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long sum = 0;
        for (int[] row : grid)
            for (int val : row)
                sum += val;

        // Check horizontal cuts (top to bottom)
        long topSum = 0;
        Set<Long> seen = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int val : grid[i]) topSum += val;
            long diff = topSum - (sum - topSum);
            for (int val : grid[i]) seen.add((long) val);
            if (diff == 0 || diff == grid[0][0] || diff == grid[0][n-1] || diff == grid[i][0] || diff == grid[i][n-1]) return true;
            if (n > 1 && i > 0 && seen.contains(diff)) return true;
        }

        // Check horizontal cuts (bottom to top)
        topSum = 0;
        seen = new HashSet<>();
        for (int i = m - 1; i >= 0; i--) {
            for (int val : grid[i]) topSum += val;
            long diff = topSum - (sum - topSum);
            for (int val : grid[i]) seen.add((long) val);
            if (diff == 0 || diff == grid[m-1][0] || diff == grid[m-1][n-1] || diff == grid[i][0] || diff == grid[i][n-1]) return true;
            if (n > 1 && i < m - 1 && seen.contains(diff)) return true;
        }

        // Check vertical cuts (left to right)
        long leftSum = 0;
        seen = new HashSet<>();
        for (int j = 0; j < n; j++) {
            for (int[] row : grid) leftSum += row[j];
            long diff = leftSum - (sum - leftSum);
            for (int[] row : grid) seen.add((long) row[j]);
            if (diff == 0 || diff == grid[0][0] || diff == grid[m-1][0] || diff == grid[0][j] || diff == grid[m-1][j]) return true;
            if (m > 1 && j > 0 && seen.contains(diff)) return true;
        }

        // Check vertical cuts (right to left)
        leftSum = 0;
        seen = new HashSet<>();
        for (int j = n - 1; j >= 0; j--) {
            for (int[] row : grid) leftSum += row[j];
            long diff = leftSum - (sum - leftSum);
            for (int[] row : grid) seen.add((long) row[j]);
            if (diff == 0 || diff == grid[0][n-1] || diff == grid[m-1][n-1] || diff == grid[0][j] || diff == grid[m-1][j]) return true;
            if (m > 1 && j < n - 1 && seen.contains(diff)) return true;
        }

        return false;
    }
}