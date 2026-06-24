class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        // Base vector for length = 2
        // first m -> last move was "down"
        // next  m -> last move was "up"
        long[] base = new long[size];

        // For every pair (a, b), a != b:
        // if a > b => down state at b
        // if a < b => up state at b
        for (int prev = 0; prev < m; prev++) {
            for (int cur = 0; cur < m; cur++) {
                if (prev == cur) continue;
                if (prev > cur) {
                    base[cur]++;          // down block
                } else {
                    base[m + cur]++;      // up block
                }
            }
        }

        // If n == 2, just sum base
        if (n == 2) {
            long ans = 0;
            for (long x : base) ans = (ans + x) % MOD;
            return (int) ans;
        }

        // Build transition matrix T of size 2m x 2m
        // State:
        // [down[0..m-1], up[0..m-1]]
        //
        // From down[x], next must go to up[y] where y > x
        // From up[x], next must go to down[y] where y < x
        long[][] T = new long[size][size];

        for (int x = 0; x < m; x++) {
            // down[x] -> up[y] for y > x
            for (int y = x + 1; y < m; y++) {
                T[m + y][x] = 1;
            }

            // up[x] -> down[y] for y < x
            for (int y = 0; y < x; y++) {
                T[y][m + x] = 1;
            }
        }

        // We already have length=2 states in base
        // Need to reach length=n => apply T^(n-2)
        long[][] Tp = matPow(T, n - 2);
        long[] res = multiply(Tp, base);

        long ans = 0;
        for (long x : res) ans = (ans + x) % MOD;
        return (int) ans;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0 || vec[j] == 0) continue;
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }
            res[i] = sum;
        }
        return res;
    }

    private long[][] matPow(long[][] mat, int exp) {
        int n = mat.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, mat);
            mat = multiply(mat, mat);
            exp >>= 1;
        }
        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                long val = a[i][k];
                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;
                    res[i][j] = (res[i][j] + val * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }
}