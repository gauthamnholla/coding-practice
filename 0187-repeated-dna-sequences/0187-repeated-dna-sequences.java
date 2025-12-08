class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        // corner case
        if (s == null || s.length() < 10) return new ArrayList<String>();

        List<String> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        // initialize Sliding Window
        int hash = 0;
        int base = 4;

        for (int right = 0; right < s.length(); right++) {
            // update hash
            if (right >= 10) {
                hash -= getBits(s, right - 10) * Math.pow(base, 10 - 1);
            }
            hash *= base;
            hash += getBits(s, right);

            // update result & Sets
            if (right >= 10 - 1) {
                if (!set.add(hash)) { // hash already in set
                    if(duplicates.add(hash)) { // hash NOT yet in duplicates
                        result.add(s.substring(right - 10 + 1, right + 1));
                    }
                }
            }
            
        }

        return result;
    }

    // Helpers
    // get the 2 bits representation of a char at s[i]
    private int getBits(String s, int i) {
        return getBits(s.charAt(i));
    }

    // convert a char to a int bits
    private int getBits(char c) {
        assert c == 'A' || c == 'C' || c == 'T' || c == 'G': "should be a DNA Sequence character";

        switch(c) {
            case 'A':
                return 0b00;
            case 'C':
                return 0b01;
            case 'T':
                return 0b10;
            default:
                return 0b11;
        }
    }
}