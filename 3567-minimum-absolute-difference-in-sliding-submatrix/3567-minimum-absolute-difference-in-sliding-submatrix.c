#include <stdlib.h>
#include <limits.h>

int cmp(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

int** minAbsDiff(int** grid, int gridSize, int* gridColSize,
                 int k, int* returnSize, int** returnColumnSizes) {
    
    int m = gridSize;
    int n = gridColSize[0];
    
    int rows = m - k + 1;
    int cols = n - k + 1;
    
    *returnSize = rows;
    *returnColumnSizes = (int*)malloc(rows * sizeof(int));
    
    int** ans = (int**)malloc(rows * sizeof(int*));
    
    for (int i = 0; i < rows; i++) {
        ans[i] = (int*)malloc(cols * sizeof(int));
        (*returnColumnSizes)[i] = cols;
    }
    
    int size = k * k;
    int* arr = (int*)malloc(size * sizeof(int));
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            
            int idx = 0;
            for (int x = i; x < i + k; x++) {
                for (int y = j; y < j + k; y++) {
                    arr[idx++] = grid[x][y];
                }
            }
            qsort(arr, size, sizeof(int), cmp);
            int minDiff = INT_MAX;
            
            for (int t = 1; t < size; t++) {
                if (arr[t] != arr[t - 1]) {
                    int diff = arr[t] - arr[t - 1];
                    if (diff < minDiff)
                        minDiff = diff;
                }
            }
            ans[i][j] = (minDiff == INT_MAX) ? 0 : minDiff;
        }
    }
    free(arr);
    return ans;
}