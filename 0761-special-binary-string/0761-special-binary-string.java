class Solution {
    public String makeLargestSpecial(String s) {
        // 1. Identify independent "Special Substrings" at the current level
        List<String> results = new ArrayList<>();
        int count = 0; 
        int start = 0;
        
        for (int end = 0; end < s.length(); end++) {
            // Treat '1' as ( and '0' as )
            count += (s.charAt(end) == '1') ? 1 : -1;
            
            // 2. When count == 0, we found a complete, independent special substring
            if (count == 0) {
                /* 3. RECURSION & STRIPPING:
                   A special string always starts with '1' and ends with '0'.
                   To optimize it, we leave the outer '1' and '0' alone and 
                   recursively sort the "inner" part of this block.
                */
                String innerPart = s.substring(start + 1, end);
                results.add("1" + makeLargestSpecial(innerPart) + "0");
                
                // Move start pointer to the beginning of the next possible block
                start = end + 1;
            }
        }
        
        // 4. GREEDY SORT: 
        // Since we can swap any two consecutive special substrings, 
        // we can essentially sort all blocks at this level.
        Collections.sort(results, Collections.reverseOrder());
        
        // 5. JOIN: Combine the sorted blocks to form the largest string
        return String.join("", results);
    }
}