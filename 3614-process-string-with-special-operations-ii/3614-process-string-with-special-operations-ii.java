class Solution {
    public char processStr(String s, long k) {
        long len = 0;

        // Find final length
        for (char ch : s.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                len++;
            } else if (ch == '*') {
                if (len > 0) len--;
            } else if (ch == '#') {
                len *= 2;
            } else { // '%'
                // length unchanged
            }
        }

        if (k >= len) return '.';

        // Reverse process
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (k == len - 1) {
                    return ch;
                }
                len--;
            } 
            else if (ch == '*') {
                len++;
            } 
            else if (ch == '#') {
                long half = len / 2;
                if (k >= half) {
                    k -= half;
                }
                len = half;
            } 
            else { // '%'
                k = len - k - 1;
            }
        }

        return '.';
    }
}