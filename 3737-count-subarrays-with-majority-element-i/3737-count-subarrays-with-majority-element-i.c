int countMajoritySubarrays(int* nums, int numsSize, int target) {
    int ans = 0;

    for (int i = 0; i < numsSize; i++) {
        int cntTarget = 0;

        for (int j = i; j < numsSize; j++) {
            if (nums[j] == target)
                cntTarget++;

            int len = j - i + 1;

            if (2 * cntTarget > len)
                ans++;
        }
    }

    return ans;
}