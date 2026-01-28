class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int i = 0, j = plants.length - 1;
        int waterA = capacityA;
        int waterB = capacityB;
        int refills = 0;

        while (i < j) {
            // Alice waters plant i
            if (waterA < plants[i]) {
                refills++;
                waterA = capacityA;
            }
            waterA -= plants[i];
            i++;

            // Bob waters plant j
            if (waterB < plants[j]) {
                refills++;
                waterB = capacityB;
            }
            waterB -= plants[j];
            j--;
        }

        // If both meet at the same plant
        if (i == j) {
            if (Math.max(waterA, waterB) < plants[i]) {
                refills++;
            }
        }

        return refills;
    }
}
