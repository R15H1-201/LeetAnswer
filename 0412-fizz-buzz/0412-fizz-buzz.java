class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int currentNumber = 1; currentNumber <= n; currentNumber++) {
            String currentString = "";
            if (currentNumber % 3 == 0) {
                currentString += "Fizz";
            }
            if (currentNumber % 5 == 0) {
                currentString += "Buzz";
            }
            if (currentString.length() == 0) {
                currentString += currentNumber;
            }
            result.add(currentString);
        }     
        return result;
    }
}