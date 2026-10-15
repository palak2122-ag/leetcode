class Solution {
    public int[] maxValue(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int[] maxJump = new int[len];

        maxJump[0] = nums[0];

        for (int i = 1; i < len; i++) {
            maxJump[i] = Math.max(nums[i], maxJump[i - 1]);
        }

        int minSuff = Integer.MAX_VALUE;

        for (int i = len - 1; i >= 0; i--) {
            if (maxJump[i] > minSuff) {
                result[i] = result[i + 1];
            } else {
                result[i] = maxJump[i];
            }

            minSuff = Math.min(minSuff, nums[i]);
        }

        return result;
    }
}