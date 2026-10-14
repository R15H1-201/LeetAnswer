class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if num * 9 < sum:
            return ""
        quotient, remainder = divmod(sum, 9)
        result = "9" * quotient
        if remainder:
            result += str(remainder)
        result += "0" * (num - len(result))

        return result