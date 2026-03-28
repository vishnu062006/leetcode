class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];

        // Step 1: Construct string
        char curr = 'a';

        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (curr > 'z') return "";
                word[i] = curr;

                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = curr;
                    }
                }
                curr++;
            }
        }

        // Step 2: Validate LCP
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = 0;
                }

                if (dp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}