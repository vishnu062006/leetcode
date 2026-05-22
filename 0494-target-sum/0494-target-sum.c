int findTargetSumWays(int* nums, int numsSize, int target) {
    int sum = 0;
    for (int i = 0; i < numsSize; i++) {
        sum += nums[i];
    }

    if ((sum + target) % 2 != 0 || abs(target) > sum) return 0;

    int P = (sum + target) / 2;
    int dp[P + 1];
    memset(dp, 0, sizeof(dp));
    dp[0] = 1;

    for (int i = 0; i < numsSize; i++) {
        for (int j = P; j >= nums[i]; j--) {
            dp[j] += dp[j - nums[i]];
        }
    }

    return dp[P];
}
