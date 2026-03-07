int minFlips(char* s) {
    int n = strlen(s);

    char t[200005];
    strcpy(t, s);
    strcat(t, s);

    int diff1 = 0, diff2 = 0;
    int res = INT_MAX;
    int l = 0;

    for(int r = 0; r < 2*n; r++){

        char expected1 = (r % 2 == 0) ? '0' : '1';
        char expected2 = (r % 2 == 0) ? '1' : '0';

        if(t[r] != expected1) diff1++;
        if(t[r] != expected2) diff2++;

        if(r - l + 1 > n){
            char exp1 = (l % 2 == 0) ? '0' : '1';
            char exp2 = (l % 2 == 0) ? '1' : '0';

            if(t[l] != exp1) diff1--;
            if(t[l] != exp2) diff2--;

            l++;
        }

        if(r - l + 1 == n){
            int curr = diff1 < diff2 ? diff1 : diff2;
            if(curr < res) res = curr;
        }
    }

    return res;
}