int countSubmatrices(int** grid, int gridSize, int* gridColSize, int k) {
    int m = gridSize;
    int n = gridColSize[0];
    
    int cols[n];  // stores column sums
    for (int j = 0; j < n; j++) cols[j] = 0;

    int count = 0;

    for (int i = 0; i < m; i++) {
        int sum = 0;
        
        for (int j = 0; j < n; j++) {
            cols[j] += grid[i][j];  // update column sum
            sum += cols[j];         // running prefix sum
            
            if (sum <= k) count++;
        }
    }

    return count;
}