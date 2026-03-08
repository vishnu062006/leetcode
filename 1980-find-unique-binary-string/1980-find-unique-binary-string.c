char* findDifferentBinaryString(char** nums, int numsSize) {
    char* res=(char*)malloc(sizeof(char)*numsSize+1);
    for(int i=0;i<numsSize;i++){
        if(nums[i][i]=='0')   res[i]='1';
        else res[i]='0';
    }
    res[numsSize]='\0';
    return res;
}