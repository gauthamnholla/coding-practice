class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        // Morse code map for each letter a–z
        String[] code = {
            ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
            "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-",
            "..-","...-",".--","-..-","-.--","--.."
        };

        Set<String> set = new HashSet<>(); // Stores unique transformations

        for (String word : words) {
            StringBuilder sb = new StringBuilder();

            // Convert each character to Morse code
            for (char c : word.toCharArray()) {
                sb.append(code[c - 'a']);
            }

            set.add(sb.toString()); // Add the Morse transformation
        }

        return set.size(); // Number of unique Morse codes
    }
}
