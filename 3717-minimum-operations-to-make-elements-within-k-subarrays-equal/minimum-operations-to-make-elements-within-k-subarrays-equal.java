
import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> lo = new TreeMap<>();
        TreeMap<Integer, Integer> hi = new TreeMap<>();

        int sizeLo = 0, sizeHi = 0;
        long sumLo = 0, sumHi = 0;

        void addMap(TreeMap<Integer, Integer> map, int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        void removeMap(TreeMap<Integer, Integer> map, int x) {
            int count = map.get(x);
            if (count == 1) map.remove(x);
            else map.put(x, count - 1);
        }

        void add(int x) {
            if (lo.isEmpty() || x <= lo.lastKey()) {
                addMap(lo, x);
                sizeLo++;
                sumLo += x;
            } else {
                addMap(hi, x);
                sizeHi++;
                sumHi += x;
            }
            balance();
        }

        void remove(int x) {
            if (lo.containsKey(x)) {
                removeMap(lo, x);
                sizeLo--;
                sumLo -= x;
            } else {
                removeMap(hi, x);
                sizeHi--;
                sumHi -= x;
            }
            balance();
        }

        void balance() {
            int need = (sizeLo + sizeHi + 1) / 2;

            while (sizeLo > need) {
                int v = lo.lastKey();
                removeMap(lo, v);
                sizeLo--;
                sumLo -= v;

                addMap(hi, v);
                sizeHi++;
                sumHi += v;
            }

            while (sizeLo < need) {
                int v = hi.firstKey();
                removeMap(hi, v);
                sizeHi--;
                sumHi -= v;

                addMap(lo, v);
                sizeLo++;
                sumLo += v;
            }
        }

        long getCost() {
            long median = lo.lastKey();

            return median * sizeLo - sumLo
                 + sumHi - median * sizeHi;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Calculate cost of every window of length x
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < n; i++) {
            window.add(nums[i]);

            if (i >= x) {
                window.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        // DP: minimum cost to select j windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select window ending at i - 1
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
