int numSpecial(int** mat, int matSize, int* matColSize) {

    int count1[100]={0};
    int count2[100]={0};
    int count=0;

    for(int i=0;i<matSize;i++){
        for(int j=0;j<matColSize[0];j++){
            if(mat[i][j]==1){
                count1[i]++;
                count2[j]++;
            }
        }
    }

    for(int i=0;i<matSize;i++){
        for(int j=0;j<matColSize[0];j++){
            if(mat[i][j]==1 && count1[i]==1 && count2[j]==1){
                count++;
            }
        }
    }

    return count;
}