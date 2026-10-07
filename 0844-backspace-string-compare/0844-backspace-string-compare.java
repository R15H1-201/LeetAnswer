class Solution {
    public boolean backspaceCompare(String s, String t) {
        int sPointer = s.length() - 1;
        int tPointer = t.length() - 1;
        int sBackspaceCount = 0;
        int tBackspaceCount = 0;
        while (sPointer >= 0 || tPointer >= 0) {
            while (sPointer >= 0) {
                if (s.charAt(sPointer) == '#') {
                    sBackspaceCount++;
                    sPointer--;
                } else if (sBackspaceCount > 0) {
                    sBackspaceCount--;
                    sPointer--;
                } else {
                    break;
                }
            }
            while (tPointer >= 0) {
                if (t.charAt(tPointer) == '#') {
                    tBackspaceCount++;
                    tPointer--;
                } else if (tBackspaceCount > 0) {
                    tBackspaceCount--;
                    tPointer--;
                } else {
                    break;
                }
            }
            if (sPointer >= 0 && tPointer >= 0) {
                if (s.charAt(sPointer) != t.charAt(tPointer)) {
                    return false;
                }
            } else if (sPointer >= 0 || tPointer >= 0) {
                return false;
            }
            sPointer--;
            tPointer--;
        }
        return true;
    }
}
