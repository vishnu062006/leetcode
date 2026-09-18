import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Find valid substring intervals
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int left = first[i];
            int right = last[i];
            boolean isValid = true;

            // Expand the interval to include all required characters
            for (int j = left; j <= right; j++) {
                int c = s.charAt(j) - 'a';
                if (first[c] < left) {
                    // If a character's first occurrence is outside our current left bound,
                    // it means this interval is invalid (overlaps partially).
                    isValid = false;
                    break;
                }
                right = Math.max(right, last[c]);
            }

            if (isValid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Step 3: Greedily select non-overlapping intervals with minimum total length
        // Sort intervals by their ending index
        intervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}