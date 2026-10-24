class Solution:
    def countDaysTogether(
        self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str
    ) -> int:
        overlap_start = max(arriveAlice, arriveBob)
        overlap_end = min(leaveAlice, leaveBob)
        days_in_month = (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        start_month = int(overlap_start[:2])
        start_day = int(overlap_start[3:])
        start_day_of_year = sum(days_in_month[:start_month - 1]) + start_day
        end_month = int(overlap_end[:2])
        end_day = int(overlap_end[3:])
        end_day_of_year = sum(days_in_month[:end_month - 1]) + end_day
        return max(end_day_of_year - start_day_of_year + 1, 0)