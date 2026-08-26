class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String best = "";
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < s.length(); i++) {
            int onesCount = 0;
            
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    onesCount++;
                }
                
                // Once we hit exactly k ones, we have our shortest valid substring starting at i
                if (onesCount == k) {
                    String current = s.substring(i, j + 1);
                    
                    if (current.length() < minLen) {
                        minLen = current.length();
                        best = current;
                    } else if (current.length() == minLen) {
                        // If lengths are equal, pick the lexicographically smaller one
                        if (current.compareTo(best) < 0) {
                            best = current;
                        }
                    }
                    // Break early because extending the substring will only increase its length
                    break; 
                }
            }
        }
        
        return best;
    }
}