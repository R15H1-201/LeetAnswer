class Solution {
public:

    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        int n = items.size();
        vector<int> frees(n, 0);
        for(int i=0 ; i<n ; ++i){
            for(int j=0 ; j<n ; ++j){
                int fac_i = items[i][0], fac_j = items[j][0];
                if((i!=j) && ((fac_j % fac_i) == 0))
                    ++frees[i];
            }
        }
          
        vector<int> dp(budget+1, 0);
        for(int i=0 ; i<n ; ++i){
            int cost = items[i][1];
            int free = frees[i];
            for(int i=budget ; i>=cost ; --i){
                dp[i] = max(dp[i], dp[i-cost] + 1 + free);
            }
            for(int i=cost ; i<=budget ; ++i){
                dp[i] = max(dp[i], dp[i-cost] + 1);
            }

        }
        return dp[budget];
    }
};