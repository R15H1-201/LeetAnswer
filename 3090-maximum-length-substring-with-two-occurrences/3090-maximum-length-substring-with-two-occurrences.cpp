class Solution {
public:
    int maximumLengthSubstring(string s) {
        // Array to count frequency of each character (a-z)
        int charCount[26] = {0};
      
        // Variable to store the maximum length found
        int maxLength = 0;
      
        // Sliding window approach with left and right pointers
        int left = 0;
        for (int right = 0; right < s.length(); ++right) {
            // Get the index of current character (0-25 for a-z)
            int currentCharIndex = s[right] - 'a';
          
            // Increment count for current character
            ++charCount[currentCharIndex];
          
            // Shrink window from left if any character appears more than twice
            while (charCount[currentCharIndex] > 2) {
                int leftCharIndex = s[left] - 'a';
                --charCount[leftCharIndex];
                ++left;
            }
          
            // Update maximum length with current window size
            int currentWindowSize = right - left + 1;
            maxLength = max(maxLength, currentWindowSize);
        }
      
        return maxLength;
    }
};
