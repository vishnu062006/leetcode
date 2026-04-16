import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        
        // Step 1: Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        List<Integer> res = new ArrayList<>();
        
        // Step 2: Process each query
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);
            
            // Only one occurrence
            if (list.size() == 1) {
                res.add(-1);
                continue;
            }
            
            // Binary search to find position
            int idx = Collections.binarySearch(list, q);
            
            int size = list.size();
            
            // Previous index (circular)
            int prev = list.get((idx - 1 + size) % size);
            
            // Next index (circular)
            int next = list.get((idx + 1) % size);
            
            // Compute circular distance
            int d1 = Math.min(Math.abs(q - prev), n - Math.abs(q - prev));
            int d2 = Math.min(Math.abs(q - next), n - Math.abs(q - next));
            
            res.add(Math.min(d1, d2));
        }
        
        return res;
    }
}