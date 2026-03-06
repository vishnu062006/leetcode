bool checkOnesSegment(char* s) {
    int n = strlen(s);

    for(int i = 1; i < n; i++){
        if(s[i] == '1' && s[i-1] == '0'){
            return false;
        }
    }

    return true;
}