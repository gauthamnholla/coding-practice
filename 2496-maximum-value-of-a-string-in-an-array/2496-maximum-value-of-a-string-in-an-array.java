class Solution {
    public int maximumValue(String[] strs) {
        int mcount = 0;
        for (String s : strs) {
            if (s.chars().allMatch(Character::isLetter)) {
                mcount = Math.max(mcount, s.length());
            } else if (s.chars().allMatch(Character::isDigit)) {
                mcount = Math.max(mcount, Integer.parseInt(s));
            } else {
                mcount = Math.max(mcount, s.length());
            }
        }
        return mcount;
    }
}