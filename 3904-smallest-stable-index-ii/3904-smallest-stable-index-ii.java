class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Precompute the suffix minimums
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        // Calculate the prefix maximum on the fly and check the instability score
        int currentMax = nums[0];
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            if (currentMax - suffMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}