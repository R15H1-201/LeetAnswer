class Solution:
    def minOperations(self, s: str) -> int:
        n = len(s)
        
        # Case 1: Already sorted
        if all(s[i] <= s[i + 1] for i in range(n - 1)):
            return 0
            
        # Case 2: Unsorted with length 2 is impossible
        if n == 2:
            return -1
            
        # Identify the global minimum and maximum characters
        mn = min(s)
        mx = max(s)
        
        # Case 3: min is at the start OR max is at the end
        if s[0] == mn or s[n - 1] == mx:
            return 1
            
        # Case 4: min or max exists somewhere in the middle
        for i in range(1, n - 1):
            if s[i] == mn or s[i] == mx:
                return 2
                
        # Case 5: max is at index 0 AND min is at the last index
        return 3
