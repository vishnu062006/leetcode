class Solution {
    public int findGCD(int[] nums) {
        int r1 = nums[0];
        int r2 = nums[0];

        for (int i = 1; i < nums.length; i++) {
            r1 = Math.min(r1, nums[i]);
            r2 = Math.max(r2, nums[i]);
        }

        while (r2 != 0) {
            int temp = r2;
            r2 = r1 % r2;
            r1 = temp;
        }

        return r1;
    }
}