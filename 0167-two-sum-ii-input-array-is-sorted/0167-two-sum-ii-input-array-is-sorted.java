class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length-1;
 
        while(left <= right){
            int currentSum = numbers[left]+numbers[right];
            if(currentSum == target) return new int[] {++left, ++right};
            if(currentSum<target) left++;
            else right--;
        }
        return null;
    }
}