#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
private:
    const int MOD = 1e9 + 7;
    vector<long long> treeSum;
    vector<long long> treeSq;
    vector<long long> lazy;

    // Helper function to push lazy updates down to children
    void push(int node, int start, int end) {
        if (lazy[node] == 0) return;
        
        int mid = start + (end - start) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        long long val = lazy[node];

        // Update left child
        long long lenLeft = mid - start + 1;
        treeSq[leftNode] = (treeSq[leftNode] + 2 * val * treeSum[leftNode] % MOD + val * val % MOD * lenLeft % MOD) % MOD;
        treeSum[leftNode] = (treeSum[leftNode] + val * lenLeft) % MOD;
        lazy[leftNode] = (lazy[leftNode] + val) % MOD;

        // Update right child
        long long lenRight = end - mid;
        treeSq[rightNode] = (treeSq[rightNode] + 2 * val * treeSum[rightNode] % MOD + val * val % MOD * lenRight % MOD) % MOD;
        treeSum[rightNode] = (treeSum[rightNode] + val * lenRight) % MOD;
        lazy[rightNode] = (lazy[rightNode] + val) % MOD;

        lazy[node] = 0;
    }

    // Range update function to add 1 to elements in [l, r]
    void update(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return;
        
        if (l <= start && end <= r) {
            long long len = end - start + 1;
            treeSq[node] = (treeSq[node] + 2 * treeSum[node] + len) % MOD;
            treeSum[node] = (treeSum[node] + len) % MOD;
            lazy[node] = (lazy[node] + 1) % MOD;
            return;
        }

        push(node, start, end);
        int mid = start + (end - start) / 2;
        update(2 * node, start, mid, l, r);
        update(2 * node + 1, mid + 1, end, l, r);

        treeSum[node] = (treeSum[2 * node] + treeSum[2 * node + 1]) % MOD;
        treeSq[node] = (treeSq[2 * node] + treeSq[2 * node + 1]) % MOD;
    }

public:
    int sumCounts(vector<int>& nums) {
        int n = nums.size();
        
        // Initialize Segment Tree sizes (4 * n space allocation)
        treeSum.assign(4 * n, 0);
        treeSq.assign(4 * n, 0);
        lazy.assign(4 * n, 0);

        unordered_map<int, int> lastSeen;
        long long totalSum = 0;

        for (int r = 0; r < n; ++r) {
            // If the element was seen before, distinct count only increases 
            // for subarrays starting after its last occurrence.
            int l = lastSeen.count(nums[r]) ? lastSeen[nums[r]] + 1 : 0;
            
            // Increment the distinct counts for all valid starting indices in [l, r]
            update(1, 0, n - 1, l, r);
            
            // Record the current index as the element's last seen position
            lastSeen[nums[r]] = r;

            // treeSq[1] always holds the aggregate sum of squares for the active window
            totalSum = (totalSum + treeSq[1]) % MOD;
        }

        return totalSum;
    }
};
