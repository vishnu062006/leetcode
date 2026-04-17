import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            // check if this value was expected before
            if (map.containsKey(curr)) {
                ans = Math.min(ans, i - map.get(curr));
            }

            // store reverse for future matches
            int rev = reverse(curr);
            map.put(rev, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return rev;
    }
}