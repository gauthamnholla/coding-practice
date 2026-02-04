class Solution 
{
    public int minimumOperations(String s) 
    {
        int min = Integer.MAX_VALUE; // Initialize a variable to store the minimum operations.

        char c[] = s.toCharArray(); // Convert the input string to a character array for easier traversal.

        for (int i = c.length - 1; i >= 0; i--) 
        {
            if (c[i] == '5') 
            {
                for (int j = i - 1; j >= 0; j--) 
                {
                    if (c[j] == '2' || c[j] == '7') 
                    {
                        int f = i - j - 1; // Calculate the number of characters between i and j exclusive.
                        int l = c.length - 1 - i; // Calculate the number of characters from i to the end of the string.

                        min = Math.min(f + l, min); // Update the minimum operations with the minimum of the current value and f + l.
                        break; // Exit the inner loop since we found a valid pair.
                    }
                }
            }

            if (c[i] == '0') 
            {
                for (int j = i - 1; j >= 0; j--) 
                {
                    if (c[j] == '0' || c[j] == '5') 
                    {
                        int f = i - j - 1; // Calculate the number of characters between i and j exclusive.
                        int l = c.length - 1 - i; // Calculate the number of characters from i to the end of the string.

                        min = Math.min(f + l, min); // Update the minimum operations with the minimum of the current value and f + l.
                        break; // Exit the inner loop since we found a valid pair.
                    }
                }
            }
        }

        if (min == Integer.MAX_VALUE) 
        {
            // If no valid pairs of '5' or '0' are found, check if the string contains '0'.
            // If it does, return the length of the string minus 1, otherwise, return the length of the string.
            if (s.contains("0"))
                return s.length() - 1;
            else
                return s.length();
        }

        return min; // Return the minimum operations.
    }
}