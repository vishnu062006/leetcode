import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (!map.containsKey(num)) {
                map.put(num, new int[]{i, -1}); // store first occurrence
            } else {
                int[] arr = map.get(num);

                if (arr[1] == -1) {
                    // second occurrence
                    arr[1] = i;
                } else {
                    // third or more occurrence
                    int dist = 2 * (i - arr[0]);
                    res = Math.min(res, dist);

                    // shift window
                    arr[0] = arr[1];
                    arr[1] = i;
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}