class Solution {

    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int minimumDistance(String word) {
        int n = word.length();

        // dp[i][j] = max saving when second finger is at j
        int[] dp = new int[26];

        for (int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';

            int[] newDp = new int[26];

            for (int j = 0; j < 26; j++) {
                // case 1: same finger types next
                newDp[j] = Math.max(newDp[j], dp[j]);

                // case 2: use second finger
                int gain = dist(curr, next);
                newDp[curr] = Math.max(newDp[curr],
                        dp[j] + gain - dist(j, next));
            }

            dp = newDp;
        }

        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            total += dist(word.charAt(i) - 'A', word.charAt(i + 1) - 'A');
        }

        int maxSave = 0;
        for (int x : dp) maxSave = Math.max(maxSave, x);

        return total - maxSave;
    }
}