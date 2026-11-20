
import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int takeIncrement = dp.getOrDefault(num, 0) + 1;
            int takeOriginal = dp.getOrDefault(num - 1, 0) + 1;

            dp.put(num + 1, takeIncrement);
            dp.put(num, takeOriginal);

            ans = Math.max(ans, Math.max(takeIncrement, takeOriginal));
        }

        return ans;
    }
}
