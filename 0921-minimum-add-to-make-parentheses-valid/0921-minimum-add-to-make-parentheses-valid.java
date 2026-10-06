class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char currentChar : s.toCharArray()) {
            if (currentChar == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(currentChar);
            }
        }
        return stack.size();
    }
}