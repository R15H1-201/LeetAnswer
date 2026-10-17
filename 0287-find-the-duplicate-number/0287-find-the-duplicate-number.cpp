class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int n = nums.size() - 1;  // n is the max value
        // Binary search template on value range [1, n]
        int left = 1;
        int right = n;
        int firstTrueIndex = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Count how many numbers are <= mid
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            // Feasible: is there excess?
            if (count > mid) {
                firstTrueIndex = mid;
                right = mid - 1;  // Search for smaller duplicate
            } else {
                left = mid + 1;
            }
        }
        return firstTrueIndex;
    }
};