#define MAX 60010
int topo(int n, int g[MAX][20], int sz[], int indeg[], int order[]) {
    int q[MAX], front = 0, rear = 0, count = 0;
    for (int i = 0; i < n; i++)
        if (indeg[i] == 0)
            q[rear++] = i;
    while (front < rear) {
        int u = q[front++];
        order[count++] = u;
        for (int i = 0; i < sz[u]; i++) {
            int v = g[u][i];
            if (--indeg[v] == 0)
                q[rear++] = v;
        }
    }
    return count == n;
}

int* sortItems(int n, int m, int* group, int groupSize,
               int** beforeItems, int beforeItemsSize,
               int* beforeItemsColSize, int* returnSize) {

    for (int i = 0; i < n; i++)
        if (group[i] == -1)     group[i] = m++;
    static int itemG[MAX][20], groupG[MAX][20];
    int itemSz[MAX] = {0}, groupSz[MAX] = {0};
    int itemIndeg[MAX] = {0}, groupIndeg[MAX] = {0};
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < beforeItemsColSize[i]; j++) {
            int prev = beforeItems[i][j];
            itemG[prev][itemSz[prev]++] = i;
            itemIndeg[i]++;

            if (group[prev] != group[i]) {
                groupG[group[prev]][groupSz[group[prev]]++] = group[i];
                groupIndeg[group[i]]++;
            }
        }
    }
    int itemOrder[MAX], groupOrder[MAX];
    if (!topo(n, itemG, itemSz, itemIndeg, itemOrder) ||
        !topo(m, groupG, groupSz, groupIndeg, groupOrder)) {
        *returnSize = 0;
        return NULL;
    }
    int grouped[MAX][20], count[MAX] = {0};
    for (int i = 0; i < n; i++) {
        int item = itemOrder[i];
        grouped[group[item]][count[group[item]]++] = item;
    }
    int *res = malloc(sizeof(int) * n); int idx = 0;
    for (int i = 0; i < m; i++) {
        int g = groupOrder[i];
        for (int j = 0; j < count[g]; j++)
            res[idx++] = grouped[g][j];
    }
    *returnSize = idx;
    return res;
}