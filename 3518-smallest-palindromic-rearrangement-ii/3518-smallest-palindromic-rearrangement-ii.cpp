class Solution {
public:
    static constexpr int LIM = 1000001;

    vector<double> logFact;

    void buildLogFact(int n) {
        logFact.assign(n + 1, 0.0);
        for (int i = 1; i <= n; i++)
            logFact[i] = logFact[i - 1] + log((double)i);
    }

    int countWays(vector<int>& freq) {
        int tot = 0;
        for (int x : freq) tot += x;

        double lg = logFact[tot];
        for (int x : freq) lg -= logFact[x];

        // definitely exceeds LIMIT
        if (lg > log((double)LIM))
            return LIM;

        long long ans = 1;
        int rem = tot;

        for (int f : freq) {
            if (f == 0) continue;

            for (int i = 1; i <= f; i++) {
                ans = ans * (rem - f + i) / i;
                if (ans >= LIM) return LIM;
            }
            rem -= f;
        }

        return (int)ans;
    }

    string smallestPalindrome(string s, int k) {
        vector<int> cnt(26, 0);

        for (char c : s)
            cnt[c - 'a']++;

        vector<int> halfCnt(26);
        char mid = 0;

        int m = 0;
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
            m += halfCnt[i];
            if (cnt[i] & 1)
                mid = char('a' + i);
        }

        buildLogFact(m);

        if (countWays(halfCnt) < k)
            return "";

        string half;

        for (int pos = 0; pos < m; pos++) {

            for (int c = 0; c < 26; c++) {

                if (halfCnt[c] == 0) continue;

                halfCnt[c]--;

                int ways = countWays(halfCnt);

                if (ways >= k) {
                    half.push_back(char('a' + c));
                    break;
                }

                k -= ways;
                halfCnt[c]++;
            }
        }

        string ans = half;

        if (mid)
            ans.push_back(mid);

        reverse(half.begin(), half.end());
        ans += half;

        return ans;
    }
};