
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long g = 0;
            long l = 1;

            for (int j = i; j < n; j++) {
                product *= nums[j];
                g = gcd(g, nums[j]);
                l = (l / gcd(l, nums[j])) * nums[j];

                if (product == g * l) {
                    ans = Math.max(ans, j - i + 1);
                }

                if (product > 25200) {
                    break;
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
