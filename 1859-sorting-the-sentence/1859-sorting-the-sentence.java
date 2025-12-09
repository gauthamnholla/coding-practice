class Solution {
    public String sortSentence(String s) {
        String s1[] = s.split(" ");
        String s2[] = new String[s1.length];
        
        for (int i = 0; i < s1.length; i++) {
            String t1 = s1[i]; // taking the string separately
            int t = t1.charAt(t1.length() - 1) - '0'; // extracting last digit as position
            s2[t - 1] = t1.substring(0, t1.length() - 1); // placing word in correct position
        }

        String s9 = "";
        for (int i = 0; i < s1.length; i++) {
            s9 += s2[i] + " "; // building the final sentence
        }

        return s9.trim(); // remove extra space at the end
    }
}