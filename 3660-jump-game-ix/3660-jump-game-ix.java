class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];

        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int start = 0;

        for (int i = 0; i < n - 1; i++) {
            if (prefixMax[i] <= suffixMin[i + 1]) {
                int mx = prefixMax[i];
                for (int j = start; j <= i; j++) {
                    ans[j] = mx;
                }
                start = i + 1;
            }
        }

        int mx = prefixMax[n - 1];
        for (int j = start; j < n; j++) {
            ans[j] = mx;
        }

        return ans;
    }
}