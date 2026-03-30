int balancedStringSplit(char* s) {
    int bal = 0, count = 0;
    for(int i = 0; s[i] != '\0'; i++) {
        if(s[i] == 'L') bal++;
        else bal--;
        if(bal == 0) count++;
    }
    return count;
}