class Solution {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    int m, n;
    
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, boolean[][] visited, int r, int c, int pr, int pc) {
        visited[r][c] = true;
        
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            
            if (nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
            if (grid[nr][nc] != grid[r][c]) continue;
            
            // Skip parent
            if (nr == pr && nc == pc) continue;
            
            if (visited[nr][nc]) return true;
            
            if (dfs(grid, visited, nr, nc, r, c)) return true;
        }
        
        return false;
    }
}