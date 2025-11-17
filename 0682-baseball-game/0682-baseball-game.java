class Solution {
    public int calPoints(String[] operations) {
        int n = operations.length;
        int[] arr = new int[n]; // Stores valid round scores
        int j = 0;              // Index for arr (like a stack top)
        
        for (int i = 0; i < n; i++) {
            char op = operations[i].charAt(0);

            if (op == '+') {
                // Add last two valid scores
                arr[j] = arr[j - 1] + arr[j - 2];
                j++;
            }
            else if (op == 'D') {
                // Double last score
                arr[j] = 2 * arr[j - 1];
                j++;
            }
            else if (op == 'C') {
                // Remove last score
                j--;
                arr[j] = 0;
            }
            else {
                // Add new score (number)
                arr[j] = Integer.parseInt(operations[i]);
                j++;
            }
        }

        // Compute total sum
        int sum = 0;
        for (int k = 0; k < j; k++) {
            sum += arr[k];
        }

        return sum;
    }
}
