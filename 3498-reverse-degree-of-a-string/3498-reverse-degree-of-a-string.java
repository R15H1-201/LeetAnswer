class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        for (int i = 0; i < s.length(); i++) {
            int reversePos = 26 - (s.charAt(i) - 'a');
            totalDegree += reversePos * (i + 1);
        }
        return totalDegree;
    }
}