class Solution {
    static final long M1 = 1_000_000_007L;
    static final long M2 = 998_244_353L;
    long[] h1, h2, p1, p2;
    int n;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        long base = 100_003L + new Random().nextInt(1_000_000);

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;

        // prefix hashes: hash of nums[0..i-1]
        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * base + nums[i]) % M1;
            h2[i + 1] = (h2[i] * base + nums[i]) % M2;
            p1[i + 1] = (p1[i] * base) % M1;
            p2[i + 1] = (p2[i] * base) % M2;
        }

        // binary search on the length
        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (hasUnique(mid)) hi = mid;      // works, try shorter
            else lo = mid + 1;                 // fails, need longer
        }
        return lo;
    }

    // Is there a window of length L that appears exactly once?
    private boolean hasUnique(int L) {
        Map<Long, Integer> count = new HashMap<>();

        for (int i = 0; i + L <= n; i++) {
            long a = ((h1[i + L] - h1[i] * p1[L]) % M1 + M1) % M1;
            long b = ((h2[i + L] - h2[i] * p2[L]) % M2 + M2) % M2;
            long key = a * M2 + b;             // combine both hashes into one key
            count.merge(key, 1, Integer::sum);
        }

        for (int c : count.values()) {
            if (c == 1) return true;
        }
        return false;
    }
}