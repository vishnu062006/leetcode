class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        
        int minIdx = 0;
        int maxIdx = 0;
        
        // Find the indices of the minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        
        // Ensure i is the smaller index and j is the larger index
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        
        // Scenario 1: Delete both from the front
        int deleteFront = j + 1;
        
        // Scenario 2: Delete both from the back
        int deleteBack = n - i;
        
        // Scenario 3: Delete the first one from the front, and the second from the back
        int deleteBothEnds = (i + 1) + (n - j);
        
        // Return the minimum of the three scenarios
        return Math.min(deleteFront, Math.min(deleteBack, deleteBothEnds));
    }
}