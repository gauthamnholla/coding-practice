class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // Use helper function to find missing numbers
        return findDuplicatesElements(nums);
    }

    // Perform cyclic sort to place each number in its correct position
    void cyclicSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correct = arr[i] - 1; // correct index for current element
            if (arr[i] != arr[correct]) {
                swap(arr, i, correct); // place element at correct index
            } else {
                i++;
            }
        }
    }

    // Swap helper function
    void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    // Find missing numbers after cyclic sort
    List<Integer> findDuplicatesElements(int[] arr) {
        cyclicSort(arr);

        List<Integer> ans = new ArrayList<>();
        for (int index = 0; index < arr.length; index++) {
            // If number is not in the correct position → missing number
            if (arr[index] != index + 1) {
                ans.add(index + 1);
            }
        }

        return ans;
    }
}
