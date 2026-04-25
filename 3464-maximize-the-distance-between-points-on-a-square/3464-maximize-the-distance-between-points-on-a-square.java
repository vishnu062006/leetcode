import java.util.*;

class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];

            if (x == 0) arr[i] = y;
            else if (y == side) arr[i] = side + x;
            else if (x == side) arr[i] = 3L * side - y;
            else arr[i] = 4L * side - x;
        }

        Arrays.sort(arr);

        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = arr[i];
            ext[i + n] = arr[i] + 4L * side;
        }

        int lo = 1, hi = side, ans = 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (can(ext, n, k, mid, side)) {
                ans = mid;
                lo = mid + 1;
            } else hi = mid - 1;
        }

        return ans;
    }

    private boolean can(long[] arr, int n, int k, int d, int side) {

        for (int start = 0; start < n; start++) {
            int cnt = 1;
            long last = arr[start];
            int pos = start;

            while (cnt < k) {
                // binary search next >= last + d
                int next = lowerBound(arr, pos + 1, start + n, last + d);
                if (next == start + n) break;

                last = arr[next];
                pos = next;
                cnt++;
            }

            if (cnt == k && last - arr[start] <= 4L * side - d) {
                return true;
            }
        }

        return false;
    }

    private int lowerBound(long[] arr, int l, int r, long target) {
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] >= target) r = m;
            else l = m + 1;
        }
        return l;
    }
}