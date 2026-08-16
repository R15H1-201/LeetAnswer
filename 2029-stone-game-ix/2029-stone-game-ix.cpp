class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> count(3, 0);
        for (int stone : stones) {
            count[stone % 3]++;
        }
        vector<int> countSwapped = {count[0], count[2], count[1]};
        auto checkAliceWins = [](vector<int> stoneCount) -> bool {
            stoneCount[1]--;
            if (stoneCount[1] < 0) {
                return false;
            }
            int totalMoves = 1 + min(stoneCount[1], stoneCount[2]) * 2 + stoneCount[0];
            if (stoneCount[1] > stoneCount[2]) {
                stoneCount[1]--;
                totalMoves++;
            }
            return (totalMoves % 2 == 1) && (stoneCount[1] != stoneCount[2]);
        };
        return checkAliceWins(count) || checkAliceWins(countSwapped);
    }
};