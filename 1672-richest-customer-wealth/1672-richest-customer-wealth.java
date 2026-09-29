class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        for (int[] customerAccounts : accounts) {
            int currentCustomerWealth = 0;
            for (int accountBalance : customerAccounts) {
                currentCustomerWealth += accountBalance;
            }
            maxWealth = Math.max(maxWealth, currentCustomerWealth);
        }
        return maxWealth;
    }
}