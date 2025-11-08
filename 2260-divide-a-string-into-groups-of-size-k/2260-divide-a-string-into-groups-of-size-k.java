class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int groups = (n + k - 1) / k; // Calculate total groups
        String[] result = new String[groups]; // Store final strings
        
        for (int i = 0; i < groups; i++) {
            StringBuilder group = new StringBuilder();
            
            // Build each group of size k
            for (int j = 0; j < k; j++) {
                int index = i * k + j;
                if (index < n) group.append(s.charAt(index)); // Add character
                else group.append(fill); // Add fill if string ends
            }
            
            result[i] = group.toString(); // Save group
        }
        
        return result; // Return all groups
    }
}
