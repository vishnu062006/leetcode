#define MOD 1000000007
#define MAXN 100005

typedef long long ll;

ll seq[MAXN];
int sz;
ll M, A; // global multiplier and adder

ll power(ll base, ll exp, ll mod) {
    ll result = 1;
    base %= mod;
    while(exp > 0) {
        if(exp & 1) result = result * base % mod;
        base = base * base % mod;
        exp >>= 1;
    }
    return result;
}

ll modInverse(ll x) {
    return power(x, MOD - 2, MOD);
}

typedef struct {
    int unused; // LC needs a struct
} Fancy;

Fancy* fancyCreate() {
    Fancy* f = (Fancy*)malloc(sizeof(Fancy));
    sz = 0;
    M = 1;
    A = 0;
    return f;
}

void fancyAppend(Fancy* obj, int val) {
    // store normalized: (val - A) * M^-1
    seq[sz++] = (val - A + MOD) % MOD * modInverse(M) % MOD;
}

void fancyAddAll(Fancy* obj, int inc) {
    A = (A + inc) % MOD;
}

void fancyMultAll(Fancy* obj, int m) {
    M = M * m % MOD;
    A = A * m % MOD;
}

int fancyGetIndex(Fancy* obj, int idx) {
    if(idx >= sz) return -1;
    return (seq[idx] * M % MOD + A) % MOD;
}

void fancyFree(Fancy* obj) {
    free(obj);
}