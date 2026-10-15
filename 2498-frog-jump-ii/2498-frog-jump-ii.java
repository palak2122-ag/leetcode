class Solution {
    public int maxJump(int[] stones) {
        int result = stones[1] - stones[0];
        //Traverse
        for(int i=2;i<stones.length;i++){
            int distance = stones[i]-stones[i-2];
           result = Math.max(result,distance);
        }
        return result;
    }
}