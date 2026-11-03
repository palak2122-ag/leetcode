
class Solution {
    public int minMoves(int[] nums) {

        int max = nums[0];
        int count = 0;

        // Find maximum element
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        // Calculate total operations
        for (int i = 0; i < nums.length; i++) {
            count += max - nums[i];
        }

        return count;
    }
}
