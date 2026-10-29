import java.util.*;

class Solution {
    int timer, LOG;
    int[] tin, tout, depth, bit, value;
    int[][] up;
    List<Integer>[] adj;

    void dfs(int u, int p) {
        tin[u] = ++timer;
        up[0][u] = p;

        for (int v : adj[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }

        tout[u] = timer;
    }

    void update(int i, int mask) {
        while (i < bit.length) {
            bit[i] ^= mask;
            i += i & -i;
        }
    }

    int prefix(int i) {
        int ans = 0;
        while (i > 0) {
            ans ^= bit[i];
            i -= i & -i;
        }
        return ans;
    }

    boolean ancestor(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    int lca(int u, int v) {
        if (ancestor(u, v)) return u;
        if (ancestor(v, u)) return v;

        for (int k = LOG - 1; k >= 0; k--) {
            if (!ancestor(up[k][u], v)) {
                u = up[k][u];
            }
        }

        return up[0][u];
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        timer = 0;
        dfs(0, 0);

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        bit = new int[n + 2];
        value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = 1 << (s.charAt(i) - 'a');
            update(tin[i], value[i]);
            update(tout[i] + 1, value[i]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int newValue = 1 << (c - 'a');
                int diff = value[u] ^ newValue;

                if (diff != 0) {
                    update(tin[u], diff);
                    update(tout[u] + 1, diff);
                    value[u] = newValue;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int w = lca(u, v);

                int mask = prefix(tin[u])
                         ^ prefix(tin[v])
                         ^ value[w];

                ans.add(Integer.bitCount(mask) <= 1);
            }
        }

        return ans;
    }
}