int parent[100005];
int rnk[100005];

int find(int x) {
    if (parent[x] != x)
        parent[x] = find(parent[x]);
    return parent[x];
}

int unite(int x, int y) {
    int px = find(x), py = find(y);
    if (px == py) return 0;
    if (rnk[px] < rnk[py]) { int t = px; px = py; py = t; }
    parent[py] = px;
    if (rnk[px] == rnk[py]) rnk[px]++;
    return 1;
}

int maxStability(int n, int** edges, int edgesSize, int* edgesColSize, int k)  {
    int maxS = 0;
    for (int i = 0; i < edgesSize; i++)
        if (edges[i][2] > maxS) maxS = edges[i][2];

    int lo = 1, hi = maxS * 2, ans = -1;

    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;

        // reset union find
        for (int i = 0; i < n; i++) { parent[i] = i; rnk[i] = 0; }

        int edgesUsed = 0, upgradesUsed = 0;

        // Step 1: must edges
        int valid = 1;
        for (int i = 0; i < edgesSize; i++) {
            if (edges[i][3] == 1) {
                if (edges[i][2] < mid) { valid = 0; break; }
                if (unite(edges[i][0], edges[i][1])) edgesUsed++;
                else { valid = 0; break; }
            }
        }

        // Step 2: optional, no upgrade
        if (valid) {
            for (int i = 0; i < edgesSize; i++)
                if (edges[i][3] == 0 && edges[i][2] >= mid)
                    if (unite(edges[i][0], edges[i][1])) edgesUsed++;
        }

        // Step 3: optional, needs upgrade
        if (valid) {
            for (int i = 0; i < edgesSize; i++)
                if (edges[i][3] == 0 && edges[i][2] < mid && edges[i][2] * 2 >= mid)
                    if (upgradesUsed < k && unite(edges[i][0], edges[i][1]))
                        { edgesUsed++; upgradesUsed++; }
        }

        if (valid && edgesUsed == n - 1) { ans = mid; lo = mid + 1; }
        else hi = mid - 1;
    }

    return ans;
}