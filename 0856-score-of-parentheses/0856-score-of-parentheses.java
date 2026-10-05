class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentScore = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(currentScore);
                currentScore = 0;
            } else {
                int innerScore = currentScore;
                int outerScore = stack.pop();
                currentScore = outerScore + Math.max(2 * innerScore, 1);
            }
        }
        return currentScore;
    }
}