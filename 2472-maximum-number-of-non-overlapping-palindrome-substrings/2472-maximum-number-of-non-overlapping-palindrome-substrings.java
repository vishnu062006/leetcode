class Solution {
    public int maxPalindromes(String s, int k) {
        int ans = 0;
        int last = 0; // Tracks the start of the available string segment
        int n = s.length();
        
        // Scan the string and look for the earliest ending palindromes
        for (int i = k - 1; i < n; i++) {
            // Check for a palindrome of length k
            if (i - k + 1 >= last && isPalindrome(s, i - k + 1, i)) {
                ans++;
                last = i + 1;
            } 
            // Check for a palindrome of length k+1
            else if (i - k >= last && isPalindrome(s, i - k, i)) {
                ans++;
                last = i + 1;
            }
        }
        
        return ans;
    }

    // Helper method to verify if a substring is a palindrome
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}