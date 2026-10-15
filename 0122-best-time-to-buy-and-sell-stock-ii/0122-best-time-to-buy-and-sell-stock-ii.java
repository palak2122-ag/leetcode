class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        //Traverse the prices;
        for(int i=1; i < prices.length;i++){
            //Check if price is more than prev price 
            if(prices[i] > prices[i-1])
            //put in totalProfit
            totalProfit += prices[i] - prices[i - 1];
        }
        return totalProfit;
            }
}