class Solution {
public:
    int sumCounts(vector<int>& nums) {
        int totalSum = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int frequency[101] = {0};
            int distinctCount = 0;
            for (int j = i; j < n; ++j) {
                if (++frequency[nums[j]] == 1) {
                    ++distinctCount;
                }
                totalSum += distinctCount * distinctCount;
            }
        }    
        return totalSum;
    }
};