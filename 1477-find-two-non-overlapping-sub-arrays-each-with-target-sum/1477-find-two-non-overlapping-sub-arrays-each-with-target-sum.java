class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int left = 0, sum = 0, currentMin = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        // Step 1: Compute the minimum length of a valid sub-array ending at or before index i
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                int len = right - left + 1;
                // If there is a valid sub-array ending before 'left', we can combine them
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + minLen[left - 1]);
                }
                currentMin = Math.min(currentMin, len);
            }
            minLen[right] = currentMin;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}