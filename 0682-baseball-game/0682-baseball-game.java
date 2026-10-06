class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> scoreStack = new ArrayDeque<>();
        for (String operation : operations) {
            if ("+".equals(operation)) {
                int lastScore = scoreStack.pop();
                int secondLastScore = scoreStack.peek();
                scoreStack.push(lastScore); 
                scoreStack.push(lastScore + secondLastScore);
            } else if ("D".equals(operation)) {
                scoreStack.push(scoreStack.peek() * 2); 
            } else if ("C".equals(operation)) {
                scoreStack.pop();
            } else {
                scoreStack.push(Integer.parseInt(operation));
            }
        }
        return scoreStack.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}