
class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1000000007;

        long even = 1;
        long odd = 0;
        long ans = 0;

        int sum = 0;

        for (int num : arr) {
            sum = (sum + num) % 2;

            if (sum == 0) {
                ans = (ans + odd) % MOD;
                even++;
            } else {
                ans = (ans + even) % MOD;
                odd++;
            }
        }

        return (int) ans;
    }
}
