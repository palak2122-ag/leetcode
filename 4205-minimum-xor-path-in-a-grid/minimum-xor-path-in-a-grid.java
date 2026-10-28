class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX = 1024;

        boolean[][] dp = new boolean[MAX][n];

        for (int i = 0; i < m; i++) {
            boolean[][] current = new boolean[MAX][n];

            for (int j = 0; j < n; j++) {
                int x = grid[i][j];

                if (i == 0 && j == 0) {
                    current[x][j] = true;
                    continue;
                }

                for (int v = 0; v < MAX; v++) {
                    boolean reachable = false;

                    if (i > 0 && dp[v][j]) {
                        reachable = true;
                    }

                    if (j > 0 && current[v][j - 1]) {
                        reachable = true;
                    }

                    if (reachable) {
                        current[v ^ x][j] = true;
                    }
                }
            }

            dp = current;
        }

        for (int x = 0; x < MAX; x++) {
            if (dp[x][n - 1]) {
                return x;
            }
        }

        return 0;
    }
}