class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        long sum = 0;
        long f0 = 0;

        // Calculate total sum and F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long) i * nums[i];
        }

        long max = f0;
        long curr = f0;

        // Use recurrence to compute next values
        for (int k = 1; k < n; k++) {
            curr = curr + sum - (long) n * nums[n - k];
            max = Math.max(max, curr);
        }

        return (int) max;
    }
}