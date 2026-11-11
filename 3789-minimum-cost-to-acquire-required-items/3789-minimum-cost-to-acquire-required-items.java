
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long common = Math.min(need1, need2);

        long ans = common * Math.min(costBoth, cost1 + (long)cost2);

        ans += (need1 - common) * Math.min(cost1, costBoth);

        ans += (need2 - common) * Math.min(cost2, costBoth);

        return ans;
    }
}
