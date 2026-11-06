class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int queryCount = queries.length;
        int[][] queriesWithIndex = new int[queryCount][2];
        for (int i = 0; i < queryCount; ++i) {
            queriesWithIndex[i] = new int[] {queries[i], i};
        }
        Arrays.sort(queriesWithIndex, (a, b) -> a[0] - b[0]);
        Map<Integer, Integer> serverRequestCount = new HashMap<>();
        int[] result = new int[queryCount];
        int leftPointer = 0;
        int rightPointer = 0;
        for (int[] currentQuery : queriesWithIndex) {
            int rightBound = currentQuery[0];
            int originalIndex = currentQuery[1];
            int leftBound = rightBound - x;
            while (rightPointer < logs.length && logs[rightPointer][1] <= rightBound) {
                int serverId = logs[rightPointer][0];
                serverRequestCount.merge(serverId, 1, Integer::sum);
                rightPointer++;
            }
            while (leftPointer < logs.length && logs[leftPointer][1] < leftBound) {
                int serverId = logs[leftPointer][0];
                if (serverRequestCount.merge(serverId, -1, Integer::sum) == 0) {
                    serverRequestCount.remove(serverId);
                }
                leftPointer++;
            }
            result[originalIndex] = n - serverRequestCount.size();
        }
      
        return result;
    }
}