class Solution {
    public int minInsertions(String s) {
        int insertionsNeeded = 0; 
        int openParentheses = 0;  
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);       
            if (currentChar == '(') {
                openParentheses++;
            } else {
                if (i < length - 1 && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertionsNeeded++;
                }
                if (openParentheses == 0) {
                    insertionsNeeded++;
                } else {
                    openParentheses--;
                }
            }
        }
        insertionsNeeded += openParentheses * 2;      
        return insertionsNeeded;
    }
}