class Solution {
    public int distinctSubseqII(String s) {
        // This array keeps track of the number of subsequences ending with each character.
        long[] endCount = new long[26];
        long total = 0;
        int mod = 1_000_000_007;

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            
            // The number of new subsequences we can form by appending the current character 'c'.
            // It equals the total previous subsequences + 1 (for the character 'c' by itself)
            // minus the subsequences that already ended with 'c' (to avoid duplicates).
            long added = (total + 1 - endCount[index] + mod) % mod;
            
            // Update the count of subsequences ending with 'c' and the total count.
            endCount[index] = (endCount[index] + added) % mod;
            total = (total + added) % mod;
        }
        
        return (int) total;
    }
}