char* getHappyString(int n, int k) {
    if(k > 3 * (1 << (n-1))) 
        return strdup(""); 

    char *res = malloc(n + 1);
    char prev = '\0';

    for(int i = 0; i < n; i++) {
        for(char c = 'a'; c <= 'c'; c++) {
            if(c == prev) 
                continue;
            int count = 1 << (n - i - 1);
            if(k > count) {
                k -= count;
            }
            else {
                res[i] = c;
                prev = c;
                break;
            }
        }
    }
    res[n] = '\0';
    return res;
}