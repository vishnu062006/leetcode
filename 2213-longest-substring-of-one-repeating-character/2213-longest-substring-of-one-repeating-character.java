class Solution {
    static class Node {
        int len, pre, suf, best;
        char leftChar, rightChar;

        Node(int len, char c) {
            this.len = len;
            this.pre = this.suf = this.best = 1;
            this.leftChar = this.rightChar = c;
        }

        Node() {}
    }

    Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            tree[node] = new Node(1, s.charAt(l));
            return;
        }

        int mid = l + (r - l) / 2;
        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node] = new Node(1, c);
            return;
        }

        int mid = l + (r - l) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, c);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {
        Node res = new Node();

        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.pre = a.pre;
        if (a.pre == a.len && a.rightChar == b.leftChar) {
            res.pre = a.len + b.pre;
        }

        res.suf = b.suf;
        if (b.suf == b.len && a.rightChar == b.leftChar) {
            res.suf = b.len + a.suf;
        }

        res.best = Math.max(a.best, b.best);

        if (a.rightChar == b.leftChar) {
            res.best = Math.max(res.best, a.suf + b.pre);
        }

        return res;
    }
}