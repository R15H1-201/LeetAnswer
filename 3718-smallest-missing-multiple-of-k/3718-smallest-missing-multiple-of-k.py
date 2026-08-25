class Solution:
    def missingMultiple(self, nums: list[int], k: int) -> int:
        num_set = set(nums)
        curr = k
        while curr in num_set:
            curr += k
        return curr
