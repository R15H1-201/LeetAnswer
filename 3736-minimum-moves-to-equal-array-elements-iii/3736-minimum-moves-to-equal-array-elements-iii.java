class Solution {
    public int minMoves(int[] nums) {
        int length = nums.length;
        int maxValue = 0;
        int sum = 0;
        for (int value : nums) {
            maxValue = Math.max(maxValue, value);
            sum += value;
        }
        return maxValue * length - sum;
    }
}