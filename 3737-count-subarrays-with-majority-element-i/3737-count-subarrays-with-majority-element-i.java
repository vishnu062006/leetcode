class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int cntTarget = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    cntTarget++;
                }

                int len = j - i + 1;

                if (2 * cntTarget > len) {
                    ans++;
                }
            }
        }

        return ans;
    }
}