class Solution:
  def sumOfSquares(self, nums: list[int]) -> int:
    return sum(num ** 2 for i, num in enumerate(nums, 1) if len(nums) % i == 0)