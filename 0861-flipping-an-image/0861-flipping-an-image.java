class Solution {
    public int[][] flipAndInvertImage(int[][] image) {

        // Loop through each row
        for (int[] row : image) {

            // Process only half the row (mirror swap)
            for (int i = 0; i < (row.length + 1) / 2; i++) {

                // Flip and invert at the same time using XOR with 1
                int temp = row[i] ^ 1;
                row[i] = row[row.length - i - 1] ^ 1;
                row[row.length - i - 1] = temp;
            }
        }

        return image; // Result after flip + invert
    }
}
