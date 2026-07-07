class Solution {
    public long sumAndMultiply(int n) {
        long sum = 0;
        long ans = 0;
        long p = 1;
        while (n > 0) {
            int d = n % 10;
            if (d != 0) {
                sum = sum + d;
                ans = ans + d * p;
                p = p * 10;
            }
            n = n / 10;
        }
        return ans * sum;
    }
}