int countNumbersWithUniqueDigits(int n) {
    int dp[9];
    dp[0]=1;
    dp[1]=9;
    for(int i=2;i<=n;i++){
        dp[i]=dp[i-1]*(11-i);
    }
    int tot=1;
    for(int i=1;i<=n;i++){
        tot+=dp[i];
    }

    return tot;
}