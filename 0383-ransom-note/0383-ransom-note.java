class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] letterFrequency = new int[26];      
        for (int i = 0; i < magazine.length(); i++) {
            char currentChar = magazine.charAt(i);
            int charIndex = currentChar - 'a';
            letterFrequency[charIndex]++;
        }      
        for (int i = 0; i < ransomNote.length(); i++) {
            char currentChar = ransomNote.charAt(i);
            int charIndex = currentChar - 'a';          
            letterFrequency[charIndex]--;
            if (letterFrequency[charIndex] < 0) {
                return false;
            }
        }      
        return true;
    }
}