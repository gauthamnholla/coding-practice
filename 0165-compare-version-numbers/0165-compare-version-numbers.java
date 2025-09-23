// Java Optimal: Two pointers + On-the-fly Integer Parse
class Solution {
    public int compareVersion(String version1, String version2) {
        int i1 = 0, i2 = 0;               // pointers for version1 and version2
        int n1 = version1.length(), n2 = version2.length();

        // Iterate until both strings are fully processed
        while (i1 < n1 || i2 < n2) {
            int v1 = 0, v2 = 0;           // numeric values of current revision parts

            // Parse number from version1 until '.' or end
            while (i1 < n1 && version1.charAt(i1) != '.') {
                v1 = v1 * 10 + (version1.charAt(i1) - '0');
                i1++;
            }

            // Parse number from version2 until '.' or end
            while (i2 < n2 && version2.charAt(i2) != '.') {
                v2 = v2 * 10 + (version2.charAt(i2) - '0');
                i2++;
            }

            // Compare the current parts
            if (v1 < v2) return -1;   // version1 smaller
            if (v1 > v2) return 1;    // version1 larger

            // Skip the '.' character for the next round
            i1++;
            i2++;
        }

        // If we reach here, all parts matched → versions equal
        return 0;
    }
}
