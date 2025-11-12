class Solution {
    public int romanToInt(String s) {
        int res = 0;

        // Step 1: Map Roman numerals to their values
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        // Step 2: Traverse the string
        for (int i = 0; i < s.length() - 1; i++) {
            // If current value < next value → subtract
            if (roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                res -= roman.get(s.charAt(i));
            } 
            // Otherwise → add
            else {
                res += roman.get(s.charAt(i));
            }
        }

        // Step 3: Add the value of the last character
        return res + roman.get(s.charAt(s.length() - 1));
    }
}
