int numberOfSubmatrices(char** grid, int n, int* m) {
    int rows = n;
    int cols = m[0];
    
    int ans = 0;
    
    int prefix[1001][1001] = {0};   // sum
    int countX[1001][1001] = {0};   // count of X
    
    for(int i = 1; i <= rows; i++) {
        for(int j = 1; j <= cols; j++) {
            
            int val = 0;
            if(grid[i-1][j-1] == 'X') val = 1;
            else if(grid[i-1][j-1] == 'Y') val = -1;
            
            // prefix sum
            prefix[i][j] = val 
                + prefix[i-1][j] 
                + prefix[i][j-1] 
                - prefix[i-1][j-1];
            
            // count X
            countX[i][j] = (grid[i-1][j-1] == 'X')
                + countX[i-1][j]
                + countX[i][j-1]
                - countX[i-1][j-1];
            
            // check condition
            if(prefix[i][j] == 0 && countX[i][j] > 0) {
                ans++;
            }
        }
    }
    
    return ans;
}