int minSwaps(char* s) {
    int open=0;
    int close=0;
    int count=0;
    for(int i=0;s[i]!='\0';i++){
        if(s[i]=='[')   open++;
        if(s[i]==']')   close++;

        if(close-open>0){    
            count++;
            close-=2;
        }
    }
    return count;
}