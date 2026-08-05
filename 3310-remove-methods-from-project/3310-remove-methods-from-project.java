class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : invocations) {
            graph[e[0]].add(e[1]);
        }

        boolean[] suspicious = new boolean[n];

        // DFS to mark all suspicious methods
        dfs(k, graph, suspicious);

        // If any non-suspicious method calls a suspicious one,
        // we cannot remove any methods.
        for (int[] e : invocations) {
            int u = e[0], v = e[1];
            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) ans.add(i);
                return ans;
            }
        }

        // Return remaining methods
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) ans.add(i);
        }
        return ans;
    }

    private void dfs(int u, List<Integer>[] graph, boolean[] suspicious) {
        if (suspicious[u]) return;
        suspicious[u] = true;
        for (int v : graph[u]) {
            dfs(v, graph, suspicious);
        }
    }
}