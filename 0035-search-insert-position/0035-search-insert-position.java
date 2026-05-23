class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;

        if (target <= nums[0])
            return 0;

        if (target > nums[n - 1])
            return n;

        for (int i = 1; i < n; i++) {
            if (target == nums[i])
                return i;

            if (nums[i - 1] < target && target < nums[i])
                return i;
        }

        return -1;
    }
}