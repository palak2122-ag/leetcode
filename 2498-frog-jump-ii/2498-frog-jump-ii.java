class Solution {
    public int maxJump(int[] stones) {
        int result = stones[1] - stones[0];
        //Traverse
        for(int i=2;i<stones.length;i++){
           result = Math.max(result,stones[i]-stones[i-2]);
        }
        return result;
    }
}