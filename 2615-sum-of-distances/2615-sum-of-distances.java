import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];

        Map<Integer, Long> count = new HashMap<>();
        Map<Integer, Long> sum = new HashMap<>();

        // LEFT PASS
        for (int i = 0; i < n; i++) {
            int val = nums[i];

            if (count.containsKey(val)) {
                res[i] += i * count.get(val) - sum.get(val);
            }

            count.put(val, count.getOrDefault(val, 0L) + 1);
            sum.put(val, sum.getOrDefault(val, 0L) + i);
        }

        // RESET
        count.clear();
        sum.clear();

        // RIGHT PASS
        for (int i = n - 1; i >= 0; i--) {
            int val = nums[i];

            if (count.containsKey(val)) {
                res[i] += sum.get(val) - i * count.get(val);
            }

            count.put(val, count.getOrDefault(val, 0L) + 1);
            sum.put(val, sum.getOrDefault(val, 0L) + i);
        }

        return res;
    }
}