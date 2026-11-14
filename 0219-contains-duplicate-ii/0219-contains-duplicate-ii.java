import java.util.HashSet;
import java.util.Set;
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 1. Remove the element that is now more than k steps behind
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            // 2. If add() returns false, the number is already in the window
            if (!window.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}