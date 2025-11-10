class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if left and right spots are empty (or edge positions)
            boolean left = i == 0 || flowerbed[i - 1] == 0;
            boolean right = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
            
            // Place a flower if current spot and neighbors are empty
            if (left && right && flowerbed[i] == 0) {
                flowerbed[i] = 1; // Plant flower
                n--;              // One less flower to plant
            }
        }
        return n <= 0; // True if all flowers can be planted
    }
}
