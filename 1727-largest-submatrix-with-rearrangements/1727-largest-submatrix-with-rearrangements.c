int cmp(const void* a, const void* b) {
    return (*(int*)b - *(int*)a);  // descending
}

int largestSubmatrix(int** matrix, int matrixSize, int* matrixColSize) {
    int i, j, ans = 0;
    int rows = matrixSize;
    int cols = matrixColSize[0];

    // step 1: consecutive 1s upward
    for (i = 1; i < rows; i++)
        for (j = 0; j < cols; j++)
            if (matrix[i][j] != 0)
                matrix[i][j] = matrix[i-1][j] + 1;

    // step 2: sort each row descending using qsort
    for (i = 0; i < rows; i++) {
        qsort(matrix[i], cols, sizeof(int), cmp);

        // step 3: max area
        for (j = 0; j < cols; j++)
            if ((j + 1) * matrix[i][j] > ans)
                ans = (j + 1) * matrix[i][j];
    }

    return ans;
}