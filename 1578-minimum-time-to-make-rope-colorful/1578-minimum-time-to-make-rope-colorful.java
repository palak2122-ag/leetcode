class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalTime = 0;
       int maxNeededTime = neededTime[0];

       for(int i=1;i<colors.length();i++){
        if(colors.charAt(i) == colors.charAt(i-1)){
            totalTime += Math.min(maxNeededTime, neededTime[i]);
            maxNeededTime = Math.max(maxNeededTime, neededTime[i]);
        }else{
            maxNeededTime = neededTime[i];
        }
       } 
       return totalTime;
    }
}