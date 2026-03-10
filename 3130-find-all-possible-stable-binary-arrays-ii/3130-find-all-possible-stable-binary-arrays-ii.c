#define MOD 1000000007

int numberOfStableArrays(int zero, int one, int limit) {
    // dp[i][j][k]: i zeros, j ones, ending with k
    long long dp[zero+1][one+1][2];
    memset(dp, 0, sizeof(dp));

    // base cases
    for (int i = 1; i <= zero && i <= limit; i++) dp[i][0][0] = 1;
    for (int j = 1; j <= one  && j <= limit; j++) dp[0][j][1] = 1;

    for (int i = 1; i <= zero; i++) {
        for (int j = 1; j <= one; j++) {
            dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
            if (i - limit - 1 >= 0)
                dp[i][j][0] = (dp[i][j][0] - dp[i-limit-1][j][1] + MOD) % MOD;

            dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
            if (j - limit - 1 >= 0)
                dp[i][j][1] = (dp[i][j][1] - dp[i][j-limit-1][0] + MOD) % MOD;
        }
    }

    return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
}