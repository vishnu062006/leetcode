class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];

        // Count frequency of each number in all length-k subarrays
        for (int i = 0; i <= n - k; i++) {
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                if (!seen[nums[j]]) {
                    freq[nums[j]]++;
                    seen[nums[j]] = true;
                }
            }
        }

        int ans = -1;

        for (int x = 0; x <= 50; x++) {
            if (freq[x] == 1) {
                ans = Math.max(ans, x);
            }
        }

        return ans;
    }
}