class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Find the maximum element to determine the size of the count array
        int maxElement = arr1[0];
        for (int i = 0; i < arr1.length; i++) {
            maxElement = Math.max(maxElement, arr1[i]);
        }
        
        // Create frequency array
        int count[] = new int[maxElement + 1];
        for (int i = 0; i < arr1.length; i++) {
            count[arr1[i]]++;
        }

        int index = 0;
        
        // 1. Fill arr1 with elements in the order of arr2
        for (int i = 0; i < arr2.length; i++) {
            while (count[arr2[i]] > 0) {
                arr1[index] = arr2[i];
                index++;
                count[arr2[i]]--;
            }
        }
        
        // 2. Fill the remaining elements (not in arr2) in ascending order
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr1[index] = i;
                index++;
                count[i]--;
            }
        }
        
        return arr1;
    }
}