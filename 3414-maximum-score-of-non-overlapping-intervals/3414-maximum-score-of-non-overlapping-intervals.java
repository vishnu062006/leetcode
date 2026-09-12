import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] ivs = new int[n][4];
        
        // Store the intervals along with their original indices
        for (int i = 0; i < n; i++) {
            ivs[i][0] = intervals.get(i).get(0); // left
            ivs[i][1] = intervals.get(i).get(1); // right
            ivs[i][2] = intervals.get(i).get(2); // weight
            ivs[i][3] = i;                       // original index
        }
        
        // Sort by left boundary
        Arrays.sort(ivs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // dpWeight[i][k] stores the max weight using EXACTLY k intervals from suffix i..n-1
        long[][] dpWeight = new long[n + 1][5];
        // dpSeq[i][k] stores the sequence of original indices matching dpWeight[i][k]
        int[][][] dpSeq = new int[n + 1][5][];
        
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dpWeight[i], -1); // -1 signifies impossible state
            dpWeight[i][0] = 0;
            dpSeq[i][0] = new int[0];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            // Find the next valid non-overlapping interval
            int nextJ = n;
            int low = i + 1, high = n - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (ivs[mid][0] > ivs[i][1]) {
                    nextJ = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip the current interval
                long w1 = dpWeight[i + 1][k];
                int[] seq1 = dpSeq[i + 1][k];
                
                // Option 2: Take the current interval
                long w2 = -1;
                int[] seq2 = null;
                if (dpWeight[nextJ][k - 1] != -1) {
                    w2 = ivs[i][2] + dpWeight[nextJ][k - 1];
                    seq2 = new int[k];
                    int[] prevSeq = dpSeq[nextJ][k - 1];
                    for (int m = 0; m < k - 1; m++) {
                        seq2[m] = prevSeq[m];
                    }
                    seq2[k - 1] = ivs[i][3];
                    Arrays.sort(seq2); // Sort indices array to compare lexicographically
                }
                
                if (w1 == -1 && w2 == -1) {
                    continue;
                }
                
                // Compare Option 1 and Option 2
                if (w1 > w2) {
                    dpWeight[i][k] = w1;
                    dpSeq[i][k] = seq1;
                } else if (w2 > w1) {
                    dpWeight[i][k] = w2;
                    dpSeq[i][k] = seq2;
                } else {
                    // Weight tie-breaker (Compare lexicographically)
                    boolean pick1 = true;
                    for (int m = 0; m < k; m++) {
                        if (seq1[m] != seq2[m]) {
                            pick1 = seq1[m] < seq2[m];
                            break;
                        }
                    }
                    if (pick1) {
                        dpWeight[i][k] = w1;
                        dpSeq[i][k] = seq1;
                    } else {
                        dpWeight[i][k] = w2;
                        dpSeq[i][k] = seq2;
                    }
                }
            }
        }
        
        long maxW = -1;
        int[] bestSeq = null;
        
        // Find the absolute best sequence tracking lengths 1 to 4
        for (int k = 1; k <= 4; k++) {
            if (dpWeight[0][k] > maxW) {
                maxW = dpWeight[0][k];
                bestSeq = dpSeq[0][k];
            } else if (dpWeight[0][k] == maxW && maxW != -1) {
                int[] seq1 = bestSeq;
                int[] seq2 = dpSeq[0][k];
                int len = Math.min(seq1.length, seq2.length);
                
                boolean pick1 = true;
                boolean same = true;
                for (int m = 0; m < len; m++) {
                    if (seq1[m] != seq2[m]) {
                        pick1 = seq1[m] < seq2[m];
                        same = false;
                        break;
                    }
                }
                if (same) {
                    pick1 = seq1.length < seq2.length; // If a sequence is fully identical but shorter
                }
                if (!pick1) {
                    bestSeq = seq2;
                }
            }
        }
        
        return bestSeq != null ? bestSeq : new int[0];
    }
}