void insert(int *top, int val){
    for(int i=0;i<3;i++){
        if(top[i]==val) return;
    }
    for(int i=0;i<3;i++){
        if(val > top[i]){
            for(int j=2;j>i;j--) 
                top[j]=top[j-1];
            top[i]=val;
            return;
        }
    }
}
int* getBiggestThree(int** grid, int gridSize, int* gridColSize, int* returnSize) {
    int m = gridSize;
    int n = gridColSize[0];
    int top[3] = {0,0,0};
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            insert(top, grid[i][j]);
            for(int k=1; ;k++){
                if(i + 2*k >= m || j-k < 0 || j+k >= n)     break;
                int sum = 0;
                int r = i, c = j;
                for(int t=0;t<k;t++) sum += grid[r+t][c+t];
                for(int t=0;t<k;t++) sum += grid[r+k+t][c+k-t];
                for(int t=0;t<k;t++) sum += grid[r+2*k-t][c-t];
                for(int t=0;t<k;t++) sum += grid[r+k-t][c-k+t];
                insert(top, sum);
            }
        }
    }
    int count = 3;
    while(count>0 && top[count-1]==0) count--;
    int *ans = malloc(sizeof(int)*count);
    for(int i=0;i<count;i++) ans[i] = top[i];
    *returnSize = count;
    return ans;
}