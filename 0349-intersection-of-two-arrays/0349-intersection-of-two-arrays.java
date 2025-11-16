class Solution {
    
    // Add value to list only if it is not a duplicate
    void addList(ArrayList<Integer> list, int x) {
        if (list.isEmpty() || list.get(list.size() - 1) != x) {
            list.add(x);
        }
    }

    public int[] intersection(int[] arr1, int[] arr2) {
        Arrays.sort(arr1); // Sort both arrays
        Arrays.sort(arr2);

        ArrayList<Integer> list = new ArrayList<>();
        int i = 0, j = 0;

        // Two-pointer approach to find common elements
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                addList(list, arr1[i]); // Add only once
                i++;
                j++;
            } 
            else if (arr1[i] < arr2[j]) {
                i++; // Move pointer in smaller array
            } 
            else {
                j++;
            }
        }

        // Convert list to array
        int[] result = new int[list.size()];
        for (int k = 0; k < result.length; k++) {
            result[k] = list.get(k);
        }

        return result;
    }
}
