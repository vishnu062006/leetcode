class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;
        
        while (n >= threshold) {
            // Add the count of numbers that are large enough to contain a comma at this threshold
            totalCommas += (n - threshold + 1);
            threshold *= 1000;
        }
        
        return totalCommas;
    }
}