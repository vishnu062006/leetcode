class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans = Math.min(solve(landStartTime, landDuration, waterStartTime, waterDuration), solve(waterStartTime, waterDuration, landStartTime, landDuration));
        return ans;
    }
    private int solve(int[] start1, int[] dur1,
                      int[] start2, int[] dur2) {

        int earliestEnd = Integer.MAX_VALUE;

        for (int i = 0; i < start1.length; i++) {
            earliestEnd = Math.min(earliestEnd, start1[i] + dur1[i]);
        }

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < start2.length; i++) {
            int finish = Math.max(earliestEnd, start2[i]) + dur2[i];
            res = Math.min(res, finish);
        }

        return res;
    }
}