
class Solution {
    public boolean checkString(String s) {
        boolean foundB = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == 'b') {
                foundB = true;
            } else if (ch == 'a' && foundB) {
                return false;
            }
        }

        return true;
    }
}
