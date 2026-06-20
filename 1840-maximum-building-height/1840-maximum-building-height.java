import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(new int[]{r[0], r[1]});
        }

        list.add(new int[]{n, n - 1});

        list.sort((a, b) -> Integer.compare(a[0], b[0]));

        int m = list.size();

        // Left to right pass
        for (int i = 1; i < m; i++) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(
                list.get(i)[1],
                list.get(i - 1)[1] + dist
            );
        }

        // Right to left pass
        for (int i = m - 2; i >= 0; i--) {
            int dist = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(
                list.get(i)[1],
                list.get(i + 1)[1] + dist
            );
        }

        long ans = 0;

        for (int i = 1; i < m; i++) {
            long x = list.get(i - 1)[1];
            long y = list.get(i)[1];
            long d = list.get(i)[0] - list.get(i - 1)[0];

            ans = Math.max(ans, (x + y + d) / 2);
        }

        return (int) ans;
    }
}