class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[][] count = new int[2][26]; 
        
        for (int i = 0; i < s1.length(); i++) {
            int idx = i % 2;
            count[idx][s1.charAt(i) - 'a']++;
            count[idx][s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26; j++) {
                if (count[i][j] != 0) 
                    return false;
            }
        }
        return true;
    }
}