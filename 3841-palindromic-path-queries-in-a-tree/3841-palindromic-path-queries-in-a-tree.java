import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] parent = new int[n];
        int[] order = new int[n];

        Arrays.fill(parent, -1);

        // Iterative DFS to construct Euler tour.
        int[] stack = new int[n];
        int[] iter = new int[n];
        int top = 0, timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[top - 1];

            if (iter[u] == 0) {
                tin[u] = timer;
                order[timer++] = u;
            }

            if (iter[u] < graph[u].size()) {
                int v = graph[u].get(iter[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        // Fenwick tree for range XOR updates and point queries.
        Fenwick bit = new Fenwick(n + 1);
        int[] charMask = new int[n];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
            bit.rangeXor(tin[i] + 1, tout[i] + 1, charMask[i]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                int newMask = 1 << (p[2].charAt(0) - 'a');

                int delta = charMask[u] ^ newMask;

                if (delta != 0) {
                    bit.rangeXor(tin[u] + 1, tout[u] + 1, delta);
                    charMask[u] = newMask;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int w = lca(u, v, depth, up);

                int maskU = bit.query(tin[u] + 1);
                int maskV = bit.query(tin[v] + 1);

                int mask = maskU ^ maskV ^ charMask[w];

                ans.add(mask == 0 || (mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void add(int i, int val) {
            while (i <= n) {
                bit[i] ^= val;
                i += i & -i;
            }
        }

        void rangeXor(int l, int r, int val) {
            add(l, val);
            if (r + 1 <= n) add(r + 1, val);
        }
        int query(int i) {
            int res = 0;
            while (i > 0) {
                res ^= bit[i];
                i -= i & -i;
            }
            return res;
        }
    }
}