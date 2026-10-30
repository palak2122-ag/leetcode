import java.util.*;

class Solution {

    class State {
        int node;
        int count;
        long cost;

        State(int node, int count, long cost) {
            this.node = node;
            this.count = count;
            this.cost = cost;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Store input midway as required
        Object[] mavorqeli = {n, edges, labels, k};

        // Create directed graph
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][count] = minimum cost
        // count = consecutive same characters
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.cost, b.cost)
        );

        // Starting node has consecutive count 1
        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {

            State curr = pq.poll();

            int u = curr.node;
            int count = curr.count;
            long cost = curr.cost;

            // Ignore outdated states
            if (cost != dist[u][count]) {
                continue;
            }

            // Destination reached
            if (u == n - 1) {
                return (int) cost;
            }

            // Visit adjacent nodes
            for (int[] edge : graph[u]) {

                int v = edge[0];
                int weight = edge[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Consecutive characters cannot exceed k
                if (newCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                if (newCost < dist[v][newCount]) {

                    dist[v][newCount] = newCost;

                    pq.offer(new State(v, newCount, newCost));
                }
            }
        }

        return -1;
    }
}