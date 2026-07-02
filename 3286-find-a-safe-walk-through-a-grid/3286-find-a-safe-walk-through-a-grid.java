class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Deque<int[]> dq = new ArrayDeque<>();

        dist[0][0] = grid.get(0).get(0);
        dq.offerFirst(new int[]{0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0], c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                int w = grid.get(nr).get(nc);
                if (dist[r][c] + w < dist[nr][nc]) {
                    dist[nr][nc] = dist[r][c] + w;
                    if (w == 0)
                        dq.offerFirst(new int[]{nr, nc});
                    else
                        dq.offerLast(new int[]{nr, nc});
                }
            }
        }

        return dist[m - 1][n - 1] < health;
    }
}