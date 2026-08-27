class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        if (dfs(count, target, 0, sb)) {
            return sb.toString();
        }
        
        return ""; // No valid permutation exists
    }

    private boolean dfs(int[] count, String target, int i, StringBuilder sb) {
        // If we reach the end by only matching characters exactly, it's equal to target 
        // (not strictly greater), so this path is invalid.
        if (i == target.length()) {
            return false;
        }

        int required = target.charAt(i) - 'a';

        // 1. Try to place the exact character to match the target prefix
        if (count[required] > 0) {
            count[required]--;
            sb.append((char) (required + 'a'));
            
            if (dfs(count, target, i + 1, sb)) {
                return true; // Found a valid path ahead
            }
            
            // Backtrack if matching exactly leads to a dead end
            sb.deleteCharAt(sb.length() - 1);
            count[required]++;
        }

        // 2. Try placing the smallest available character strictly greater than target[i]
        for (int c = required + 1; c < 26; c++) {
            if (count[c] > 0) {
                count[c]--;
                sb.append((char) (c + 'a'));
                
                // Since we placed a strictly greater character, just append the 
                // remaining characters in sorted (lexicographically smallest) order
                for (int k = 0; k < 26; k++) {
                    while (count[k] > 0) {
                        sb.append((char) (k + 'a'));
                        count[k]--;
                    }
                }
                return true; 
            }
        }

        return false;
    }
}