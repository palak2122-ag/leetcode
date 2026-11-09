
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        int[] count = new int[k];

        // Initially all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            // Release servers whose work is completed
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            // If no server is available, skip request
            if (available.isEmpty()) {
                continue;
            }

            // Find next available server
            int server = i % k;

            Integer assigned = available.ceiling(server);

            if (assigned == null) {
                assigned = available.first();
            }

            // Assign request
            available.remove(assigned);
            count[assigned]++;

            // Mark server as busy
            busy.offer(new int[]{
                arrival[i] + load[i], assigned
            });
        }

        // Find maximum requests handled
        int max = 0;

        for (int x : count) {
            max = Math.max(max, x);
        }

        // Store all busiest servers
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
