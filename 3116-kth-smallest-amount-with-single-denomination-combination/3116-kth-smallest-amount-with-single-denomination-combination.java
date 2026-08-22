class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1, high = 25L * k; // Max possible value bound
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countValidAmounts(coins, mid) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Counts how many distinct multiples can be formed up to 'maxVal' using any combination of coins
    private long countValidAmounts(int[] coins, long maxVal) {
        long count = 0;
        int n = coins.length;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long lcmVal = 1;
            int setBits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setBits++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    if (lcmVal > maxVal) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                if (setBits % 2 == 1) {
                    count += maxVal / lcmVal;
                } else {
                    count -= maxVal / lcmVal;
                }
            }
        }
        return count;
    }

    // Helper method to compute Greatest Common Divisor (GCD)
    private long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Helper method to compute Least Common Multiple (LCM) with overflow check
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}