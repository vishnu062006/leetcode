class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] word = new char[n + m - 1];
        boolean[] fixed = new boolean[n + m - 1];
        for (int i = 0; i < word.length; i++) {
            word[i] = 'a';
        }
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int idx = i + j;
                    if (fixed[idx] && word[idx] != str2.charAt(j)) {
                        return "";
                    }
                    word[idx] = str2.charAt(j);
                    fixed[idx] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean same = true;
                int changeIdx = -1;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        same = false;
                        break;
                    }
                    if (!fixed[i + j]) {
                        changeIdx = i + j;
                    }
                }
                
                if (same) {
                    if (changeIdx == -1) {
                        return "";
                    }
                    word[changeIdx] = 'b';
                }
            }
        }
        
        return new String(word);
    }
}