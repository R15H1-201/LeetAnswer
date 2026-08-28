class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        n = len(s)

        cnt = [0] * 26
        for ch in s:
            cnt[ord(ch) - ord('a')] += 1

        # A palindrome can have at most one odd frequency.
        odd = 0
        middle = -1

        for i in range(26):
            if cnt[i] % 2:
                odd += 1
                middle = i

        if odd > 1:
            return ""

        # Counts for the left half.
        half = [x // 2 for x in cnt]
        half_len = n // 2

        prefix = []

        def build_max_palindrome():
            # Current prefix + largest possible remaining half.
            left = prefix[:]

            for c in range(25, -1, -1):
                left.extend([chr(ord('a') + c)] * half[c])

            left_str = ''.join(left)

            mid = "" if middle == -1 else chr(ord('a') + middle)

            return left_str + mid + left_str[::-1]

        for _ in range(half_len):
            chosen = False

            for c in range(26):
                if half[c] == 0:
                    continue

                # Try this character.
                half[c] -= 1
                prefix.append(chr(ord('a') + c))

                # If the largest completion still isn't > target,
                # this choice can never work.
                if build_max_palindrome() > target:
                    chosen = True
                    break

                # Undo.
                prefix.pop()
                half[c] += 1

            if not chosen:
                return ""

        left = ''.join(prefix)
        mid = "" if middle == -1 else chr(ord('a') + middle)

        ans = left + mid + left[::-1]

        return ans if ans > target else ""