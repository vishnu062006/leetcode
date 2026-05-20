class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;
        int n=s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != ' ') {
                count++;
            } else if (i + 1 < n && s.charAt(i + 1) != ' ') {
                count = 0;
            }
        }
        return count;
    }
}