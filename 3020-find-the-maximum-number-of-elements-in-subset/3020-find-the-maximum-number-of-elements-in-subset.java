import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> cnt = new HashMap<>();

        for (int x : nums)
            cnt.put((long) x, cnt.getOrDefault((long) x, 0) + 1);

        int ans = 1;

        if (cnt.containsKey(1L)) {
            int ones = cnt.get(1L);
            ans = Math.max(ans, ones % 2 == 0 ? ones - 1 : ones);
        }

        for (long start : cnt.keySet()) {
            if (start == 1) continue;

            long x = start;
            int cur = 0;

            while (cnt.getOrDefault(x, 0) >= 2) {
                cur += 2;

                long nxt = x * x;
                if (nxt > 1_000_000_000L) {
                    x = nxt;
                    break;
                }
                x = nxt;
            }

            if (cnt.containsKey(x))
                ans = Math.max(ans, cur + 1);
            else
                ans = Math.max(ans, cur - 1);
        }

        return ans;
    }
}