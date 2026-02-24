class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int count = 0, org = capacity;

        for (int i = 0; i < plants.length; i++) {
            if (capacity >= plants[i]) {
                capacity -= plants[i];
                count++;
            } else {
                capacity = org;
                count += i * 2;     
                capacity -= plants[i];
                count++;             
            }
        }
        return count;
    }
}