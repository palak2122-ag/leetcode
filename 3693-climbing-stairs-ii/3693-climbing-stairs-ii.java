class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] arr = new int[n+1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0]=0;
        for(int i = 1;i <= n;++i){
            int currCost = costs[i-1];
            for(int j = Math.max(0, i-3);j < i;++j){
                arr[i] = Math.min(arr[i], arr[j] + currCost + (i-j)*(i-j));
            }
        }
        return arr[n];
    }
}