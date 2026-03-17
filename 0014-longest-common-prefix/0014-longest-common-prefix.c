char* longestCommonPrefix(char** strs, int strsSize) {
    int n= strsSize;
    if(n==0)    return "";

    for(int i=0;strs[0][i];i++){
        char ch=strs[0][i];

        for(int j=1; j<n; j++){
            if(strs[j][i]!=ch){
                strs[0][i]='\0';
                return strs[0];
            }
        }

    }
    return strs[0];
}