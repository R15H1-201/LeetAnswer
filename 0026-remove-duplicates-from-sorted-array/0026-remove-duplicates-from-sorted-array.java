class Solution {
    public int removeDuplicates(int[] nums) {
        int uniNo = 0;
        for (int curEle : nums) {
            if (uniNo == 0 || curEle != nums[uniNo - 1]) {
                nums[uniNo] = curEle;
                uniNo++;
            }
        }
        return uniNo;
    }
}