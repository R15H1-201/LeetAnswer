from collections import defaultdict


class Solution:
    def maximumSaleItems(self, items: list[list[int]], budget: int) -> int:
        n = len(items)
        cnt_factor = [0] * (n + 1)
        min_price = float("inf")

        for factor, price in items:
            cnt_factor[factor] += 1
            if price < min_price:
                min_price = price

        cnt_multi = [0] * (n + 1)
        price_to_freebies = defaultdict(int)

        for factor, price in items:
            if price >= 2 * min_price:
                continue
            if cnt_multi[factor] == 0:
                total = 0
                for multiple in range(factor, n + 1, factor):
                    total += cnt_factor[multiple]
                cnt_multi[factor] = total

            freebies = cnt_multi[factor] - 1
            if freebies > 0:
                price_to_freebies[price] += freebies

        ans = 0
        rem = budget
        for price in sorted(price_to_freebies):
            if rem < price:
                break
            take = min(price_to_freebies[price], rem // price)
            rem -= price * take
            ans += 2 * take

        return ans + (rem // min_price)
